package com.example.colors2web.user_management.POJO;

import com.google.gson.annotations.Expose;

public class TaskPostR {

        @Expose
        private Tasks data;
        @Expose
        private String message;
        @Expose
        private Boolean success;

        public Tasks getData() {
            return data;
        }

        public void setData(Tasks data) {
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


