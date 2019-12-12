package com.forest10.spring.boot.family.web;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class UserReq {

    @NotEmpty(message = "必须传递")
    private String name;
    @Min(value = 1, message = "必须大于1岁")
    private Integer age;
}