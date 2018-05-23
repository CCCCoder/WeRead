package com.n1njac.weread.player;

import android.net.Uri;

/*
 *    Created by N1njaC on 2018/5/20.
 *    email:aiai173cc@gmail.com
 */
public interface IPlayback {

    public void start(String url);

    public void pause();

    public void resume();

    public void release();

    void stop();

    boolean isPlaying();

    int getDuration();

    int getCurrentProgress();

    void seekTo(int progress);

    void registerCallback(Callback callback);

    void unregisterCallback(Callback callback);

    String getUrl();

    public interface Callback {
        void onPlayStatusChanged(PlayState status);
    }
}
