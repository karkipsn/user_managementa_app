package com.example.colors2web.user_management.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.user_management.POJO.EmpPostResponse;
import com.example.colors2web.user_management.POJO.EmployeeResponse;
import com.example.colors2web.user_management.POJO.Employees;
import com.example.colors2web.user_management.POJO.TaskResponse;
import com.example.colors2web.user_management.POJO.Tasks;
import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.RecycleViews.EmployeeView;
import com.example.colors2web.user_management.api.APIClient;
import com.example.colors2web.user_management.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivity extends AppCompatActivity {

    @BindView(R.id.btnpatchtEmployees)
    Button pEmployees;

    @BindView(R.id.btndeleteEmp)
    Button dEmp;

    @BindView(R.id.btnETask)
    Button btask;

    @BindView(R.id.tv_employee_display)
    TextView display;

    @BindView(R.id.emp_task_display)
    TextView etdisplay;

    Integer empid;

    APIInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final int v1 = intent.getExtras().getInt("position");
        Log.d("Wat coms asemp postion",String.valueOf(v1));

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String token = preferences.getString("Token", "");

        Call<EmployeeResponse> call = apiInterface.getEmployees("application/json", "Bearer" + " " + token);
        // make an asynchronous call
        call.enqueue(new Callback<EmployeeResponse>() {
            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {

                EmployeeResponse dep = response.body();
                Log.d("employee:", dep.toString());

                List<Employees> depList = dep.getData();

                String displayResponse = "";

               // for (Employees dep1 : depList) {
                    for (int i = 0; i < depList.size(); i++) {
                        if (i == v1) {

                            displayResponse = "Id: " + depList.get(i).getId() + "\n" + "Emp_Id: " + depList.get(i).getmEmployee_id() + "\n" + "Name: " + depList.get(i).getName() + "\n" +
                                    "Address: " + depList.get(i).getAdd() + "\n" + "Birthdate: " + depList.get(i).getBirthdate() + "\n" +
                                    "Date_hired: " + depList.get(i).getDateHired() + "\n" +
                                    "Department_Nmae: " + depList.get(i).getDepartmentName() + "\n" + "\n";

                            empid = depList.get(i).getmEmployee_id();
                            display.setText(displayResponse);
                        }

                    }
                }


            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
            }

        });
        //For employee Task

//        final List<Tasks> emplist = new ArrayList<>();
        btask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadtask(empid);
            }
        });
        pEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int v2 = v1;
                Intent i = new Intent(EmployeeActivity.this, EmployeePut.class);
                i.putExtra("id", v2);
                startActivity(i);
            }
        });

        //delete api calling w.r.t to delete button
        dEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeActivity.this);
                builder.setTitle("Delete ");
                builder.setMessage("Do you want to delete this employee ??");
                builder.setPositiveButton("Yes.Proceed!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        delete();
                    }
                });
                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }

            public void delete() {

                Call<EmpPostResponse> call = apiInterface.deleteEmployees("application/json", "Bearer" + " " + token, empid);

                // make an asynchronous call
                call.enqueue(new Callback<EmpPostResponse>() {

                    @Override
                    public void onResponse(Call<EmpPostResponse> call, Response<EmpPostResponse> response) {

                        EmpPostResponse dep = response.body();
                        Log.d("employee:", dep.toString());
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        display.setText(response.body().getMessage());
                    }


                    @Override
                    public void onFailure(Call<EmpPostResponse> call, Throwable t) {
                        call.cancel();
                        Log.e("response-failure", call.toString());
                        Toast.makeText(getApplicationContext(), "Network Filure", Toast.LENGTH_SHORT).show();

                    }

                });

            }
        });
    }

    private void loadtask(Integer empid) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String token = preferences.getString("Token", "");

        Call<TaskResponse> call1 = apiInterface.getemptasks("application/json", "Bearer" + " " + token, empid);
        call1.enqueue(new Callback<TaskResponse>() {

            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                if (response.isSuccessful()) {

                    TaskResponse emp = response.body();

                    List<Tasks> empz = emp.getData();
                    String displayResponse1 = "";

                    for (Tasks dep1 : empz) {

                        displayResponse1 = "Title: " + dep1.getTitle() + "\n" +
                                "Attachment: " + dep1.getAttachment() + "\n" + "Description: " + dep1.getDescription() + "\n" +
                                "Deadline: " + dep1.getDeadline() + "\n";

                        etdisplay.setText(displayResponse1);
                        Toast.makeText(getApplicationContext(), "Tasks Successfully Retrived", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "NO Tasks Remaining for this Employee", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
                Log.e("Error", t.toString());
                Toast.makeText(getApplicationContext(), "Network Failure", Toast.LENGTH_SHORT).show();
            }

        });

    }
    //TODO: DROPDOWN ON THE DEPARTMENT_ID
    //TODO: TASKS to be displayed
    //TODO: EMPLOYEE DISPLAY ON TEXTVIEW
    //TODO: Alert Messages on Delete Option.
//    TODO: Issue of position/ Displaying last only


}
