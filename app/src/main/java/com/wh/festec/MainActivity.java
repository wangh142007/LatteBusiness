package com.wh.festec;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Toast;

import com.wh.festec.activity.ProxyActivity;
import com.wh.festec.ec.launcher.LauncherFragment;
import com.wh.festec.ec.launcher.LauncherScrollFragment;
import com.wh.festec.ec.sign.ISignListener;
import com.wh.festec.ec.sign.SignInFragment;
import com.wh.festec.ec.sign.SignUpFragment;
import com.wh.festec.fragment.LatteFragment;
import com.wh.festec.ui.launcher.ILauncherListener;
import com.wh.festec.ui.launcher.OnLauncherFinishTag;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteFragment setRootFragment() {
        return new LauncherFragment();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录", Toast.LENGTH_LONG).show();
                startWithPop(new ExampleFragment());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                startWithPop(new SignInFragment());
                break;
            default:
                break;
        }
    }
}
