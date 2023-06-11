package com.example.se_project_schedulemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        int hour = listAlarm.get(position).getAlarmActivation().getHours();
        int minute = listAlarm.get(position).getAlarmActivation().getMinutes();
        String alarmTitle = listAlarm.get(position).getAlarmTitle();
        String alarmTimeD = "Alarm : " + listAlarm.get(position).getAlarmActivation().getDate() +
                " " + getMonth(listAlarm.get(position).getAlarmActivation().getMonth()) +
                " " + listAlarm.get(position).getAlarmActivation().getYear() +
                " " + String.format("%02d:%02d", hour, minute);
        String description = "Description : " + listAlarm.get(position).getAlarmDescription();

        // duration Begin
        int durBeginHrs = listAlarm.get(position).getScheduleStartTime().getHours();
        int durBeginMin = listAlarm.get(position).getScheduleStartTime().getMinutes();

        String durBegin = String.format("%02d:%02d", durBeginHrs, durBeginMin);

        // Duration End
        int durEndHrs = listAlarm.get(position).getScheduleEndTime().getHours();
        int durEndMin = listAlarm.get(position).getScheduleEndTime().getMinutes();

        String durEnd = String.format("%02d:%02d", durEndHrs, durEndMin);

        holder.tvClassName.setText(alarmTitle);
        holder.tvAlarmTime.setText(alarmTimeD);
        holder.tvDescription.setText(description);
        holder.tvSessDuration.setText("Duration  : " + durBegin
                                        + " - " + durEnd);

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

    public String getMonth (int monthIndex){

        String returnString = null;

        switch(monthIndex){
            case 0:
                returnString = "January";
                break;
            case 1:
                returnString = "February";
                break;
            case 2:
                returnString = "March";
                break;
            case 3:
                returnString = "April";
                break;
            case 4:
                returnString = "May";
                break;
            case 5:
                returnString = "June";
                break;
            case 6:
                returnString = "July";
                break;
            case 7:
                returnString = "August";
                break;
            case 8:
                returnString = "September";
                break;
            case 9:
                returnString = "October";
                break;
            case 10:
                returnString = "November";
                break;
            case 11:
                returnString = "December";
                break;

        }
        return returnString;
    }

}
