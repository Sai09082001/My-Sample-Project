package com.example.prm391x_project_1_hoainam.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.prm391x_project_1_hoainam.App;
import com.example.prm391x_project_1_hoainam.OnActionCallBack;
import com.example.prm391x_project_1_hoainam.R;
import com.example.prm391x_project_1_hoainam.databinding.FrgM001SmsBinding;
import com.example.prm391x_project_1_hoainam.view.act.MainAct;
import com.example.prm391x_project_1_hoainam.view.model.M001SMSViewModel;

import java.util.Date;


public class M001SMSFragment extends BaseFragment<FrgM001SmsBinding , M001SMSViewModel> {
    public static final String KEY_SMS_BACK_MENU = "KEY_SMS_BACK_MENU";

    @Override
    protected FrgM001SmsBinding initBinding(View mRootView) {
        return FrgM001SmsBinding.bind(mRootView);
    }

    @Override
    protected Class<M001SMSViewModel> getViewModelClass() {
        return M001SMSViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m001_sms;
    }

    @Override
    protected void initViews() {
      binding.tvSetup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_setup){
            if(getActivity().checkSelfPermission(
                    Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.RECEIVE_MMS
                },101);
            }
            sendSMS();
        }
    }

    private void sendSMS() {
        if(isPhone(binding.edtPhoneSender.getText().toString()) && binding.edtMessage.getText().toString() != null &&
                binding.rbHour.isChecked() || binding.rbMinute.isChecked() || binding.rbSecond.isChecked()){
            long time = 1000;
            if(binding.rbSecond.isChecked()){
                time= Integer.parseInt(binding.edtTime.getText().toString())*1000;
            }else if(binding.rbMinute.isChecked()){
                time = Integer.parseInt(binding.edtTime.getText().toString())*60*1000;
            }else {
                time = Integer.parseInt(binding.edtTime.getText().toString())*60*60*1000;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    String mobile=binding.edtPhoneSender.getText().toString();
                    String message=binding.edtMessage.getText().toString();

                    //Getting intent and PendingIntent instance
                    Intent intent=new Intent(mContext, MainAct.class);
                    PendingIntent pi= PendingIntent.getActivity(mContext, 0, intent,0);

                    SmsManager sms= SmsManager.getDefault();
                    sms.sendTextMessage(mobile, null, message, pi,null);

                    Toast.makeText(mContext, "Message Sent successfully!",
                            Toast.LENGTH_LONG).show();
                    sendNotification();
                }
            },time);
        }
    }

    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        Notification notification = new NotificationCompat.Builder(mContext, App.CHANNEL_ID)
                .setContentTitle(binding.edtPhoneSender.getText().toString())
                .setContentText(binding.edtMessage.getText().toString())
                .setSmallIcon(R.drawable.ic_message)
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.design_default_color_secondary))
                .build();
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.notify(getNotificationId(),notification);
        }
    }

    private int getNotificationId() {
        return (int) new Date().getTime();
    }

    public boolean isPhone(String phone) {
        return phone.matches("^(09|03|07|08|05)\\d{8}$");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == 101) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(mContext, "Please allow permission for using it", Toast.LENGTH_SHORT).show();
                callBack.callBack(KEY_SMS_BACK_MENU,null);
            } else {
                return;
            }
        }
    }
}
