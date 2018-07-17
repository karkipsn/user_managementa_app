package com.example.colors2web.user_management.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeResponse {

        @SerializedName("data")
        public List<Employees> mData;

        @SerializedName("message")
        private String mMessage;

        @SerializedName("success")
        private Boolean mSuccess;

        public List<Employees> getData() {
            return mData;
        }

        public void setData(List<Employees> data) {
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


