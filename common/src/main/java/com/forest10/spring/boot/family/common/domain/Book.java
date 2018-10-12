package com.forest10.spring.boot.family.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * 描述:
 * 书籍
 *
 * @author Forest10
 * @date 2018/04/01 16:00
 */
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reader;

    private String isbn;
}