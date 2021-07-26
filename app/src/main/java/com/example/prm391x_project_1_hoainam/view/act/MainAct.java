package com.example.prm391x_project_1_hoainam.view.act;

import android.view.View;
import android.widget.Toast;

import com.example.prm391x_project_1_hoainam.OnActionCallBack;
import com.example.prm391x_project_1_hoainam.R;
import com.example.prm391x_project_1_hoainam.databinding.ActivityMainBinding;
import com.example.prm391x_project_1_hoainam.view.fragment.M000MenuFragment;
import com.example.prm391x_project_1_hoainam.view.fragment.M001SMSFragment;
import com.example.prm391x_project_1_hoainam.view.fragment.M002PhoneFragment;

public class MainAct extends BaseAct<ActivityMainBinding> implements OnActionCallBack {
    private M000MenuFragment m000MenuFragment;

    @Override
    protected ActivityMainBinding initBinding(View rootView) {
        return ActivityMainBinding.bind(rootView);
    }

    @Override
    protected void initViews() {
        m000MenuFragment = new M000MenuFragment();
        m000MenuFragment.setCallBack(this);
        showFragment(R.layout.frg_m001_sms,m000MenuFragment,false );
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void callBack(String key, Object data) {
        switch (key) {
            case M000MenuFragment.KEY_SHOW_SMS:
                M001SMSFragment m001SMSFragment = new M001SMSFragment();
                showFragment(R.layout.frg_m001_sms,m001SMSFragment,true );
                m001SMSFragment.setCallBack(this);
                break;
            case M000MenuFragment.KEY_SHOW_PHONE:
                M002PhoneFragment m002PhoneFragment = new M002PhoneFragment();
                showFragment(R.layout.frg_m001_sms,m002PhoneFragment,true );
                m002PhoneFragment.setCallBack(this);
                break;
            case M001SMSFragment.KEY_SMS_BACK_MENU:
            case M002PhoneFragment.KEY_PHONE_BACK_MENU:
                m000MenuFragment = new M000MenuFragment();
                m000MenuFragment.setCallBack(this);
                showFragment(R.layout.frg_m001_sms,m000MenuFragment,false );
                break;
        }
    }
}
