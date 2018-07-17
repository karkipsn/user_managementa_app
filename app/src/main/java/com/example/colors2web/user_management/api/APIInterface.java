package com.example.colors2web.user_management.api;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.colors2web.user_management.Activities.MainActivity;
import com.example.colors2web.user_management.POJO.DepartmentPostR;
import com.example.colors2web.user_management.POJO.Departments;
import com.example.colors2web.user_management.POJO.EmpPostResponse;
import com.example.colors2web.user_management.POJO.EmployeeResponse;
import com.example.colors2web.user_management.POJO.Employees;
import com.example.colors2web.user_management.POJO.Login;
import com.example.colors2web.user_management.POJO.LoginResponse;
import com.example.colors2web.user_management.POJO.ResponseUser;
import com.example.colors2web.user_management.POJO.DepartmentResponse;
import com.example.colors2web.user_management.POJO.TaskPostR;
import com.example.colors2web.user_management.POJO.TaskResponse;
import com.example.colors2web.user_management.POJO.Tasks;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface APIInterface {

//        // Static Header
//        //String authorization = "Authorization: Basic XXXXXX";
//        String contentType = "Content-Type: application/json";

          @Headers({
            "Accept: application/json"
           })
         @POST("api/auth/login")
        Call<LoginResponse> dologin(@Body Login login);

        // Dynamic Header
//
//        @POST("login")
//        Call<LoginResponse> login(@Header("Authorization") String basicAuth, @Body Login login);


    @GET("api/users")
//    @Headers({
//            "Content-Type: application/json",
//              "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjM5NzdlZjk5NjJlOTNhNzZhMzQ2N2M0YzFjM2U2ODUyY2VjN2FmYTZhOGZhY2EyMDc2OGVkOTNiODJhNmIxYjgwZjE2YWVhZGQxYmNiM2JiIn0.eyJhdWQiOiIxIiwianRpIjoiMzk3N2VmOTk2MmU5M2E3NmEzNDY3YzRjMWMzZTY4NTJjZWM3YWZhNmE4ZmFjYTIwNzY4ZWQ5M2I4MmE2YjFiODBmMTZhZWFkZDFiY2IzYmIiLCJpYXQiOjE1MzA3NjU3NjQsIm5iZiI6MTUzMDc2NTc2NCwiZXhwIjoxNTYyMzAxNzY0LCJzdWIiOiIyMSIsInNjb3BlcyI6W119.f4id6OMxUh-ywVFNsNlBP3m5Q56mnOLyhqSBuX-K_6xN7DSLxB4fZ5LN9q-sZ04-XcQpqF1sHXFXSBorpDtjGktpamHfiMl8nVZgb2QdurP7zwfat0QWcIDXXuRnH4hH6jIpsrDnrYWm1_bDIkiW5VfR096O13OZMF80v8hzLef54LPvqdV4spNOyA9ecmHAOTE5jtZSOOVdE69T2OPE01yazWXqs6Ut_axV67yx_ZMecxpp-EXbt3BQuMywZrabc3WUMM2gfVwEfNA-dC6F1aEBcr95w7yk5dtNXKo7gKsAGVdN8f5TNnFSiUbW0UGdLqWvONRxR8JkF1FaelLRQHjYkbCfZNlav5NZyGYSB8ycXAb6BijqcrxU9b1q_2lpHf6Iurd8Dwprusc0YaA_4XPRbZTZFsgu-NTt-PjCUZrX954cMs2R2UoZqhYMykmgl_uSwHj7Rkb1KKY1wSYaEK5CTgHPw47SsNpKCHwV6IoO2CFNujQSGu5pSxws-DhDpef1oYRF6eSLrVd7Dtn1wV4TAja4Ib4w79-zNX2ZGuAe-aWouqXOiHDSGK6oHFSy5QgCE3S-n_yS1q0lF4wUhhgJXTsUGMnFHE35cWYmU7EzEsCnBjj9JbHbSqneTZOkxMJX3Wh2zV59Uif8TD8P8PqB71AURCzEtj4iUK_1oaE"
//    })
     Call<ResponseUser> getUsers(@Header("Authorization") String token);


    @GET("api/departments")
    //passing Headers Dynamically
     Call<DepartmentResponse> getdepartments(@Header("Content-Type") String accept, @Header("Authorization") String token);

    @POST("api/departments")
    Call<DepartmentPostR> postDepartment(@Header("Content-Type") String accept, @Header("Authorization") String token, @Body Departments departments);


    @PATCH("api/departments/{department}")
    Call<DepartmentPostR> putDepartment(@Header("Content-Type") String accept, @Header("Authorization") String token,@Path("department") Integer dep_id, @Body Departments departments);


    @DELETE("api/departments/{department}")
    Call<DepartmentPostR> deleteDepartments(@Header("Content-Type") String accept, @Header("Authorization") String token,@Path("department") Integer dep_id);


    @GET("api/employees")
    Call<EmployeeResponse> getEmployees(@Header("Content-Type") String accept, @Header("Authorization") String token);

    @POST("api/employees")
    Call<EmpPostResponse> postEmployees(@Header("Content-Type") String accept, @Header("Authorization") String token,@Body Employees employees);

    @PATCH("api/employees/{employee}")
    Call<EmpPostResponse> patchEmployees(@Header("Content-Type") String accept, @Header("Authorization") String token,@Path("employee") Integer emp_id,@Body Employees employees);


    @DELETE("api/employees/{employee}")
    Call<EmpPostResponse> deleteEmployees(@Header("Content-Type") String accept, @Header("Authorization") String token,@Path("employee") Integer emp_id);

    @GET("api/tasks")
    Call<TaskResponse> gettasks(@Header("Content-Type") String accept, @Header("Authorization") String token);

    @GET("api/tasks/{task}")
    Call<TaskResponse> getemptasks(@Header("Content-Type") String accept, @Header("Authorization") String token,@Path("task") Integer emp_id);

    @POST("api/employees")
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjM5NzdlZjk5NjJlOTNhNzZhMzQ2N2M0YzFjM2U2ODUyY2VjN2FmYTZhOGZhY" +
                    "2EyMDc2OGVkOTNiODJhNmIxYjgwZjE2YWVhZGQxYmNiM2JiIn0.eyJhdWQiOiIxIiwianRpIjoiMzk3N2VmOTk2MmU5M2E3NmEzNDY3YzRjMWMzZTY4NTJj" +
                    "ZWM3YWZhNmE4ZmFjYTIwNzY4ZWQ5M2I4MmE2YjFiODBmMTZhZWFkZDFiY2IzYmIiLCJpYXQiOjE1MzA3NjU3NjQsIm5iZiI6MTUzMDc2NTc2NCwiZXhw" +
                    "IjoxNTYyMzAxNzY0LCJzdWIiOiIyMSIsInNjb3BlcyI6W119.f4id6OMxUh-ywVFNsNlBP3m5Q56mnOLyhqSBuX-K_6xN7DSLxB4fZ5LN9q-sZ04-XcQpqF1" +
                    "sHXFXSBorpDtjGktpamHfiMl8nVZgb2QdurP7zwfat0QWcIDXXuRnH4hH6jIpsrDnrYWm1_bDIkiW5VfR096O13OZMF80v8hzLef54LPvqdV4spNOyA9ecmH" +
                    "AOTE5jtZSOOVdE69T2OPE01yazWXqs6Ut_axV67yx_ZMecxpp-EXbt3BQuMywZrabc3WUMM2gfVwEfNA-dC6F1aEBcr95w7yk5dtNXKo7gKsAGVdN8f5TNn" +
                    "FSiUbW0UGdLqWvONRxR8JkF1FaelLRQHjYkbCfZNlav5NZyGYSB8ycXAb6BijqcrxU9b1q_2lpHf6Iurd8Dwprusc0YaA_4XPRbZTZFsgu-NTt-PjCUZrX" +
                    "954cMs2R2UoZqhYMykmgl_uSwHj7Rkb1KKY1wSYaEK5CTgHPw47SsNpKCHwV6IoO2CFNujQSGu5pSxws-DhDpef1oYRF6eSLrVd7Dtn1wV4TAja4Ib4w79" +
                    "-zNX2ZGuAe-aWouqXOiHDSGK6oHFSy5QgCE3S-n_yS1q0lF4wUhhgJXTsUGMnFHE35cWYmU7EzEsCnBjj9JbHbSqneTZOkxMJX3Wh2zV59Uif8TD8" +
                    "P8PqB71AURCzEtj4iUK_1oaE"
    })
    Call<TaskPostR> postTasks(@Body Tasks tasks);
//    TODO: Shared Prefernce
}
//
//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);// send in network as https://reqres.in/api/users?&page=2
//

//
//    @POST("/api/users")
//    Call<User> createUser(@Body User user);
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
//
//    @PUT("/api/users/{id}")
//    Call<Patch> doPutUser(@Path("id") String id, @Body Patch userput);
//
//    @PATCH("/api/users/{id}")
//    Call<Patch> doPatchUser(@Path("id") String id, @Body Patch userput);
//
//    @DELETE("/api/users/{id}")
//    Call<Patch>delete(@Path("id") String id);

//For using query
// @GET("/data/2.5/weather?")
//    Call<City> getWeather(@Query("q") String location, @Query("appid") String key);
