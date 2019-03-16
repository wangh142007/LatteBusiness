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

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 41926 on 2019/3/12 21:23.
 */
public class SignUpFragment extends LatteFragment {

    @BindView(R.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    @OnClick(R.id.tv_link_sign_in)
    void onClickLink() {
        getSupportDelegate().start(new SignInFragment());
    }

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }


    @OnClick(R.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.Builder()
                    .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.d("TAG", "onSuccess: " + response);
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }
}
