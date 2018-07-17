
package com.example.colors2web.user_management.POJO;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("created_at")
    private Object mCreatedAt;
    @SerializedName("deleted_at")
    private Object mDeletedAt;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("fname")
    private String mFname;
    @SerializedName("id")
    private Long mId;
    @SerializedName("lname")
    private String mLname;
    @SerializedName("updated_at")
    private Object mUpdatedAt;

    public Object getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Object createdAt) {
        mCreatedAt = createdAt;
    }

    public Object getDeletedAt() {
        return mDeletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        mDeletedAt = deletedAt;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFname() {
        return mFname;
    }

    public void setFname(String fname) {
        mFname = fname;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLname() {
        return mLname;
    }

    public void setLname(String lname) {
        mLname = lname;
    }

    public Object getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
