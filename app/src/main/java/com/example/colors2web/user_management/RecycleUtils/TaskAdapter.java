package com.example.colors2web.user_management.RecycleUtils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.user_management.POJO.Tasks;
import com.example.colors2web.user_management.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    List<Tasks> tasksList;

    public TaskAdapter(List<Tasks> tasksList) {
        this.tasksList = tasksList;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lists1,parent,false);
        return new TaskViewHolder(view );
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        Tasks tasks = tasksList.get(position);
        holder.emp_id.setText(String.valueOf(tasks.getId()));
        holder.name.setText(String.valueOf(tasks.getEmployeeId()));
        holder.department.setText(String.valueOf(tasks.getEmployeeName()));
        holder.address.setText(tasks.getDeadline());
        holder.birth.setText(tasks.getAttachment());
        holder.hired.setText(tasks.getDescription());

    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public void updateAnswers(List<Tasks> emplist) {
        tasksList =emplist;
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView emp_id,department,name,address,birth,hired;

        public TaskViewHolder(View itemView) {
            super(itemView);

            emp_id =  itemView.findViewById(R.id.tv_dd_id);
            name =  itemView.findViewById(R.id.tv_dd_name);
            department =  itemView.findViewById(R.id.tv_dd_name1);
            address =  itemView.findViewById(R.id.tv_dd_name2);
            birth =  itemView.findViewById(R.id.tv_dd_name3);
            hired =  itemView.findViewById(R.id.tv_dd_name4);
        }
    }
}
