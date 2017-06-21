package com.slamingdev.daniillerusse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.ShareActionProvider;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.tjeannin.apprate.AppRate;

public class MainActivity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private PagerSlidingTabStrip tabsStrip;
    private SampleFragmentPagerAdapter fragmentPagerAdapter;
    private ShareActionProvider mShareActionProvider;
    int currentPageSelected;
    boolean internetAccess;
    boolean isAdviewLoaded;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            View menuItemView = findViewById(R.id.action_settings);
            PopupMenu popupMenu = new PopupMenu(this, menuItemView);
            popupMenu.inflate(R.menu.about);
            popupMenu.show();
            return true;
        }

        if(id == R.id.share){
            this.onShareAction();
            return true;
        }
        if(id == R.id.aboutDev){
            this.otherApps(item);
            return true;
        }
        if(id == R.id.rate){
            this.rate(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onShareAction(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Super la nouvelle application android Daniil le Russe !";
        String linkApp = "https://play.google.com/store/apps/details?id=com.slamingdev.daniillerusse";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody + "\n" + linkApp);
        startActivity(Intent.createChooser(sharingIntent, "Partager avec :"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isAdviewLoaded = false;
        internetAccess = checkDataConnection();
//        if(internetAccess) {
//            setContentView(R.layout.activity_main_app);

//            final AdView adView = (AdView) this.findViewById(R.id.adView);
//            final AdRequest adRequest1 = new AdRequest.Builder().build();
//
//            final InterstitialAd interstitial2 = new InterstitialAd(getApplicationContext());
//            interstitial2.setAdUnitId("ca-app-pub-2373549811754777/1329506047");
//            interstitial2.loadAd(new AdRequest.Builder().build());
//            interstitial2.setAdListener(new AdListener() {
//                @Override
//                public void onAdLoaded() {
//                    if (interstitial2.isLoaded()) {
//                        interstitial2.show();
//                    }
//                }
//
//                @Override
//                public void onAdClosed() {
//                    adView.loadAd(adRequest1);
//                    isAdviewLoaded = true;
//                }
//
//                @Override
//                public void onAdFailedToLoad(int errorCode) {
//                    adView.loadAd(adRequest1);
//                    isAdviewLoaded = true;
//                }
//            });
//
//            if (!isAdviewLoaded)
//                adView.loadAd(adRequest1);
//
//        }
//        else{
//            setContentView(R.layout.activity_main_no_ad);
//        }

        setContentView(R.layout.activity_main_no_ad);
        initColor();
        // Give the PagerSlidingTabStrip the ViewPager
        tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        fragmentPagerAdapter = new SampleFragmentPagerAdapter(getSupportFragmentManager(), this.getApplicationContext());
        viewPager.setAdapter(fragmentPagerAdapter);

        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        // Set a listener to handle events when pages selected is changed
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPageSelected = position;
                //YoutubeFragment mYtFragment = fragmentPagerAdapter.getYtFragment();
                FacebookFragment mFbFragment = fragmentPagerAdapter.getFbFragment();
                SoundboxFragment soundboxFragment = fragmentPagerAdapter.getSoundboxFragment();
                android.support.v7.app.ActionBar actionBar = getSupportActionBar();

                switch (currentPageSelected) {
                    /*case 0:
                        String url = mYtFragment.url0;
                        mYtFragment.webView.loadUrl(url);
                        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CC181E")));
                        tabsStrip.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorYoutubeFragment));
                        mFbFragment.webView.loadUrl("about:blank");
                        stopMusic(soundboxFragment);
                        break;*/
                    case 0:
                        String url2 = mFbFragment.url0;
                        mFbFragment.webView.loadUrl(url2);
                        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3B5998")));
                        tabsStrip.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorFacebookFragment));
                        //mYtFragment.webView.loadUrl("about:blank");
                        stopMusic(soundboxFragment);
                        break;
                    case 1:
                        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F4842D")));
                        tabsStrip.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorShowFragment));
                        stopMusic(soundboxFragment);
                        break;
                    case 2:
                        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7253A7")));
                        tabsStrip.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorNetworksFragment));
                        stopMusic(soundboxFragment);
                        break;
                    case 3:
                        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F9FE0")));
                        tabsStrip.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSoundboxFragment));
                        stopMusic(soundboxFragment);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initColor() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3B5998")));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    switch (currentPageSelected) {
                        /*case 0:
                            if (fragmentPagerAdapter != null && fragmentPagerAdapter.getYtFragment().canGoBack()) {
                                fragmentPagerAdapter.getYtFragment().goBack();
                                break;
                            }*/
                        case 0:
                            if (fragmentPagerAdapter != null && fragmentPagerAdapter.getFbFragment().canGoBack()) {
                                fragmentPagerAdapter.getFbFragment().goBack();
                                break;
                            }
                        default:
                            finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void stopMusic(SoundboxFragment sbfrag){
        if(sbfrag != null){
            if(sbfrag.getPlayer() != null){
                if(sbfrag.getPlayer().isPlaying()) {
                    sbfrag.getPlayer().stop();
                    //soundboxFragment.getPlayer().release();
                }
            }
        }
    }

    public boolean rate(MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                //.setCustomTitle("Noter l'application")
                .setIcon(R.drawable.ic_star_rate_black_18dp)
                .setMessage("L'application te plaît ? \n Une petite notation fait toujours plaisir :)")
                .setPositiveButton("Wesh ça marche je vais la noter.",null)
                .setNegativeButton("Nope, je passe mon tour.",null)
                .setNeutralButton("Pas maintenant, rapelle le moi plus tard.",null);

        new AppRate(this)
                //.setCustomDialog(builder)
                .init();

        return true;
    }


    public boolean otherApps(MenuItem item){
        String url0 = "https://play.google.com/store/apps/developer?id=SlamingDev";
        //String url1 = "market://details?id=SlamingDev";
       // try{
         //   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url1)));
        //}catch (ActivityNotFoundException anfe){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url0)));
        //}
        return  true;
    }

    private boolean checkDataConnection(){
        boolean status = false;
        ConnectivityManager connectivityMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityMgr.getActiveNetworkInfo()!=null &&
                connectivityMgr.getActiveNetworkInfo().isAvailable() &&
                connectivityMgr.getActiveNetworkInfo().isConnected()) {
            status = true;
        }
        return status;
    }
}
