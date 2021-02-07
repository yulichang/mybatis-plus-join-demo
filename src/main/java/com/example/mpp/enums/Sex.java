package com.example.mpp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum Sex {

    MAN(0, "男"),

    WOMAN(1, "女");

    @EnumValue
    private int code;
    private String des;

    Sex(int code, String des) {
        this.code = code;
        this.des = des;
    }

    @Override
    public String toString() {
        return "Sex{" +
                "code=" + code +
                ", des='" + des + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}
