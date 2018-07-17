package com.example.colors2web.user_management.RecycleUtils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.user_management.POJO.Employees;
import com.example.colors2web.user_management.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmpViewHolder>{
    List<Employees> empList ;

    @NonNull
    @Override
    public EmployeeAdapter.EmpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lists1, parent, false);

        return new EmpViewHolder(view);
    }

    public EmployeeAdapter(List<Employees> empList) {
        this.empList = empList;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.EmpViewHolder holder, int position) {
      Employees employees = empList.get(position);

      holder.emp_id.setText(String.valueOf(employees.getmEmployee_id()));
      holder.department.setText(employees.getDepartmentName());
      holder.name.setText(employees.getName());
//      holder.address.setText(employees.getAdd());
//      holder.birth.setText(employees.getBirthdate());
//      holder.hired.setText(employees.getDateHired());

    }

    @Override
    public int getItemCount() {
        return empList.size();
    }

    public class EmpViewHolder extends RecyclerView.ViewHolder {

        TextView emp_id,department,name,address,birth,hired;

        public EmpViewHolder(View itemView) {
            super(itemView);
            emp_id =  itemView.findViewById(R.id.tv_dd_id);
            name =  itemView.findViewById(R.id.tv_dd_name);
            department =  itemView.findViewById(R.id.tv_dd_name1);
//            address =  itemView.findViewById(R.id.tv_dd_name2);
//            birth =  itemView.findViewById(R.id.tv_dd_name3);
//            hired =  itemView.findViewById(R.id.tv_dd_name4);
        }
    }


    public void updateAnswers(List<Employees> emps)
    {
        empList = emps;
        notifyDataSetChanged();
    }

}
