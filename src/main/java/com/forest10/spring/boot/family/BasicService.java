package com.forest10.spring.boot.family;

import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2019-06-07 16:27
 */
@Service
public class BasicService {

	public String getMobile(Integer size) {
		String s = "13718799123";
		StringBuilder stringBuilder = new StringBuilder();
		int max = Objects.isNull(size) ? 3_100_000 : size;
		for (int i = 0; i < max; i++) {
			stringBuilder.append(s);
		}
		return stringBuilder.toString();
	}

}
