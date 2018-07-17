package com.example.colors2web.user_management.POJO;

import com.google.gson.annotations.Expose;

public class DepartmentPostR {

    @Expose
    private Departments data;
    @Expose
    private String message;
    @Expose
    private Boolean success;

    public Departments getData() {
        return data;
    }

    public void setData(Departments data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


}
