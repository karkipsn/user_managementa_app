package com.example.colors2web.user_management.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.user_management.POJO.Departments;
import com.example.colors2web.user_management.POJO.DepartmentResponse;
import com.example.colors2web.user_management.POJO.EmpPostResponse;
import com.example.colors2web.user_management.POJO.EmployeeResponse;
import com.example.colors2web.user_management.POJO.Employees;
import com.example.colors2web.user_management.POJO.TaskPostR;
import com.example.colors2web.user_management.POJO.TaskResponse;
import com.example.colors2web.user_management.POJO.Tasks;
import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.RecycleViews.TaskView;
import com.example.colors2web.user_management.api.APIClient;
import com.example.colors2web.user_management.api.APIInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskActivity extends AppCompatActivity {

    @BindView(R.id.btngetTasks)
    Button gTask;

    @BindView(R.id.btnpostTasks)
    Button pTask;

    @BindView(R.id.btnRecTasks)
    Button rTask;

    @BindView(R.id.tv_task_display)
    TextView tdisplay;

    @BindView(R.id.tv_tempid)
    EditText empid;
    @BindView(R.id.tv_ttitle)
    EditText title;
    @BindView(R.id.tv_tdescription)
    EditText description;
    @BindView(R.id.tv_tdeadline)
    EditText deadline;
    @BindView(R.id.tv_tattachment)
    EditText attachment;

    APIInterface apiInterface;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String token = preferences.getString("Token", "");
        ButterKnife.bind(this);

        rTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TaskActivity.this, TaskView.class);
                startActivity(i);
            }
        });

        gTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<TaskResponse> call = apiInterface.gettasks("application/json", "Bearer" + " " + token);

                // make an asynchronous call
                call.enqueue(new Callback<TaskResponse>() {

                    @Override
                    public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                        TaskResponse dep = response.body();
                        Log.d("employee:", dep.toString());
                        List<Tasks> depList = dep.getData();
                        Log.d("index", String.valueOf(depList.size()));
                        String displayResponse = "";
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TaskActivity.this);

                        for (Tasks dep1 : depList) {
                            // for (int i = 0; i < depList.size(); i++) {
//
                            displayResponse += "id: " + dep1.getId() + "\n" + "Employee_Id: " + dep1.getEmployeeId() + "\n" +
                                    "Name: " + dep1.getEmployeeName() + "\n" + "Title: " + dep1.getTitle() + "\n" +
                                    "Description: " + dep1.getDescription() + "\n" +
                                    "Deadline: " + dep1.getDeadline() + "\n" +
                                    "Attachment_url: " + dep1.getAttachment() + "\n" + "\n";

                            tdisplay.setText(displayResponse);
                        }
                    }
                    // }

                    @Override
                    public void onFailure(Call<TaskResponse> call, Throwable t) {
                        call.cancel();
                        Log.e("response-failure", call.toString());
                        Toast.makeText(TaskActivity.this, "Error:" + t.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();

                    }

                });
            }
        });

        pTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title1, description1, deadline1, attachment1;
                Integer empid1 = empid.getText().length();
                title1 = title.getText().toString();
                description1 = description.getText().toString();
                deadline1 = deadline.getText().toString();
                attachment1 = attachment.getText().toString();

                emppost(empid1, title1, description1, deadline1, attachment1);


            }
        });
    }

    private void emppost(Integer empid1, String title1, String description1, String deadline1, String attachment1) {
        Tasks tasks = new Tasks(empid1, title1, description1, deadline1, attachment1);

        Call<TaskPostR> call = apiInterface.postTasks(tasks);

        call.enqueue(new Callback<TaskPostR>() {

            String displayResponse = "";

            @Override
            public void onResponse(Call<TaskPostR> call, Response<TaskPostR> response) {

                TaskPostR dep = response.body();
                Log.d("employee:", dep.toString());

                displayResponse = "Success: " + response.body().getSuccess() + "\n" + "Message: " + dep.getMessage() + "\n" +
                        "data: " + "\n" + "id:" + dep.getData().getId() + "\n" + "name:" + dep.getData().getEmployeeName() + "\n"
                        + "Employee_Id:" + dep.getData().getEmployeeId() + "\n" + "Title:" + dep.getData().getTitle() + "\n"
                        + "Description:" + dep.getData().getDescription() + "\n" + "Deadline:" + dep.getData().getDeadline() + "\n"
                        + "Attachment:" + dep.getData().getAttachment() + "\n" + "created_at:" + dep.getData().getCreatedAt() + "\n"
                        + "updated_at:" + dep.getData().getUpdatedAt() + "\n";

                tdisplay.setText(displayResponse);
            }

            @Override
            public void onFailure(Call<TaskPostR> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
                Log.e("response-failure", t.toString());
            }

        });
    }
}
