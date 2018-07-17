
package com.example.colors2web.user_management.POJO;

import com.google.gson.annotations.SerializedName;


public class Tasks {

    @SerializedName("attachment")
    private String mAttachment;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("deadline")
    private String mDeadline;
    @SerializedName("deleted_at")
    private String mDeletedAt;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("employee_id")
    private Integer mEmployeeId;
    @SerializedName("employee_name")
    private String mEmployeeName;
    @SerializedName("id")
    private Integer mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("updated_at")
    private String mUpdatedAt;


    public Tasks(Integer mEmployeeId, String mTitle, String mDescription , String mDeadline, String mAttachment ) {
        this.mAttachment = mAttachment;
        this.mDeadline = mDeadline;
        this.mDescription = mDescription;
        this.mEmployeeId = mEmployeeId;
        this.mTitle = mTitle;
    }

    public Tasks() {

    }

    public String getAttachment() {
        return mAttachment;
    }

    public void setAttachment(String attachment) {
        mAttachment = attachment;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDeadline() {
        return mDeadline;
    }

    public void setDeadline(String deadline) {
        mDeadline = deadline;
    }

    public String getDeletedAt() {
        return mDeletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        mDeletedAt = deletedAt;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Integer getEmployeeId() {
        return mEmployeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        mEmployeeId = employeeId;
    }

    public String getEmployeeName() {
        return mEmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        mEmployeeName = employeeName;
    }

    public int getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
