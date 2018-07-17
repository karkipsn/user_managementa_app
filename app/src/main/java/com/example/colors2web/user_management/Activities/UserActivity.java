package com.example.colors2web.user_management.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.colors2web.user_management.POJO.EmployeeResponse;
import com.example.colors2web.user_management.POJO.Employees;
import com.example.colors2web.user_management.POJO.ResponseUser;
import com.example.colors2web.user_management.POJO.User;
import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.api.APIClient;
import com.example.colors2web.user_management.api.APIInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    APIInterface apiInterface;

    @BindView(R.id.btngetUsers)
    Button getUser;

    @BindView(R.id.tv_uid)
    TextView display;

    ListView listViewUsers;

    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    String token = preferences.getString("Token", "");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        ButterKnife.bind(this);

        getUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUsers();
            }
        });
    }

    private void getUsers() {

        Call<ResponseUser> call = apiInterface.getUsers("Bearer" + token);
        // make an asynchronous call
        call.enqueue(new Callback<ResponseUser>() {

            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                ResponseUser dep = response.body();
                Log.d("employee:", dep.toString());
                List<User> depList = dep.getData();
                Log.d("index", String.valueOf(depList.size()));
                String displayResponse = "";

                for (User dep1 : depList) {

                    displayResponse += "id: " + dep1.getId() + "\n" + "Name: " + dep1.getFname() + dep1.getLname() + "\n" +
                            "Email: " + dep1.getEmail() + "\n" + "\n";

                    display.setText(displayResponse);

                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
            }

        });
    }


}
