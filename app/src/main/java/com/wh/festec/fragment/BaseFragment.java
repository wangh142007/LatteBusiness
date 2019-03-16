package com.wh.festec.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wh.festec.activity.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

@SuppressWarnings("SpellCheckingInspection")
public abstract class BaseFragment extends SwipeBackFragment {

    public abstract Object setLayout();

    private Unbinder mUnbinder = null;

    public abstract void onBindView(@NonNull Bundle savedInstanceState , View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = null;

        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        }else if (setLayout() instanceof View){
            rootView = (View) setLayout();
        }else {
            throw new ClassCastException("setLayout() type must be int or View");
        }

        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this,rootView);
            onBindView(savedInstanceState ,rootView);
        }

        return rootView;
    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
