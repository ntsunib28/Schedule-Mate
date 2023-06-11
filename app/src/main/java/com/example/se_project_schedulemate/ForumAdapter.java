package com.example.se_project_schedulemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ViewHolder>{

    Vector<Forum> forumItems;
    Context context;
    MyInterface clickInterface;


    public ForumAdapter(Context context, MyInterface clickInterface){
        this.context = context;
        this.clickInterface = clickInterface;
    }

    public void setForumAdapterItems(Vector<Forum> items) {
        this.forumItems = items;
    }

    @NonNull
    @Override
    public ForumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_forum_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumAdapter.ViewHolder holder, int position) {

        Timestamp dedline = forumItems.get(position).getForumDeadline();

        holder.tvForumName.setText(forumItems.get(position).getForumTitle());
        holder.tvSessionName.setText(forumItems.get(position).getForumSession());
        holder.tvLecturerName.setText(forumItems.get(position).getForumLecturer());



        String deadlineTime = String.format("%d %s %d - %02d.%02d", dedline.getDate(), getMonth(dedline.getMonth()), dedline.getYear(),
                                    dedline.getHours(), dedline.getMinutes());

        holder.tvDeadline.setText("DEADLINE  : " + deadlineTime);

        holder.itemView.findViewById(R.id.cv_forumCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.onClick(holder.getBindingAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {

        return forumItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvForumName, tvSessionName, tvLecturerName, tvDeadline;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvForumName = itemView.findViewById(R.id.tvForumName);
            tvSessionName= itemView.findViewById(R.id.tvSessionName);
            tvLecturerName = itemView.findViewById(R.id.tvLecturerName);
            tvDeadline = itemView.findViewById(R.id.tvDeadline);
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
