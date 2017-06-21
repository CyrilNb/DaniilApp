package com.slamingdev.daniillerusse;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by Cyril on 12/02/2016.
 */
public class SoundboxFragment extends Fragment {
    GridView gridView;
    MediaPlayer mPlayer;
    AudioManager audioManager;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_soundbox, container, false);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(context));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public synchronized void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                try{
                    if(mPlayer != null && mPlayer.isPlaying())
                    {
                        mPlayer.stop();
                        mPlayer.release();
                        playSong(position);
                    }
                    else
                    {
                        playSong(position);
                    }
                }catch(Exception ex){
                    //Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        //System.exit(0);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.exit(0);
    }

    private void playSong(int position){
        switch(position){
            case 0:
                mPlayer = MediaPlayer.create(gridView.getContext(),R.raw.entrevraibonhommes);
                mPlayer.start();
                break;
            case 1:
                mPlayer = MediaPlayer.create(gridView.getContext(),R.raw.gardelamonnaie);
                mPlayer.start();
                break;
            case 2:
                mPlayer = MediaPlayer.create(gridView.getContext(),R.raw.menbaslescouilles);
                mPlayer.start();
                break;
            case 3:
                mPlayer = MediaPlayer.create(gridView.getContext(),R.raw.jesimulais);
                mPlayer.start();
                break;
            case 4:
                mPlayer = MediaPlayer.create(gridView.getContext(),R.raw.seulcheztoi);
                mPlayer.start();
                break;
            case 5:
                mPlayer = MediaPlayer.create(gridView.getContext(),R.raw.toutvabien);
                mPlayer.start();
                break;
            case 6:
                mPlayer = MediaPlayer.create(gridView.getContext(),R.raw.appellemoilegros);
                mPlayer.start();
                break;
            case 7:
                mPlayer = MediaPlayer.create(gridView.getContext(),R.raw.jesuisquunturc);
                mPlayer.start();
                break;
            case 8:
                mPlayer = MediaPlayer.create(gridView.getContext(),R.raw.questcequetuvasfaire);
                mPlayer.start();
                break;
            case 9:
                mPlayer = MediaPlayer.create(gridView.getContext(),R.raw.justeunebaguette);
                mPlayer.start();
                break;
        }
    }

    public MediaPlayer getPlayer(){
        return mPlayer;
    }
}
