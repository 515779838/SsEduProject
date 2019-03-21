package com.example.sseduproject.view.SortNameListView;

import java.io.Serializable;
import java.util.ArrayList;


public class SortModel implements Serializable {

    private String id = "";
    private String other1 = "";
    private String other2 = "";
    private String other3 = "";
    private String other4 = "";
    private String other5 = "";
    private String other6 = "";

    private String other7 = "";
    private String other8 = "";
    private String other9 = "";
    private String other10 = "";

    private String name = "";
    private String sortLetters;
    private boolean isChecked = false;

    private String image;



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

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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

    public String getOther7() {
        return other7;
    }

    public void setOther7(String other7) {
        this.other7 = other7;
    }

    public String getOther8() {
        return other8;
    }

    public void setOther8(String other8) {
        this.other8 = other8;
    }

    public String getOther9() {
        return other9;
    }

    public void setOther9(String other9) {
        this.other9 = other9;
    }

    public String getOther10() {
        return other10;
    }

    public void setOther10(String other10) {
        this.other10 = other10;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "SortModel{" +
                "id='" + id + '\'' +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", other3='" + other3 + '\'' +
                ", other4='" + other4 + '\'' +
                ", other5='" + other5 + '\'' +
                ", other6='" + other6 + '\'' +
                ", other7='" + other7 + '\'' +
                ", other8='" + other8 + '\'' +
                ", other9='" + other9 + '\'' +
                ", other10='" + other10 + '\'' +
                ", name='" + name + '\'' +
                ", sortLetters='" + sortLetters + '\'' +
                ", isChecked=" + isChecked +
                ", image='" + image + '\'' +
                '}';
    }
}
