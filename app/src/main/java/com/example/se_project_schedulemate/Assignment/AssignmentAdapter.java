package com.example.se_project_schedulemate.Assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se_project_schedulemate.MyInterface;
import com.example.se_project_schedulemate.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {
    Context ctx;
    Vector<Assignment> assignments;

    MyInterface myInterface;

    public AssignmentAdapter(Context ctx, MyInterface myInterface) {
        this.ctx = ctx;
        this.myInterface = myInterface;
    }

    public void setAssignments(Vector<Assignment> assignments) {
        this.assignments = assignments;
    }

    @NonNull
    @Override
    public AssignmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.rv_asg_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // create format
        DateFormat notificationFormat = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
        DateFormat deadlineFormat = new SimpleDateFormat("dd MMMM yyyy - HH:mm");

        // Simpen dalam bentuk string
        String str_notification = notificationFormat.format(assignments.get(position).getNotification());
        String str_deadline = deadlineFormat.format(assignments.get(position).getDeadline());

        holder.tvAssignmentName.setText(assignments.get(position).getAssignmentName());
        holder.tvNotification.setText("Notification: " + str_notification);
        holder.tvSession.setText("Session: " + assignments.get(position).getSession());
        holder.tvLecturer.setText(assignments.get(position).getLecturer());
        holder.tvDeadline.setText("DEADLINE: " + str_deadline);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInterface.onClick(holder.getBindingAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //sesuaiin sama komponen yg ada di rv building xml
        TextView tvAssignmentName, tvNotification, tvSession,
                tvLecturer, tvDeadline;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAssignmentName = itemView.findViewById(R.id.tvAssignmentName);
            tvNotification = itemView.findViewById(R.id.tvNotification);
            tvSession = itemView.findViewById(R.id.tvSession);
            tvLecturer = itemView.findViewById(R.id.tvLecturer);
            tvDeadline = itemView.findViewById(R.id.tvDeadline);


        }
    }
}