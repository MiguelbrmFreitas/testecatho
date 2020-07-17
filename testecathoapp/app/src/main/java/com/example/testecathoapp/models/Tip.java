package com.example.testecathoapp.models;

/*
 *  Definição da model de Dica
 */
public class Tip
{
    private String mId;
    private String mDescription;
    private boolean mShowButton;
    private String mButtonLabel;
    private String mUrl;

    public Tip (String id, String description, boolean showButton, String label, String url) {
        mId = id;
        mDescription = description;
        mShowButton = showButton;
        mButtonLabel= label;
        mUrl = url;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean isShowButton() {
        return mShowButton;
    }

    public void setShowButton(boolean mShowButton) {
        this.mShowButton = mShowButton;
    }

    public String getButtonLabel() {
        return mButtonLabel;
    }

    public void setButtonLabel(String mButtonLabel) {
        this.mButtonLabel = mButtonLabel;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
