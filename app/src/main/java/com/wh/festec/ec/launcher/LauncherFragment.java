package com.wh.festec.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.wh.festec.R;
import com.wh.festec.app.AccountManager;
import com.wh.festec.app.IUserChecker;
import com.wh.festec.fragment.LatteFragment;
import com.wh.festec.ui.launcher.ILauncherListener;
import com.wh.festec.ui.launcher.OnLauncherFinishTag;
import com.wh.festec.ui.launcher.ScrollLauncherTag;
import com.wh.festec.utils.storage.LattePreference;
import com.wh.festec.utils.timer.BaseTimerTask;
import com.wh.festec.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 41926 on 2019/3/1 22:20.
 */
public class LauncherFragment extends LatteFragment implements ITimerListener {

    @BindView(R.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @OnClick(R.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTvTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollFragment(), SINGLETASK);
        } else {
            //检查用户是否登录了APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_launcher;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTvTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
