package com.n1njac.weread.player;

import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/*
 *    Created by N1njaC on 2018/5/20.
 *    email:aiai173cc@gmail.com
 */
public class Player implements IPlayback, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    private static Player sInstance;
    private MediaPlayer mMediaPlayer;
    private Callback mCallback;
    private boolean isPaused;
    private String mCurrentUrl;

    private Player() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnCompletionListener(this);
    }

    public static Player getInstance() {
        if (sInstance == null) {
            synchronized (Player.class) {
                if (sInstance == null) {
                    sInstance = new Player();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void start(String url) {
        try {
            mCurrentUrl = url;
            notifyPlayStatusChanged(PlayState.INIT);
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepare();
        } catch (IOException e) {
            notifyPlayStatusChanged(PlayState.ERROR);
            e.printStackTrace();
        }
    }

    @Override
    public void pause() {
        if (isPlaying()) {
            notifyPlayStatusChanged(PlayState.PAUSE);
            isPaused = true;
            mMediaPlayer.pause();
        }
    }

    @Override
    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    @Override
    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    @Override
    public int getCurrentProgress() {
        return mMediaPlayer.getCurrentPosition();
    }


    @Override
    public void seekTo(int progress) {
        mMediaPlayer.seekTo(progress);
    }

    @Override
    public void resume() {
        if (isPaused) {
            notifyPlayStatusChanged(PlayState.RESUME);
            isPaused = false;
            mMediaPlayer.start();
        }
    }

    @Override
    public void release() {
        mMediaPlayer.reset();
        mMediaPlayer.release();
        mMediaPlayer = null;
        sInstance = null;
    }

    @Override
    public void registerCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterCallback(Callback callback) {
        this.mCallback = null;
    }

    @Override
    public String getUrl() {
        return mCurrentUrl;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
        notifyPlayStatusChanged(PlayState.PLAYING);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mMediaPlayer.reset();
        notifyPlayStatusChanged(PlayState.COMPLETE);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        notifyPlayStatusChanged(PlayState.ERROR);
        return false;
    }

    private void notifyPlayStatusChanged(PlayState status) {
        mCallback.onPlayStatusChanged(status);
    }
}
