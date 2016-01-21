package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

/**
 * Created by user on 14.10.2015.
 */
public class MyTripsFrag extends Fragment implements TextureView.SurfaceTextureListener {
    Context cont;
    // Log tag.
    private static final String TAG = MyTripsFrag.class.getName();

    // Asset video file name.
    private static final String FILE_NAME = "rzhd.mp4";

    // MediaPlayer instance to control playback of video file.
    private MediaPlayer mMediaPlayer;

    TextureView myVideo;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        cont = activity;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_trips_frag, container, false);
        myVideo = (TextureView)v.findViewById(R.id.VideoMaster);
        myVideo.setSurfaceTextureListener(this);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Surface mySurface = new Surface(surface);

        try {

            AssetFileDescriptor afd = cont.getAssets().openFd(FILE_NAME);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mMediaPlayer.setSurface(mySurface);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.prepareAsync();

            // Play video when the media source is ready for playback.
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

        } catch (IllegalArgumentException e) {
            Log.d(TAG, e.getMessage());
        } catch (SecurityException e) {
            Log.d(TAG, e.getMessage());
        } catch (IllegalStateException e) {
            Log.d(TAG, e.getMessage());
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
