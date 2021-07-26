package com.example.prm391x_project_1_hoainam.view.fragment;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.prm391x_project_1_hoainam.R;
import com.example.prm391x_project_1_hoainam.databinding.FrgM002PhoneBinding;
import com.example.prm391x_project_1_hoainam.view.act.MainAct;
import com.example.prm391x_project_1_hoainam.view.model.M002PhoneViewModel;

public class M002PhoneFragment extends BaseFragment<FrgM002PhoneBinding , M002PhoneViewModel> {
    public static final String KEY_PHONE_BACK_MENU = "KEY_PHONE_BACK_MENU";

    @Override
    protected FrgM002PhoneBinding initBinding(View mRootView) {
        return FrgM002PhoneBinding.bind(mRootView);
    }

    @Override
    protected Class<M002PhoneViewModel> getViewModelClass() {
        return M002PhoneViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m002_phone;
    }

    @Override
    protected void initViews() {
      binding.tvSetup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.tv_setup){
            if(getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.CALL_PHONE
                },101);
            }
            long time = 1000;
            if(binding.rbSecond.isChecked()){
                time= Integer.parseInt(binding.edtPhoneTime.getText().toString())*1000;
            }else if(binding.rbMinute.isChecked()){
                time = Integer.parseInt(binding.edtPhoneTime.getText().toString())*60*1000;
            }else {
                time = Integer.parseInt(binding.edtPhoneTime.getText().toString())*60*60*1000;
            }
            if(isPhone(binding.phoneSomeOne.getText().toString())){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        doCall(binding.phoneSomeOne.getText().toString());
                    }
                },time);
            }

        }

    }

    private void doCall(String phone) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+phone));
        startActivity(intent);
    }

    public boolean isPhone(String phone) {
        return phone.matches("^(09|03|07|08|05)\\d{8}$");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode==101) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(mContext, "Please allow permission for using it", Toast.LENGTH_LONG).show();
                callBack.callBack(KEY_PHONE_BACK_MENU, null);
            } else {
                return;
            }
        }
    }
}
