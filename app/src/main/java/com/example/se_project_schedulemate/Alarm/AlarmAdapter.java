package com.example.se_project_schedulemate.Alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se_project_schedulemate.MyInterface;
import com.example.se_project_schedulemate.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder>{

    Vector<Alarm> listAlarm;
    Context context;

    MyInterface clickInterface;


    public AlarmAdapter(Context context, MyInterface clickInterface) {
        this.context = context;
        this.clickInterface = clickInterface;
    }

    public void setAlarmAdapterItem(Vector<Alarm> listAlarm){
        this.listAlarm = listAlarm;
    }


    @NonNull
    @Override
    public AlarmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_alarm_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmAdapter.ViewHolder holder, int position) {

        String alarmTitle = listAlarm.get(position).getAlarmTitle();
        String description = "Description : " + listAlarm.get(position).getAlarmDescription();

        // Set Alarm time.
        // ini kalo lu paham cara ngurangin waktunya berdasarkan settingan silakan karna gw gapaham
        DateFormat dtf = new SimpleDateFormat("dd MMMM yyyy, HH:mm z");
        Date _alarmTimeD = new Date(listAlarm.get(position).getAlarmActivation().getTime());
        String alarmTimeD = "Alarm : " + dtf.format(_alarmTimeD);

        // duration Begin
        DateFormat startDurFormat = new SimpleDateFormat("HH:mm");
        Date _durStart = new Date(listAlarm.get(position).getScheduleStartTime().getTime());
        String durStart = startDurFormat.format(_durStart);

        // Duration End
        DateFormat endDurFormat = new SimpleDateFormat("HH:mm");
        Date _durEnd = new Date(listAlarm.get(position).getScheduleEndTime().getTime());
        String durEnd = startDurFormat.format(_durEnd);

        holder.tvClassName.setText(alarmTitle);
        holder.tvAlarmTime.setText(alarmTimeD);
        holder.tvDescription.setText(description);
        holder.tvSessDuration.setText("Duration  : " + durStart + " - " + durEnd );

        holder.itemView.findViewById(R.id.cv_alarmCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.onClick(holder.getBindingAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listAlarm.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvClassName, tvAlarmTime, tvDescription, tvSessDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClassName = (TextView) itemView.findViewById(R.id.tvClassName);
            tvAlarmTime = (TextView) itemView.findViewById(R.id.tvAlarmTime);
            tvDescription = itemView.findViewById(R.id.tvDescText);
            tvSessDuration = itemView.findViewById(R.id.tvSessDuration);

        }

    }


}
