package com.example.mpp.dto;

import com.example.mpp.enums.Sex;
import lombok.Data;

@Data
public class UserDTO {
    /** user */
    private Integer id;
    /** user */
    private String nameName;
    /** user */
    private Sex sex;
    /** user */
    private String headImg;
    /** user */
    private String userHeadImg;//同 headImg 别名测试
    /** user_address */
    private String tel;
    /** user_address */
    private String address;
    /** user_address */
    private String userAddress;
    /** area */
    private String province;
    /** area */
    private String city;
    /** area */
    private String area;


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nameName='" + nameName + '\'' +
                ", sex=" + sex +
                ", headImg='" + headImg + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
