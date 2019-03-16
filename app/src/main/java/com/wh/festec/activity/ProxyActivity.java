package com.wh.festec.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;

import com.wh.festec.R;
import com.wh.festec.fragment.LatteFragment;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteFragment setRootFragment();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer( Bundle savedInstanceState){
        @SuppressLint("RestrictedApi")
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container ,setRootFragment());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
