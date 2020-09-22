package com.whdx.data.data.user;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description:
 * create by LGM at 2018 2018/12/12 17:29
 **/
public class TokenBean implements Parcelable {

    /**
     * id : string
     * userId : string
     * userType : string
     * clientId : string
     * accessToken : string
     * accessTokenExpiresAt : string
     * refreshToken : string
     * refreshTokenExpiresAt : string
     */

    private String id;
    private String userId;
    /**
     * 1:老师 2:学生 3：家长
     **/
    private String userType;
    private String clientId;
    private String accessToken;
    private String accessTokenExpiresAt;
    private String refreshToken;
    private String refreshTokenExpiresAt;
    private boolean valid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenExpiresAt() {
        return accessTokenExpiresAt;
    }

    public void setAccessTokenExpiresAt(String accessTokenExpiresAt) {
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenExpiresAt() {
        return refreshTokenExpiresAt;
    }

    public void setRefreshTokenExpiresAt(String refreshTokenExpiresAt) {
        this.refreshTokenExpiresAt = refreshTokenExpiresAt;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public TokenBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.userId);
        dest.writeString(this.userType);
        dest.writeString(this.clientId);
        dest.writeString(this.accessToken);
        dest.writeString(this.accessTokenExpiresAt);
        dest.writeString(this.refreshToken);
        dest.writeString(this.refreshTokenExpiresAt);
        dest.writeByte(this.valid ? (byte) 1 : (byte) 0);
    }

    protected TokenBean(Parcel in) {
        this.id = in.readString();
        this.userId = in.readString();
        this.userType = in.readString();
        this.clientId = in.readString();
        this.accessToken = in.readString();
        this.accessTokenExpiresAt = in.readString();
        this.refreshToken = in.readString();
        this.refreshTokenExpiresAt = in.readString();
        this.valid = in.readByte() != 0;
    }

    public static final Creator<TokenBean> CREATOR = new Creator<TokenBean>() {
        @Override
        public TokenBean createFromParcel(Parcel source) {
            return new TokenBean(source);
        }

        @Override
        public TokenBean[] newArray(int size) {
            return new TokenBean[size];
        }
    };
}
