package com.example.sseduproject.bean;

/**
 * Created by caoyingfu on 2017/8/23.
 */

public class SelectBean {

    private String id;
    private String name;
    private String code;

    private String other1;
    private String other2;
    private String other3;
    private String other4;
    private String other5;
    private String other6;


    private String xyId = "";//学院Id
    private String xyName = "";//学院名称

    private String zyId = "";//专业Id
    private String zyName = "";//专业名称

    private String szId = "";//专业Id
    private String szName = "";//专业名称

    public SelectBean() {
    }

    public SelectBean(String name) {
        this.name = name;
    }

    private boolean isCheck = false;// 多选check使用

    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }

    public String getOther4() {
        return other4;
    }

    public void setOther4(String other4) {
        this.other4 = other4;
    }

    public String getOther5() {
        return other5;
    }

    public void setOther5(String other5) {
        this.other5 = other5;
    }

    public String getOther6() {
        return other6;
    }

    public void setOther6(String other6) {
        this.other6 = other6;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getXyId() {
        return xyId;
    }

    public void setXyId(String xyId) {
        this.xyId = xyId;
    }

    public String getXyName() {
        return xyName;
    }

    public void setXyName(String xyName) {
        this.xyName = xyName;
    }

    public String getZyId() {
        return zyId;
    }

    public void setZyId(String zyId) {
        this.zyId = zyId;
    }

    public String getZyName() {
        return zyName;
    }

    public void setZyName(String zyName) {
        this.zyName = zyName;
    }

    public String getSzId() {
        return szId;
    }

    public void setSzId(String szId) {
        this.szId = szId;
    }

    public String getSzName() {
        return szName;
    }

    public void setSzName(String szName) {
        this.szName = szName;
    }

    @Override
    public String toString() {
        return "SelectBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", other3='" + other3 + '\'' +
                ", other4='" + other4 + '\'' +
                ", other5='" + other5 + '\'' +
                ", other6='" + other6 + '\'' +
                ", xyId='" + xyId + '\'' +
                ", xyName='" + xyName + '\'' +
                ", zyId='" + zyId + '\'' +
                ", zyName='" + zyName + '\'' +
                ", szId='" + szId + '\'' +
                ", szName='" + szName + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
