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

import com.example.colors2web.user_management.Activities.UserActivity;
import com.example.colors2web.user_management.POJO.EmployeeResponse;
import com.example.colors2web.user_management.POJO.Employees;
import com.example.colors2web.user_management.POJO.TaskResponse;
import com.example.colors2web.user_management.POJO.Tasks;
import com.example.colors2web.user_management.POJO.User;
import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.RecycleUtils.MyDividerItemDecoration;
import com.example.colors2web.user_management.RecycleUtils.RecyclerTouchListener;
import com.example.colors2web.user_management.RecycleUtils.TaskAdapter;
import com.example.colors2web.user_management.api.APIClient;
import com.example.colors2web.user_management.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskView extends AppCompatActivity {

    RecyclerView mrecycleview;
    APIInterface apiInterface;
    TaskAdapter tadapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_department);

        List<Tasks> taskList = new ArrayList<>();

        mrecycleview = findViewById(R.id.recycler_view);
        tadapter = new TaskAdapter(taskList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mrecycleview.setHasFixedSize(true);
        mrecycleview.setLayoutManager(mLayoutManager);

        mrecycleview.addItemDecoration(new MyDividerItemDecoration(TaskView.this, LinearLayoutManager.HORIZONTAL, 16));
        mrecycleview.setItemAnimator(new DefaultItemAnimator());
        mrecycleview.addOnItemTouchListener(new RecyclerTouchListener(this, mrecycleview, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Intent intent = new Intent(DepartmentsView.this,DepartmentDetails.class);
//                intent.putExtra("position",position);
//                startActivity(intent);
//
//                Log.d("P",String.valueOf(position));
                Toast.makeText(getApplicationContext(), "Item " + position + " is selected!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
//                Intent intent = new Intent(DepartmentsView.this,DepartmentDetails.class);
//                intent.putExtra("position",position);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Long Clicked on Item " + position, Toast.LENGTH_SHORT).show();
            }
        }));

        mrecycleview.setAdapter(tadapter);
        loadTasks();
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
                Toast.makeText(TaskView.this, "Eployee is Selected", Toast.LENGTH_SHORT).show();
                jump1();

                return true;

            case R.id.menu_department:
                Toast.makeText(TaskView.this, "Department is Selected", Toast.LENGTH_SHORT).show();
                jump2();
                return true;

            case R.id.menu_tasks:
                Toast.makeText(TaskView.this, "Tasks is Selected", Toast.LENGTH_SHORT).show();
                jump3();
                return true;

            case R.id.menu_users:
                Toast.makeText(TaskView.this, "Users is Selected", Toast.LENGTH_SHORT).show();
                jump4();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }}

    private void jump4() {
        Intent l = new Intent(TaskView.this,UserActivity.class);
        startActivity(l);
    }

    private void jump3() {
        Intent k = new Intent(TaskView.this,TaskView.class);
        startActivity(k);
    }

    private void jump2() {
        Intent j = new Intent(TaskView.this,DepartmentsView.class);
        startActivity(j);
    }

    private void jump1() {
        Intent i = new Intent(TaskView.this,EmployeeView.class);
        startActivity(i);
    }

    private void loadTasks() {
        final List<Tasks> emplist = new ArrayList<>();
        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = preferences.getString("Token", "");

        Call<TaskResponse> call = apiInterface.gettasks("application/json", "Bearer" + " " + token);
        call.enqueue(new Callback<TaskResponse>() {

            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {

                TaskResponse emp = response.body();

                List<Tasks> empz= emp.getData();

                for (int i = 0; i < empz.size(); i++) {

                    Tasks dis = new Tasks();

                    int id = empz.get(i).getId();
                    int tid = empz.get(i).getEmployeeId();
                    String name = empz.get(i).getEmployeeName();
                    String ded =empz.get(i).getDeadline();
                    String att = empz.get(i).getAttachment();
                    String des = empz.get(i).getDescription();

                    dis.setId(id);
                    dis.setEmployeeId( tid);
                    dis.setEmployeeName("Name: "+ name);
                    dis.setDescription("Description:"+des);
                    dis.setDeadline("DeadLine:"+ded);
                    dis.setAttachment("Attachment:"+ att);

                    emplist.add(dis); // must be the object of empty list initiated
                }
                tadapter.updateAnswers(emplist);//adapter's content is updated and update function is called;
                //send parameters according to urs adapter view setup.
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
                Log.e("Error", t.toString());
            }

        });

    }
}
