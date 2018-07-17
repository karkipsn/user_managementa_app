
package com.example.colors2web.user_management.POJO;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TaskResponse {

    @SerializedName("data")
    private List<Tasks> mData;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("success")
    private Boolean mSuccess;

    public List<Tasks> getData() {
        return mData;
    }

    public void setData(List<Tasks> data) {
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
