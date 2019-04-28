package com.forest10.spring.boot.family.entity;

import lombok.Data;

/**
 * @author Forest10
 * @date 2019-04-27 11:04
 */
@Data
public class Book {

    private Long id;
    private String isbn;
    private String reader;
}
