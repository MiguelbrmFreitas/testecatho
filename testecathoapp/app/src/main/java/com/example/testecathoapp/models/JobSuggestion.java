package com.example.testecathoapp.models;

/*
 *  Definição da model de Sugestão de Vaga
 */
public class JobSuggestion
{
    private String mJobAdTitle;
    private String mCompany;
    private String mDate;
    private int mTotalPositions;
    private String[] mLocations;
    private String mSalary;

    public JobSuggestion(String jobAdTitle, String company, String date, int totalPositions, String[] locations, String salary) {
        mJobAdTitle = jobAdTitle;
        mCompany = company;
        mDate = date;
        mTotalPositions = totalPositions;
        mLocations = locations;
        mSalary = salary;
    }

    public String getJobAdTitle() {
        return mJobAdTitle;
    }

    public void setJobAdTitle(String mJobAdTitle) {
        this.mJobAdTitle = mJobAdTitle;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public int getTotalPositions() {
        return mTotalPositions;
    }

    public void setTotalPositions(int mTotalPositions) {
        this.mTotalPositions = mTotalPositions;
    }

    public String[] getLocations() {
        return mLocations;
    }

    public void setLocations(String[] mLocations) {
        this.mLocations = mLocations;
    }

    public String getSalary() {
        return mSalary;
    }

    public void setSalary(String mSalary) {
        this.mSalary = mSalary;
    }
}
