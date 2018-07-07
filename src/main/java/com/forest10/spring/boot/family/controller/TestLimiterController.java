package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.api.pojo.JsonResult;
import com.forest10.spring.boot.family.domain.Book;
import com.forest10.spring.boot.family.repository.ReadingListRepository;
import com.forest10.spring.boot.family.selfantion.RequestLimitRate;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述:
 * Api限流测试
 *
 * @author Forest10
 * @date 2018/07/07 21:21
 */
@RestController
@RequestMapping("/limit")
public class TestLimiterController {


	@RequestLimitRate
	@GetMapping(value = "/getList")
	public JsonResult getList() {
		return JsonResult.success("获取列表成功", Lists.newArrayList());
	}


}