package com.slamingdev.daniillerusse;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.larvalabs.svgandroid.*;

/**
 * Created by SlamingDev on 26/01/2016.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    final int PAGE_COUNT = 4;
    private Context context;
    public FacebookFragment fbFragment;
    public YoutubeFragment ytFragment;
    public NetworksFragment networksFragment;
    public ShowFragment showFragment;
    public SoundboxFragment soundboxFragment;

//    private String tabTitles[] = new String[] { "Youtube", "Facebook", "Shows", "Réseaux sociaux" };
   private int tabIcons[] = {R.mipmap.facebook,R.mipmap.audio,R.mipmap.tickets,R.mipmap.thumbs};

    public SampleFragmentPagerAdapter(FragmentManager fm,Context c) {
        super(fm);
        this.context = c;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            /*case 0:
                ytFragment = new YoutubeFragment();
                return ytFragment;*/
            case 0:
                fbFragment = new FacebookFragment();
                return fbFragment;
            case 1:
                soundboxFragment = new SoundboxFragment();
                return soundboxFragment;
            case 2:
                showFragment = new ShowFragment();
                return  showFragment;
            case 3:
                networksFragment = new NetworksFragment();
                return networksFragment;
            default:
                return PageFragment.newInstance(position + 1);
        }
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        // Generate title based on item position
//        return tabTitles[position];
//    }

    @Override
    public int getPageIconResId(int position) {
        // Generate icon bases on item position
        return tabIcons[position];
    }

    public FacebookFragment getFbFragment(){
        return this.fbFragment;
    }

    /*public YoutubeFragment getYtFragment(){
        return this.ytFragment;
    }*/

    public SoundboxFragment getSoundboxFragment(){
        return this.soundboxFragment;
    }
}
