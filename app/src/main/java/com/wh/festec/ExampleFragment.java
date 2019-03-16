package com.wh.festec;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wh.festec.fragment.LatteFragment;
import com.wh.festec.net.RestClient;
import com.wh.festec.net.callback.IError;
import com.wh.festec.net.callback.IFailure;
import com.wh.festec.net.callback.ISuccess;

public class ExampleFragment extends LatteFragment {
    private String TAG ="ExampleFragment";

    @Override
    public Object setLayout() {
        return R.layout.fragment_example;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.Builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "onSuccess: "+response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d(TAG, "onFailure: ");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d(TAG, "onError: ");
                    }
                })
                .build()
                .get();
    }
}
