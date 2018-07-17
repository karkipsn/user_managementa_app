
package com.example.colors2web.user_management.POJO;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DepartmentResponse {

    @SerializedName("data")
    public List<Departments> mData = new ArrayList<>();

    @SerializedName("message")
    private String mMessage;

    @SerializedName("success")
    private Boolean mSuccess;

    public List<Departments> getData() {
        return mData;
    }

    public void setData(List<Departments> data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}
