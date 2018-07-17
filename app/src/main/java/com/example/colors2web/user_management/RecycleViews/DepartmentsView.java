package com.example.colors2web.user_management.RecycleViews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.colors2web.user_management.Activities.DepartmentActivity;
import com.example.colors2web.user_management.Activities.UserActivity;
import com.example.colors2web.user_management.POJO.DepartmentResponse;
import com.example.colors2web.user_management.POJO.Departments;
import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.RecycleUtils.DepartmentsAdapter;
import com.example.colors2web.user_management.RecycleUtils.MyDividerItemDecoration;
import com.example.colors2web.user_management.RecycleUtils.RecyclerTouchListener;
import com.example.colors2web.user_management.api.APIClient;
import com.example.colors2web.user_management.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentsView extends AppCompatActivity {

    RecyclerView mrecyclerView;
    DepartmentsAdapter radapter;
    APIInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_department);

        final List<Departments> depList = new ArrayList<>();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        mrecyclerView = findViewById(R.id.recycler_view);
        radapter = new DepartmentsAdapter(depList);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mLayoutManager);

        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(DepartmentsView.this, LinearLayoutManager.HORIZONTAL, 16));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mrecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(DepartmentsView.this,DepartmentActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);

                Log.d("P",String.valueOf(position));
                Toast.makeText(getApplicationContext(), "Item " + position + " is selected!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
                Intent intent = new Intent(DepartmentsView.this,DepartmentActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Long Clicked on Item " + position, Toast.LENGTH_SHORT).show();

            }
        }));

//        TODO:RecyclerView Customization
        //TODO:Adapter Like a Cards

        mrecyclerView.setAdapter(radapter);

        loadAdapter();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_employee:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Toast.makeText(DepartmentsView.this, "Eployee is Selected", Toast.LENGTH_SHORT).show();
                jump1();

                return true;

            case R.id.menu_department:
                Toast.makeText(DepartmentsView.this, "Department is Selected", Toast.LENGTH_SHORT).show();
                jump2();
                return true;

            case R.id.menu_tasks:
                Toast.makeText(DepartmentsView.this, "Tasks is Selected", Toast.LENGTH_SHORT).show();
                jump3();
                return true;

            case R.id.menu_users:
                Toast.makeText(DepartmentsView.this, "Users is Selected", Toast.LENGTH_SHORT).show();
                jump4();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void jump4() {
        Intent l = new Intent(DepartmentsView.this,UserActivity.class);
        startActivity(l);
    }

    private void jump3() {
        Intent k = new Intent(DepartmentsView.this,TaskView.class);
        startActivity(k);
    }

    private void jump2() {
        Intent j = new Intent(DepartmentsView.this,DepartmentsView.class);
        startActivity(j);
    }

    private void jump1() {
        Intent i = new Intent(DepartmentsView.this,EmployeeView.class);
        startActivity(i);
    }

    private void loadAdapter() {

        final List<Departments> deprec = new ArrayList<>();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = preferences.getString("Token", "");

        Call<DepartmentResponse> call = apiInterface.getdepartments("application/json", "Bearer" + " " + token);
        call.enqueue(new Callback<DepartmentResponse>() {

            @Override
            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {

                DepartmentResponse dep = response.body();
                List<Departments> deps = dep.getData();

                for (int i = 0; i < deps.size(); i++) {

                    Departments dis = new Departments();

                    int id = deps.get(i).getId();
                    String name = deps.get(i).getName();

                    dis.setId(id);
                    dis.setName("Name:" + name);

                    deprec.add(dis); // must be the object of empty list initiated
                }
                radapter.updateAnswers(deprec);//adapter's content is updated and update function is called;
                //send parameters according to urs adapter view setup.
            }

            @Override
            public void onFailure(Call<DepartmentResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
            }

        });

    }
}
