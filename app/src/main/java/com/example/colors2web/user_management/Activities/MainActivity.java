package com.example.colors2web.user_management.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.user_management.POJO.Login;
import com.example.colors2web.user_management.POJO.LoginResponse;
import com.example.colors2web.user_management.R;
import com.example.colors2web.user_management.RecycleViews.EmployeeView;
import com.example.colors2web.user_management.api.APIClient;
import com.example.colors2web.user_management.api.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_email)
    EditText email;


    @BindView(R.id.tv_password)
    EditText password;

    @BindView(R.id.tv_token)
    TextView tokens;

    @BindView(R.id.btnlogin)
    Button signin;

    String u_email, u_password;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        ButterKnife.bind(this);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                u_email = email.getText().toString();
                u_password = password.getText().toString();
                login(u_email, u_password);

            }
        });
    }

    private void login(String u_email, String u_password) {

        Login login = new Login(u_email, u_password);
        Log.d("email", u_email);
        Log.d("password", u_password);

        Call<LoginResponse> call = apiInterface.dologin(login);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            @Nullable
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    //just check for the status 200,400 and handle it
                    //if u want the same method of extraction for 200 and 401 it wont works

                    String toks = response.body().success.getToken();

                    Log.d("response-success", response.body().toString());
                    Toast.makeText(getApplicationContext(), "Successful Login", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getApplicationContext(), "TOKEN:" + re.toString(), Toast.LENGTH_SHORT).show();

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Token", toks);
                    editor.apply();

                    Intent i = new Intent(MainActivity.this, EmployeeView.class);
                    startActivity(i);


                } else if (response.code() == 401) {
                   //TODO: 401 error message extraction
                    Toast.makeText(MainActivity.this, "Authentication Failed.Please Enter the Valid Credentials", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(MainActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else {

                    Toast.makeText(MainActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
                Log.e("response-failure", t.toString());
                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
