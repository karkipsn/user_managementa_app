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

import com.example.colors2web.user_management.POJO.DepartmentPostR;
import com.example.colors2web.user_management.POJO.Departments;
import com.example.colors2web.user_management.POJO.DepartmentResponse;
import com.example.colors2web.user_management.POJO.EmpPostResponse;
import com.example.colors2web.user_management.POJO.Employees;
import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.api.APIClient;
import com.example.colors2web.user_management.api.APIInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class DepartmentActivity extends AppCompatActivity {

    @BindView(R.id.btnputDepartments)
    Button pDepart;

    @BindView(R.id.btndeleteDepartments)
    Button dDepart;

    @BindView(R.id.tv_depaertment_display)
    TextView display;

    APIInterface apiInterface;
    Integer depid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final int v1 = intent.getExtras().getInt("position");
        Log.d("Wat coms dep postion",String.valueOf(v1));

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String token = preferences.getString("Token", "");

        Call<DepartmentResponse> call = apiInterface.getdepartments("application/json", "Bearer" + " " + token);
        call.enqueue(new Callback<DepartmentResponse>() {

            @Override
            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
                DepartmentResponse dep = response.body();
                Log.d("user:", dep.toString());
                List<Departments> depList = dep.getData();
                Log.d("index", String.valueOf(depList.size()));
                String displayResponse = "";

               // for (Departments dep1 : depList) {
                    for (int i = 0; i < depList.size(); i++) {
                        if (i == v1) {

                            displayResponse = "id: " + depList.get(i).getId() + "\n" + "Name: " + depList.get(i).getName() + "\n";

                            depid = depList.get(i).getId();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(DepartmentActivity.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("did", depid);
                            editor.apply();

                            display.setText(displayResponse);
                        }
                    }

                }
           // }


            @Override
            public void onFailure(Call<DepartmentResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
            }

        });


        dDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<DepartmentPostR> call = apiInterface.deleteDepartments("application/json", "Bearer" + " " + token, depid);

                // make an asynchronous call
                call.enqueue(new Callback<DepartmentPostR>() {

                    @Override
                    public void onResponse(Call<DepartmentPostR> call, Response<DepartmentPostR> response) {

                        DepartmentPostR dep = response.body();
                        Log.d("department:", dep.toString());
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        display.setText(response.body().getMessage());
                    }


                    @Override
                    public void onFailure(Call<DepartmentPostR> call, Throwable t) {
                        call.cancel();
                        Log.e("response-failure", call.toString());
                        Toast.makeText(getApplicationContext(), "Network Failure", Toast.LENGTH_SHORT).show();

                    }

                });
            }
        });

        pDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int v2 = v1;
                Log.d("v2", String.valueOf(v2));
                Intent i = new Intent(DepartmentActivity.this, DepartmentPut.class);
                i.putExtra("v1", v2);
                startActivity(i);

            }
        });
    }


}
