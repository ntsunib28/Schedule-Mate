package com.example.se_project_schedulemate.Alarm;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.se_project_schedulemate.R;

import java.util.Calendar;
import java.util.Date;

public class SetAlarm extends AppCompatActivity {

    TimePicker timePicker;
    Button saveAlarm;
    private int jam, menit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm_activity);

        ImageView backBtn;
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        timePicker = findViewById(R.id.timePicker);
        saveAlarm = findViewById(R.id.saveAlarm);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                jam = hourOfDay;
                menit = minute;
            }
        });

        saveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetAlarm.this, "Set Alarm " + jam + " : " + menit, Toast.LENGTH_SHORT).show();
                setTimer();
                notification();
            }
        });
    }

    private void notification() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Schedule Mate Reminder";
            String description = "Hey, your class is about to start!";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel  = new NotificationChannel("Notify", name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setTimer() {
        AlarmManager alarmManager  = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Date date = new Date();

        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();

        cal_now.setTime(date);
        cal_alarm.setTime(date);

        cal_alarm.set(Calendar.HOUR_OF_DAY, jam);
        cal_alarm.set(Calendar.MINUTE, menit);
        cal_alarm.set(Calendar.SECOND, 0);

        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE, 1);
        }

        Intent i = new Intent(SetAlarm.this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(SetAlarm.this, 0, i, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(),pendingIntent);
    }
}