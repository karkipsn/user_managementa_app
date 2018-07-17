package com.example.colors2web.user_management.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.user_management.POJO.DepartmentResponse;
import com.example.colors2web.user_management.POJO.Departments;
import com.example.colors2web.user_management.POJO.EmpPostResponse;
import com.example.colors2web.user_management.POJO.EmployeeResponse;
import com.example.colors2web.user_management.POJO.Employees;
import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.api.APIClient;
import com.example.colors2web.user_management.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeePut extends AppCompatActivity {

    @BindView(R.id.dis_empid)
    TextView iddisplay;

    @BindView(R.id.tv_ename)
    EditText ename;

    @BindView(R.id.tv_eadd)
    EditText eadd;

    @BindView(R.id.tv_ebirthdate)
    EditText ebirthdate;

    @BindView(R.id.tv_edate_hired)
    EditText edate_hired;

    @BindView(R.id.dep_drop)
    AutoCompleteTextView acT;

    @BindView(R.id.btn_empupdate)
    Button empUpdate;

    ArrayList<Integer> spin;
    APIInterface apiInterface;
    int empid;
    Integer selection;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_employee);

        ButterKnife.bind(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Intent intent = getIntent();
        final int v1 = intent.getExtras().getInt("id");
        //to retreive previous position


        loadSpinner();

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Integer dep= (Integer) spinner.getItemAtPosition(spinner.getSelectedItemPosition());
////                Toast.makeText(getApplicationContext(),dep,Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                // DO Nothing here
//                Integer dep= (Integer) spinner.getAdapter().getItem(0);
//            }
//        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(EmployeePut.this);
        final String token = preferences.getString("Token", "");

        Call<EmployeeResponse> call = apiInterface.getEmployees("application/json", "Bearer" + " " + token);
        // make an asynchronous call
        call.enqueue(new Callback<EmployeeResponse>() {

            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {

                EmployeeResponse dep = response.body();
                Log.d("employee:", dep.toString());
                List<Employees> empList = dep.getData();
                Log.d("index", String.valueOf(empList.size()));
                String displayResponse = "";

               // for (Employees dep1 : depList) {
                    for (int i = 0; i < empList.size(); i++) {
                        if(i==v1){

                            empid = empList.get(i).getmEmployee_id();//Id to be sent for update

                            iddisplay.setText("Employee_id: "+ empList.get(i).getmEmployee_id());

                            ename.setText(empList.get(i).getName());
                            eadd.setText(empList.get(i).getAdd());
                            ebirthdate.setText(empList.get(i).getBirthdate());
                            edate_hired.setText(empList.get(i).getDateHired());
                            acT.setText(String.valueOf(empList.get(i).getDepartmentId()));

                            //TODO: spinner  for the option for the department.
                            //TODO: LINK 1:https://dzone.com/articles/populate-spinner-from-json-data
                            //TODO:LINK2-http://findnerd.com/list/view/How-to-fetch-data-in-Spinner-from-api-code/17209/
                            //TODO: how to store the array of the single parameter from retrofit into array

                        }

                    }}
//            }

            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
                Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
            }

        });

        empUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = ename.getText().toString();
                String add = eadd.getText().toString();
                String bd = ebirthdate.getText().toString();
                String hd = edate_hired.getText().toString();
                Integer did = acT.getText().length();

                emp(name, add, bd, hd, did,empid);

            }
        });

    }

    private void loadSpinner() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(EmployeePut.this);
        final String token = preferences.getString("Token", "");

        Call<DepartmentResponse> call = apiInterface.getdepartments("application/json", "Bearer" + " " + token);
        call.enqueue(new Callback<DepartmentResponse>() {

            @Override
            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
                DepartmentResponse dep = response.body();
                List<Departments> depList = dep.getData();

                spin = new ArrayList<Integer>();
//                for spinner try

                for (Departments dep1 : depList) {

                    spin.add(dep1.getId());
                }

                acT.setAdapter(new ArrayAdapter<Integer>(EmployeePut.this, android.R.layout.simple_spinner_dropdown_item, spin));
//                System.out.println("Spin: " + spin);
//                Log.d("list", spin.toString());
//                Link:https://stackoverflow.com/questions/47666321/opening-spinner-by-clicking-on-edittext/47667518#47667518?newreg=3755bd61db4b4a4d91ada181638da0d1

                acT.setCursorVisible(false);
                acT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        acT.showDropDown();
                        selection = (Integer) parent.getItemAtPosition(position);

//                        Toast.makeText(getApplicationContext(), selection,
//                                Toast.LENGTH_SHORT);
                    }
                });
                acT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        acT.showDropDown();
                    }
                });
            }

            @Override
            public void onFailure(Call<DepartmentResponse> call, Throwable t) {
                call.cancel();
                Log.e("SPinner-loading Failed", call.toString());

            }
        });
    }

    private void emp(String name, String add, String bd, String hd, Integer did,Integer empid) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(EmployeePut.this);
        final String token = preferences.getString("Token", "");

        Employees employees = new Employees(name, add, bd, hd, did);

        Call<EmpPostResponse> call = apiInterface.patchEmployees("application/json", "Bearer" + " " + token,empid, employees);

        call.enqueue(new Callback<EmpPostResponse>() {

            String displayResponse = "";

            @Override
            public void onResponse(Call<EmpPostResponse> call, Response<EmpPostResponse> response) {

                EmpPostResponse dep = response.body();
                Log.d("employee:", dep.toString());


                displayResponse =
                         "Id:" + dep.getData().getId() + "\n" + "Name:" + dep.getData().getName() + "\n"
                        + "Address:" + dep.getData().getAdd() + "\n" + "Birth:" + dep.getData().getBirthdate() + "\n"
                        + "Hired:" + dep.getData().getDateHired() + "\n" + "Department_id:" + dep.getData().getDepartmentId() + "\n"
                        + "Department_name:" + dep.getData().getDepartmentName() + "\n" + "Created_at:" + dep.getData().getCreatedAt() + "\n"
                        + "Updated_at:" + dep.getData().getUpdatedAt() + "\n";

                Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_SHORT).show();

                // display.setText(displayResponse);
            }

            @Override
            public void onFailure(Call<EmpPostResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
                Log.e("response-failure", t.toString());
                Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
            }

        });
    }
}

//for Spinner Activity:

// payment_method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                payment_method1 = payment_method.getSelectedItem().toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                payment_method1 = payment_method.getAdapter().getItem(0).toString();
//            }
//        });
