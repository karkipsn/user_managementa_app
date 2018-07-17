package com.example.colors2web.user_management.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.RecycleViews.DepartmentsView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.btnuser)
    Button mUsers;

    @BindView(R.id.btndepartment)
    Button mDepartments;

    @BindView(R.id.btnemployee)
    Button mEmployees;

    @BindView(R.id.btntask)
    Button mTasks;

    @BindView(R.id.btnrecycle)
    Button mrecycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, UserActivity.class);
                startActivity(i);

            }
        });

        mDepartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, DepartmentActivity.class);
                startActivity(i);

            }
        });

        mEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, EmployeeActivity.class);
                startActivity(i);

            }
        });

        mTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, TaskActivity.class);
                startActivity(i);

            }
        });
        mrecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, DepartmentsView.class);
                startActivity(i);
            }
        });
    }
}
