package com.example.prm391x_project_1_hoainam.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.prm391x_project_1_hoainam.OnActionCallBack;

public abstract class BaseFragment<K extends ViewBinding, V extends ViewModel> extends Fragment
        implements View.OnClickListener {
    protected Context mContext;
    protected View mRootView;
    protected OnActionCallBack callBack;

    public void setCallBack(OnActionCallBack callBack) {
        this.callBack = callBack;
    }

    protected V mViewModel;
    protected K binding;

    public final <T extends View> T findViewById(int id) {
        return findViewById(id, null);
    }

    public final <T extends View> T findViewById(int id, View.OnClickListener event) {
        T v = mRootView.findViewById(id);
        if (v != null && event != null) {
            v.setOnClickListener(event);
        }
        return v;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(getLayoutId(), container, false);
        mViewModel = new ViewModelProvider(this).get(getViewModelClass());
        binding = initBinding(mRootView);

        initViews();
        return mRootView;
    }

    protected abstract K initBinding(View mRootView);

    protected abstract Class<V> getViewModelClass();

    protected abstract int getLayoutId();

    protected abstract void initViews();

    @Override
    public void onClick(View v) {
        //do nothing
    }

}
