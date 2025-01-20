package com.linjiasong.user.interceptor;

import com.alibaba.fastjson.JSON;
import com.linjiasong.user.constant.RedisKeyEnum;
import com.linjiasong.user.constant.UserInfoContext;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author linjiasong
 * @date 2025/01/14 22:10
 */
@Component
@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();

        if ("/api/user/signup".equals(requestUri) || "/api/user/login".equals(requestUri) || requestUri.contains("/admin")) {
            return true;
        }

        // 获取请求头中的 Authorization 信息
        String token = request.getHeader("Authorization");
        if (token == null) {
            return false;
        }

        UserInfo userInfo = JSON.parseObject(TokenUtil.parseToken(token.substring("Bearer ".length())), UserInfo.class);

        RBucket<Long> bucket = redissonClient.getBucket(String.format(RedisKeyEnum.USER_BAN.getKey(), userInfo.getId()));
        if (bucket.isExists()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            String responseBody = JSON.toJSONString(Map.of("msg", "当前用户账号存在异常", "code", "401"));
            response.getWriter().write(responseBody);

            return false;
        }

        UserInfoContext.set(userInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoContext.remove();
    }
}
