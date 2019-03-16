package com.wh.festec.utils.timer;

import java.util.TimerTask;

/**
 * Created by 41926 on 2019/3/1 22:15.
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.mITimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if(mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
