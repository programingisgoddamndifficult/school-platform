package com.linjiasong.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.linjiasong.admin.constant.AdminInfoContext;
import com.linjiasong.admin.entity.AdminInfo;
import com.linjiasong.admin.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:19
 */
@Component
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();

        if ("/api/user/signup".equals(requestUri) || "/api/user/login".equals(requestUri)) {
            return true;
        }

        // 获取请求头中的 Authorization 信息
        String token = request.getHeader("Authorization");
        if (token == null) {
            return false;
        }
        AdminInfoContext.set(JSON.parseObject(TokenUtil.parseToken(token.substring("Bearer ".length())), AdminInfo.class));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AdminInfoContext.remove();
    }
}
