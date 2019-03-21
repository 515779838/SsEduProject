package com.example.sseduproject.bean;

public class WxLoginBean {

    /**
     * unionid : oDS8y5ptdFpWRRXd03ckyxYV4fSY
     * userID : ovhon5zqXAhHrJ1q-K9Zf8JSZVyU
     * icon : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKcVSg6ub9PZdxQBSxC7ehibUqZOV7FjYgvJLgDZzJ7BqPb2JeIpNicInZAMVsrrKpZhlkH8BbxRICA/132
     * expiresTime : 1553139538669
     * nickname : Mr.Z
     * token : 19__4SucifcEkkuHn7kKFaxnPKlpoS-NARc5ruQrpkakr--2a8OiBSz8Mu5AAPduuoqNVDt5AFCPwfATPwXirT0jVLjeRNsoP4YBdw842Isu5c
     * city : 长春
     * gender : 0
     * province : 吉林
     * refresh_token : 19_x8XLoKSMAwsAxl98QfSA6yJY07QhhyCLfornn8qXHMu07fkylssZVYKQuc_DNbbtSZiCARr4B_wln2NkzypMSVWaAsiKvvttBc1fDxr4jMU
     * openid : ovhon5zqXAhHrJ1q-K9Zf8JSZVyU
     * country : 中国
     * expiresIn : 7200
     */

    private String unionid;
    private String userID;
    private String icon;
    private String expiresTime;
    private String nickname;
    private String token;
    private String city;
    private String gender;
    private String province;
    private String refresh_token;
    private String openid;
    private String country;
    private String expiresIn;

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(String expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "WxLoginBean{" +
                "unionid='" + unionid + '\'' +
                ", userID='" + userID + '\'' +
                ", icon='" + icon + '\'' +
                ", expiresTime='" + expiresTime + '\'' +
                ", nickname='" + nickname + '\'' +
                ", token='" + token + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", province='" + province + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", openid='" + openid + '\'' +
                ", country='" + country + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                '}';
    }
}
