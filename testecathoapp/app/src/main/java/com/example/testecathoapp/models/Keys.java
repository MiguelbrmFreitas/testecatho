package com.example.testecathoapp.models;

/*
 *  Definição da model de Keys
 */
public class Keys
{
    private String mAuth;
    private String mTips;
    private String mSuggestion;
    private String mSurvey;

    public Keys (String auth, String tips, String suggestion, String survey) {
        mAuth = auth;
        mTips = tips;
        mSuggestion = suggestion;
        mSurvey = survey;
    }

    public String getAuth() {
        return mAuth;
    }

    public void setAuth(String mAuth) {
        this.mAuth = mAuth;
    }

    public String getTips() {
        return mTips;
    }

    public void setTips(String mTips) {
        this.mTips = mTips;
    }

    public String getSuggestion() {
        return mSuggestion;
    }

    public void setSuggestion(String mSuggestion) {
        this.mSuggestion = mSuggestion;
    }

    public String getSurvey() {
        return mSurvey;
    }

    public void setSurvey(String mSurvey) {
        this.mSurvey = mSurvey;
    }
}
