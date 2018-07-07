package com.forest10.spring.boot.family.interceptor;

import com.forest10.spring.boot.family.config.SimpleRateLimiter;
import com.forest10.spring.boot.family.selfantion.RequestLimitRate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * Api限流Interceptor
 *
 * @author Forest10
 * @date 2018/04/01 16:12
 */
@Slf4j
@Component
//@PropertySource("classpath:requestRateLimit.properties")
public class RequestRateLimiterInterceptor extends HandlerInterceptorAdapter {


	//@Value(value = "${requestRateLimit.size}")
	private int limitSize = 2;
	private static final TimeUnit limitUnit = TimeUnit.DAYS;

	private static final String CLIENT_KEY = "clientId";
	//private static final String BLACK_IP_LIST_KEY = "blackIpListKey4WxPayProject";

	private Map<String, SimpleRateLimiter> limiters = new ConcurrentHashMap<>();


//    @Autowired
//    BasicRedisHelper basicRedisHelper;

	@Override
	public boolean preHandle(HttpServletRequest request,
	                         HttpServletResponse response, Object handler)
			throws Exception {
		// &&(AnnotationUtils.getAnnotation(((HandlerMethod) handler).getBeanType(),Controller.class) != null)
		if ((handler instanceof HandlerMethod)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			RequestLimitRate annotation = method.getAnnotation(RequestLimitRate.class);
			if (Objects.nonNull(annotation)) {
				boolean limit = annotation.limit();
				if (!limit) {
					return true;
				}
//                String realIp = IpUtil.getRemoteIp(request);
//                log.info("real ip =" + realIp);
				//加黑名单过滤
//                List<String> blackIpList =
//                        basicRedisHelper.getList(BLACK_IP_LIST_KEY);

//                if ((SystemUtil.isProductionEnvironment() && Objects.isNull(realIp)) ||
//                        (Objects.nonNull(blackIpList) && blackIpList.contains(realIp))) {
//                    log.info(realIp + " is black ip!");
//                    return false;
//                }
				String clientId = request.getHeader(CLIENT_KEY);

				if (StringUtils.isBlank(clientId)) {
					response.setStatus(HttpStatus.BAD_REQUEST.value());
					response.getWriter().write("clientId header is needed");
					return false;
				}

				SimpleRateLimiter rateLimiter = getRateLimiter(clientId);
				boolean allowRequest = rateLimiter.tryAcquire();

				if (!allowRequest) {
					Long firstExecuteTime = rateLimiter.getExecuteTime();
					Long now = System.currentTimeMillis();
					if (now - firstExecuteTime < getBlackRequestTimePeriod()) {//是否加入IP黑名单
						//basicRedisHelper.setListValue(BLACK_IP_LIST_KEY, realIp);
						// log.info(realIp + " has been add to black ip list!");
					}
					response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
				}
				response.addHeader("X-RateLimit-Limit", String.valueOf(limitSize) + "in " + limitUnit);
				return allowRequest;
			}
		}
		return true;

	}

	private SimpleRateLimiter getRateLimiter(String clientId) {
		if (!limiters.isEmpty() && limiters.containsKey(clientId)) {
			return limiters.get(clientId);
		} else {
			synchronized (clientId.intern()) {
				if (limiters.containsKey(clientId)) {
					return limiters.get(clientId);
				}
				SimpleRateLimiter rateLimiter =
						SimpleRateLimiter.create(limitSize, limitUnit);
				limiters.put(clientId, rateLimiter);
				return rateLimiter;
			}
		}
	}

	@PreDestroy
	public void destroy() {
	}


	private Long getBlackRequestTimePeriod() {
		return TimeUnit.SECONDS.toMillis(5);
	}

}