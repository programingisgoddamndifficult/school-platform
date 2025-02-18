package com.linjiasong.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.linjiasong.admin.constant.AdminInfoContext;
import com.linjiasong.admin.constant.RedisKeyEnum;
import com.linjiasong.admin.entity.AdminInfo;
import com.linjiasong.admin.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Map;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:19
 */
@Component
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();

        if ("/api/admin/login".equals(requestUri)) {
            return true;
        }

        // 获取请求头中的 Authorization 信息
        String token = request.getHeader("Authorization");
        if (token == null) {
            return false;
        }

        AdminInfo adminInfo = JSON.parseObject(TokenUtil.parseToken(token.substring("Bearer ".length())), AdminInfo.class);

        RBucket<String> loginBucket = redissonClient.getBucket(String.format(RedisKeyEnum.ADMIN_LOGIN.getKey(), adminInfo.getId()));
        if (!loginBucket.isExists()) {
            setResponse(response, "用户尚未登陆或登陆信息过期");

            return false;
        }

        AdminInfoContext.set(JSON.parseObject(TokenUtil.parseToken(token.substring("Bearer ".length())), AdminInfo.class));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AdminInfoContext.remove();
    }

    private void setResponse(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        String responseBody = JSON.toJSONString(Map.of("msg", msg, "code", "401"));
        response.getWriter().write(responseBody);
    }
}
