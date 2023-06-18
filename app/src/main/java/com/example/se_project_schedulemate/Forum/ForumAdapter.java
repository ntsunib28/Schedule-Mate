package com.example.se_project_schedulemate.Forum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se_project_schedulemate.MyInterface;
import com.example.se_project_schedulemate.R;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        Timestamp deadline = forumItems.get(position).getForumDeadline();

        holder.tvForumName.setText(forumItems.get(position).getForumTitle());
        holder.tvSessionName.setText(forumItems.get(position).getForumSession());
        holder.tvLecturerName.setText(forumItems.get(position).getForumLecturer());

        // Begin Formatting tanggal
        DateFormat deadlineFormat = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
        Date _deadlineTime = new Date(deadline.getTime());
        String deadlineTime = deadlineFormat.format(_deadlineTime);
        holder.tvDeadline.setText("DEADLINE  : " + deadlineTime);
        // End Formatting Tanggal dan set text.

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

}
