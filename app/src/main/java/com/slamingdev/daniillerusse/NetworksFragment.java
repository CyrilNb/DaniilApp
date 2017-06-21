package com.slamingdev.daniillerusse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by SlamingDev on 05/02/2016.
 */
public class NetworksFragment extends Fragment implements View.OnClickListener{
    ImageView imgViewFB;
    ImageView imgViewTwitter;
    ImageView imgViewVine;
    ImageView imgViewInstagram;
    ImageView imgViewSnapchat;
    ImageView imgViewYoutube;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_networks, container, false);
        imgViewFB = (ImageView) view.findViewById(R.id.imgViewFacebook);
        imgViewTwitter = (ImageView) view.findViewById(R.id.imgViewTwitter);
        imgViewVine = (ImageView) view.findViewById(R.id.imgViewVine);
        imgViewInstagram = (ImageView) view.findViewById(R.id.imgViewInstagram);
        imgViewSnapchat = (ImageView) view.findViewById(R.id.imgViewSnapchat);
        imgViewYoutube = (ImageView) view.findViewById(R.id.imgViewYoutube);


        imgViewFB.setOnClickListener(this);
        imgViewTwitter.setOnClickListener(this);
        imgViewVine.setOnClickListener(this);
        imgViewInstagram.setOnClickListener(this);
        imgViewSnapchat.setOnClickListener(this);
        imgViewYoutube.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.imgViewFacebook:

                Intent intentFB = null;
                String uriFb = "fb://page/421727931308629";
                //Intent intentFB = new Intent("android.intent.category.LAUNCHER");
                //intentFB.setClassName("com.facebook.katana","com.facebook.katana.LoginActivity");

                try {
                    getActivity().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    //intentFB.setPackage("com.facebook.katana");
                    intentFB = new Intent(Intent.ACTION_VIEW, Uri.parse(uriFb));
                    intentFB.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Facebook app, revert to browser
                    intentFB = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/daniillerusse"));
                }

                startActivity(intentFB);
                break;

            case R.id.imgViewTwitter:

                Intent intentTwitter = null;
                String uriTwitter = "twitter://user?user_id=2204893904";

                try{
                    getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intentTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse(uriTwitter));
                    intentTwitter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }catch (Exception e){
                    //no Twitter app, revert to browser
                    intentTwitter = new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/Daniil_le_russe"));
                }
                startActivity(intentTwitter);
                break;

            case R.id.imgViewVine:

                Intent intentVine = null;
                String uriVine = "https://vine.co/u/1061393981138751488";

                try{
                    getActivity().getPackageManager().getPackageInfo("co.vine.android", 0);
                    intentVine = new Intent(Intent.ACTION_VIEW, Uri.parse(uriVine));
                    intentVine.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }catch (Exception e){
                    //no Twitter app, revert to browser
                    intentVine = new Intent(Intent.ACTION_VIEW,Uri.parse("https://vine.co/u/1061393981138751488"));
                }
                startActivity(intentVine);
                break;

            case R.id.imgViewInstagram:
                Intent intentInsta = null;
                String uriInsta = "https://www.instagram.com/_u/daniil_lerusse";

                try{
                    getActivity().getPackageManager().getPackageInfo("com.instagram.android", 0);
                    intentInsta = new Intent(Intent.ACTION_VIEW, Uri.parse(uriInsta));
                    intentInsta.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }catch (Exception e){
                    //no Twitter app, revert to browser
                    intentInsta = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/daniil_lerusse"));
                }
                startActivity(intentInsta);
                break;

            case R.id.imgViewYoutube:
                Intent intenYoubtube = null;
                String uriYoutube = "https://www.youtube.com/user/Tsarm";
                try{
                    getActivity().getPackageManager().getPackageInfo("com.google.android.youtube", 0);
                    intenYoubtube = new Intent(Intent.ACTION_VIEW, Uri.parse(uriYoutube));
                    intenYoubtube.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }catch (Exception e){
                    //no Youtube app, revert to browser
                    intenYoubtube = new Intent(Intent.ACTION_VIEW,Uri.parse(uriYoutube));
                }
                startActivity(intenYoubtube);
                break;

        }
    }

}
