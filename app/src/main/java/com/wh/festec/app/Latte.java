package com.wh.festec.app;

import android.content.Context;

public final class Latte {

    public static Configurator init(Context context){
        Configurator.getInstance().
                getLatteConfigs().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

//    public static HashMap<Object,Object> getConfiguration(){
//        return Configurator.getInstance().getLatteConfigs();
//    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext(){
        return (Context) getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

//    public static Handler getHandler() {
//        return getConfiguration(ConfigKeys.HANDLER);
//    }


}
