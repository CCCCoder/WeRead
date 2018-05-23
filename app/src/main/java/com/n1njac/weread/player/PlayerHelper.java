package com.n1njac.weread.player;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/*
 *    Created by N1njaC on 2018/5/20.
 *    email:aiai173cc@gmail.com
 */
public class PlayerHelper extends Service implements IPlayback {

    private Player mPlayer;

    public class PlayerBinder extends Binder {
        public PlayerHelper getHelper() {
            return PlayerHelper.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = Player.getInstance();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayerBinder();
    }

    @Override
    public void onDestroy() {
        release();
        super.onDestroy();
    }

    @Override
    public void start(String url) {
        mPlayer.start(url);
    }

    @Override
    public void pause() {
        mPlayer.pause();
    }

    @Override
    public void resume() {
        mPlayer.resume();
    }

    @Override
    public void release() {
        mPlayer.release();
    }

    @Override
    public void stop() {
        mPlayer.stop();
    }

    @Override
    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    @Override
    public int getDuration() {
        return mPlayer.getDuration();
    }

    @Override
    public int getCurrentProgress() {
        return mPlayer.getCurrentProgress();
    }

    @Override
    public void seekTo(int progress) {
        mPlayer.seekTo(progress);
    }

    @Override
    public void registerCallback(Callback callback) {
        mPlayer.registerCallback(callback);
    }

    @Override
    public void unregisterCallback(Callback callback) {
        mPlayer.unregisterCallback(callback);
    }

    @Override
    public String getUrl() {
        return mPlayer.getUrl();
    }

}
