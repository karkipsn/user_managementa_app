package com.example.colors2web.user_management.RecycleUtils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.colors2web.user_management.POJO.Departments;
import com.example.colors2web.user_management.R;

import java.util.List;

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.MyViewHolder> {

    private List<Departments> deptList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,department;

        public MyViewHolder (View itemView) {
            super(itemView);
            id =  itemView.findViewById(R.id.tv_dd_id);
            department =  itemView.findViewById(R.id.tv_dd_name);
        }
    }

    public DepartmentsAdapter(List<Departments> deptList) {

        this.deptList = deptList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lists, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Departments dept = deptList.get(position);
        holder.id.setText(String.valueOf(dept.getId()));
        holder.department.setText(dept.getName());

    }

    @Override
    public int getItemCount() {
        return deptList.size();
    }

    public void updateAnswers(List<Departments> departments)
    {
        deptList =  departments;
        notifyDataSetChanged();
    }


}
