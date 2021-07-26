package com.example.prm391x_project_1_hoainam.view.fragment;

import android.view.View;

import com.example.prm391x_project_1_hoainam.OnActionCallBack;
import com.example.prm391x_project_1_hoainam.R;
import com.example.prm391x_project_1_hoainam.databinding.FrgM000MenuBinding;
import com.example.prm391x_project_1_hoainam.view.model.M000MenuViewModel;

public class M000MenuFragment extends BaseFragment<FrgM000MenuBinding, M000MenuViewModel>{
    public static final String KEY_SHOW_PHONE = "KEY_SHOW_PHONE";
    public static final String KEY_SHOW_SMS = "KEY_SHOW_SMS";

    @Override
    protected FrgM000MenuBinding initBinding(View mRootView) {
        return FrgM000MenuBinding.bind(mRootView);
    }

    @Override
    protected Class<M000MenuViewModel> getViewModelClass() {
        return M000MenuViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m000_menu;
    }

    @Override
    protected void initViews() {
       binding.frameMessage.setOnClickListener(this);
       binding.framePhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.frame_message){
           callBack.callBack(KEY_SHOW_SMS,null);
        }else if(v.getId() == R.id.frame_phone){
            callBack.callBack(KEY_SHOW_PHONE,null);
        }
    }
}
