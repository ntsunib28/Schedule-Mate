package com.example.se_project_schedulemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se_project_schedulemate.MyInterface;

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
        holder.tvAssignmentName.setText(assignments.get(position).getAssignmentName());
        holder.tvNotification.setText("Notification: " + assignments.get(position).getNotification());
        holder.tvSession.setText("Session: " + assignments.get(position).getAssignmentName());
        holder.tvLecturer.setText(assignments.get(position).getLecturer());
        holder.tvDeadline.setText("DEADLINE: " + assignments.get(position).getDeadline());

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
        ImageView ivBuilding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAssignmentName = itemView.findViewById(R.id.tvAsignmentName);
            tvNotification = itemView.findViewById(R.id.tvNotification);
            tvSession = itemView.findViewById(R.id.tvSession);
            tvLecturer = itemView.findViewById(R.id.tvLecturer);
            tvDeadline = itemView.findViewById(R.id.tvDeadline);


        }
    }
}