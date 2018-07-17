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
import com.example.colors2web.user_management.POJO.DepartmentResponse;
import com.example.colors2web.user_management.POJO.Departments;
import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.api.APIClient;
import com.example.colors2web.user_management.api.APIInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentPut extends AppCompatActivity {

    @BindView(R.id.tv_dname)
    EditText dname;

    @BindView(R.id.dputdisplay)
    TextView display;

    @BindView(R.id.dis_did)
    TextView dis_did;

    @BindView(R.id.btnupdateEmployees)
    Button mupdate;

    Integer depid;
//ArrayList<Integer> spin;
    APIInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.put_departments);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final int v1 = intent.getExtras().getInt("v1");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String token = preferences.getString("Token", "");

        apiInterface = APIClient.getClient().create(APIInterface.class);


        Call<DepartmentResponse> call = apiInterface.getdepartments("application/json", "Bearer" + " " + token);
        call.enqueue(new Callback<DepartmentResponse>() {

            @Override
            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
                DepartmentResponse dep = response.body();
                Log.d("user:", dep.toString());
                List<Departments> depList = dep.getData();
                Log.d("index", String.valueOf(depList.size()));

//                for spinner try//  spin = new ArrayList<Integer>();


                for (Departments dep1 : depList) {

                    //spin.add(dep1.getId());

                    for (int i = 0; i < depList.size(); i++) {
                        if (i == v1) {
                            depid = dep1.getId();

                            dname.setText(dep1.getName());
                            dis_did.setText("ID:"+depid);
                        }
                    }

                }
//                System.out.println("Spin: " + spin);
//                Log.d("list", spin.toString());
            }

            @Override
            public void onFailure(Call<DepartmentResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
            }

        });

        mupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer id = depid;
                String name = dname.getText().toString();
                dep(name, id);
            }
        });
    }

    private void dep(String name, Integer id) {

        Departments obj = new Departments(name);//constructor calling

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String token = preferences.getString("Token", "");

        Call<DepartmentPostR> call = apiInterface.putDepartment("application/json", "Bearer" + " " + token, id, obj);

        call.enqueue(new Callback<DepartmentPostR>() {

            String displayResponse = "";

            @Override
            public void onResponse(Call<DepartmentPostR> call, Response<DepartmentPostR> response) {

                DepartmentPostR dep = response.body();
                Log.d("employee:", dep.toString());

                displayResponse =
                        "Id:" + dep.getData().getId() + "\n" + "Updated Name:" + dep.getData().getName() + "\n"
                                + "Created_at:" + dep.getData().getCreatedAt() + "\n"
                                + "Updated_at:" + dep.getData().getUpdatedAt() + "\n";

                Toast.makeText(getApplicationContext(), "Deparment Successfully Updated.", Toast.LENGTH_SHORT).show();

                display.setText(displayResponse);
            }

            @Override
            public void onFailure(Call<DepartmentPostR> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(DepartmentPut.this, "Error:" + t.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
            }

        });
    }

}
