package com.example.testecathoapp.models;

/*
 *  Definição da model de Usuário
 */
public class User
{
    private String mId;
    private String mName;
    private String mToken;
    private String mPhotoRef;

    public User(String id, String name, String token, String photoRef) {
        mId = id;
        mName = name;
        mToken = token;
        mPhotoRef = photoRef;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String mToken) {
        this.mToken = mToken;
    }

    public String getPhotoRef() {
        return mPhotoRef;
    }

    public void setPhotoRef(String mPhotoRef) {
        this.mPhotoRef = mPhotoRef;
    }
}
