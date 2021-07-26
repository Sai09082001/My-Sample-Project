package com.example.prm391x_project_1_hoainam.view.act;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.example.prm391x_project_1_hoainam.OnActionCallBack;
import com.example.prm391x_project_1_hoainam.R;

public abstract class BaseAct<V extends ViewBinding> extends AppCompatActivity
        implements View.OnClickListener , OnActionCallBack {

    protected V binding;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(getLayoutId(), null);

        setContentView(rootView);
        binding = initBinding(rootView);
        initViews();
    }

    protected abstract V initBinding(View rootView);

    protected abstract void initViews();

    protected abstract int getLayoutId();

    @Override
    public void onClick(View v) {
        //do nothing
    }

    protected void showFragment(int layoutID, Fragment fragment, boolean addToBackStack){

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_view,fragment);
        if(addToBackStack){
            transaction.addToBackStack("add");
        }
        transaction.commit();
    }
}
