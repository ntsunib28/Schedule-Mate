package com.example.se_project_schedulemate.Alarm;

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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.se_project_schedulemate.R;

import java.util.Calendar;
import java.util.Date;

public class SetAlarm extends AppCompatActivity {
    private TimePicker timePicker;
    private Button saveAlarm;


    ImageView backBtn;
    TextView tvSession, tvClassName;
//    private EditText edit = (EditText) findViewById(R.id.alarmName);
//    public static String alarmName;
    private int hour, minutes;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm_activity);

        backBtn = findViewById(R.id.backBtn);

        tvClassName = findViewById(R.id.tvClassName);
        tvSession = findViewById(R.id.tvSession);
        tvClassName.setText(getIntent().getStringExtra("Title"));
        tvSession.setText(getIntent().getStringExtra("Description"));

        timePicker = findViewById(R.id.timePicker);
        saveAlarm = findViewById(R.id.saveAlarm);
//        alarmName = (String) edit.getText().toString();
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                minutes = minute;
            }
        });

        saveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetAlarm.this, "Set Alarm " + hour + ": "+ minutes, Toast.LENGTH_SHORT).show();
                setTimer();
                notification();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void notification() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "com/example/se_project_schedulemate/Alarm";
            String description = "Hey, Wake Up!!";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("Alarm Reminders", name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setTimer() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Date date = new Date();

        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();

        cal_now.setTime(date);
        cal_alarm.setTime(date);

        cal_alarm.set(Calendar.HOUR_OF_DAY, hour);
        cal_alarm.set(Calendar.MINUTE, minutes);
        cal_alarm.set(Calendar.SECOND, 0);

        if(cal_alarm.before((cal_now))){
            cal_alarm.add(Calendar.DATE, 1);
        }

        Intent i = new Intent(SetAlarm.this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(SetAlarm.this, 0, i, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(),pendingIntent);
    }
}