package com.forest10.spring.boot.family.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;

/**
 * @author Forest10
 * @date 2019-02-15 11:54
 */
public enum AnnimalTypeEnum {

    CAT(1, "🐱"),
    DOG(2, "🐩");

    private int value;
    private String desc;

    AnnimalTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static AnnimalTypeEnum getByDesc(String desc) {
        for (AnnimalTypeEnum b : AnnimalTypeEnum.values()) {
            if (b.getDesc()
                .equals(desc)) {
                return b;
            }
        }
        return null;
    }

    /**
     * 负责包装传入controller传入枚举value参数的.删了(包括注解)系统直接瘫痪
     */
    @JsonCreator
    public static AnnimalTypeEnum getByValue(int value) {
        for (AnnimalTypeEnum b : AnnimalTypeEnum.values()) {
            if (Objects.equals(b.getValue(), value)) {
                return b;
            }
        }
        return null;
    }


    /**
     * 负责拿到传入controller传入枚举value参数的.删了(包括注解)系统直接瘫痪
     */
    @JsonValue
    public int getValue() {
        return value;
    }


    public String getDesc() {
        return desc;
    }

}
