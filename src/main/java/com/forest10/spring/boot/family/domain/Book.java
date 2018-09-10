package com.forest10.spring.boot.family.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 描述:
 * 书籍
 *
 * @author Forest10
 * @date 2018/04/01 16:00
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Book implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String reader;

	private String isbn;

}