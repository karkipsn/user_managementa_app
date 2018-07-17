
package com.example.colors2web.user_management.POJO;

import com.google.gson.annotations.SerializedName;

public class Success {

    @SerializedName("token")
    private String mToken;

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

}
