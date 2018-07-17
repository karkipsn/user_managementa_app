package com.example.colors2web.user_management.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUser {


    @SerializedName("data")
    public List<User> mData;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("success")
    private Boolean mSuccess;

    public List<User> getData() {
        return mData;
    }

    public void setData(List<User> data) {
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
