
package com.example.colors2web.user_management.POJO;


import com.google.gson.annotations.SerializedName;


public class Employees {

    @SerializedName("add")
    private String mAdd;
    @SerializedName("birthdate")
    private String mBirthdate;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("date_hired")
    private String mDateHired;
    @SerializedName("deleted_at")
    private String mDeletedAt;
    @SerializedName("department_id")
    private Integer mDepartmentId;
    @SerializedName("department_name")
    private String mDepartmentName;
    @SerializedName("id")
    private Integer mId;
    @SerializedName("emp_id")
    private Integer mEmployee_id;
    @SerializedName("name")
    private String mName;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public Employees() {

    }

    public Employees(String mName, String mAdd, String mBirthdate, String mDateHired, Integer mDepartmentId) {
        this.mName = mName;
        this.mAdd = mAdd;
        this.mBirthdate = mBirthdate;
        this.mDateHired = mDateHired;
        this.mDepartmentId = mDepartmentId;
    }

    public String getAdd() {
        return mAdd;
    }

    public void setAdd(String add) {
        mAdd = add;
    }

    public String getBirthdate() {
        return mBirthdate;
    }

    public void setBirthdate(String birthdate) {
        mBirthdate = birthdate;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDateHired() {
        return mDateHired;
    }

    public void setDateHired(String dateHired) {
        mDateHired = dateHired;
    }

    public String getDeletedAt() {
        return mDeletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        mDeletedAt = deletedAt;
    }

    public Integer getDepartmentId() {
        return mDepartmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        mDepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return mDepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        mDepartmentName = departmentName;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public Integer getmEmployee_id() {
        return mEmployee_id;
    }

    public void setmEmployee_id(Integer mEmployee_id) {
        this.mEmployee_id = mEmployee_id;
    }
}
