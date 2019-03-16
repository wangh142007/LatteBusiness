package com.wh.festec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wh.festec.database.DatabaseManager;
import com.wh.festec.net.interceptor.DebugInterceptor;
import com.wh.festec.app.Latte;

import okhttp3.OkHttpClient;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("test",R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);

        //  一般使用默认初始化配置足够使用
        Stetho.initializeWithDefaults(this);
        // 如果需要查看网络请求相关信息(以使用okhttp3为例)
        initOkHttp();
    }


    private void initOkHttp() {
        OkHttpClient okHttpClient =  new OkHttpClient()
                .newBuilder()
                .addNetworkInterceptor(new StethoInterceptor()) // 这里添加一个拦截器即可
                .build();
    }
}
