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

import com.example.colors2web.user_management.Activities.EmployeeActivity;
import com.example.colors2web.user_management.Activities.EmployeePut;
import com.example.colors2web.user_management.Activities.UserActivity;
import com.example.colors2web.user_management.POJO.EmployeeResponse;
import com.example.colors2web.user_management.POJO.Employees;
import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.RecycleUtils.EmployeeAdapter;
import com.example.colors2web.user_management.RecycleUtils.MyDividerItemDecoration;
import com.example.colors2web.user_management.RecycleUtils.RecyclerTouchListener;
import com.example.colors2web.user_management.api.APIClient;
import com.example.colors2web.user_management.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.colors2web.user_management.R.layout.*;

public class EmployeeView extends AppCompatActivity {

    RecyclerView mrecycleview;
    APIInterface apiInterface;
    EmployeeAdapter radapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(recycle_department);

        List<Employees> empList = new ArrayList<>();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        mrecycleview = findViewById(R.id.recycler_view);
        radapter = new EmployeeAdapter(empList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mrecycleview.setHasFixedSize(true);
        mrecycleview.setLayoutManager(mLayoutManager);

        mrecycleview.addItemDecoration(new MyDividerItemDecoration(EmployeeView.this, LinearLayoutManager.HORIZONTAL, 16));
        mrecycleview.setItemAnimator(new DefaultItemAnimator());
        mrecycleview.addOnItemTouchListener(new RecyclerTouchListener(this, mrecycleview, new RecyclerTouchListener.ClickListener() {


            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(EmployeeView.this, EmployeeActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

                Log.d("P", String.valueOf(position));
                Toast.makeText(getApplicationContext(), "Item " + position + " is selected!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
                Intent intent = new Intent(EmployeeView.this, EmployeeActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Long Clicked on Item " + position, Toast.LENGTH_SHORT).show();
            }
        }));

        mrecycleview.setAdapter(radapter);
        loadEmployees();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_employee:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Toast.makeText(EmployeeView.this, "Eployee is Selected", Toast.LENGTH_SHORT).show();
                jump1();

                return true;

            case R.id.menu_department:
                Toast.makeText(EmployeeView.this, "Department is Selected", Toast.LENGTH_SHORT).show();
                jump2();
                return true;

            case R.id.menu_tasks:
                Toast.makeText(EmployeeView.this, "Tasks is Selected", Toast.LENGTH_SHORT).show();
                jump3();
                return true;

            case R.id.menu_users:
                Toast.makeText(EmployeeView.this, "Users is Selected", Toast.LENGTH_SHORT).show();
                jump4();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void jump4() {
        Intent l = new Intent(EmployeeView.this, UserActivity.class);
        startActivity(l);
    }

    private void jump3() {
        Intent k = new Intent(EmployeeView.this, TaskView.class);
        startActivity(k);
    }

    private void jump2() {
        Intent j = new Intent(EmployeeView.this, DepartmentsView.class);
        startActivity(j);
    }

    private void jump1() {
        Intent i = new Intent(EmployeeView.this, EmployeeView.class);
        startActivity(i);
    }

    private void loadEmployees() {
        final List<Employees> emplist = new ArrayList<>();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = preferences.getString("Token", "");

        Call<EmployeeResponse> call = apiInterface.getEmployees("application/json", "Bearer" + " " + token);
        call.enqueue(new Callback<EmployeeResponse>() {

            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {

                EmployeeResponse emp = response.body();

                List<Employees> empz = emp.getData();

                for (int i = 0; i < empz.size(); i++) {

                    Employees dis = new Employees();

                    int id = empz.get(i).getmEmployee_id();
                    String name = empz.get(i).getName();
                    String dep_name = empz.get(i).getDepartmentName();
                    String address = empz.get(i).getAdd();
                    String birth = empz.get(i).getBirthdate();
                    String hired = empz.get(i).getDateHired();

                    dis.setmEmployee_id(id);
                    dis.setName("Name:" + name);
                    dis.setDepartmentName("DePartment:" + dep_name);
                    dis.setAdd("Address:" + address);
                    dis.setBirthdate("DOB:" + birth);
                    dis.setDateHired("Hired:" + hired);

                    emplist.add(dis); // must be the object of empty list initiated
                }
                radapter.updateAnswers(emplist);//adapter's content is updated and update function is called;
                //send parameters according to urs adapter view setup.
            }

            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
                Log.e("Error", t.toString());
            }

        });

    }
}
