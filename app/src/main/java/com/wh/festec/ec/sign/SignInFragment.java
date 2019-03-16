package com.wh.festec.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.wh.festec.R;
import com.wh.festec.fragment.LatteFragment;
import com.wh.festec.net.RestClient;
import com.wh.festec.net.callback.ISuccess;
import com.wh.festec.utils.log.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 41926 on 2019/3/12 21:56.
 */
public class SignInFragment extends LatteFragment {

    @BindView(R.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R.id.edit_sign_in_password)
    TextInputEditText mPassword = null;


    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpFragment());
    }


    @OnClick(R.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()) {
            RestClient.Builder()
                .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("TAG", "onSuccess: " + response);
                        SignHandler.onSignIn(response, mISignListener);
                    }
                })
                .build()
                .post();
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.fragment_sign_in;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }
}
