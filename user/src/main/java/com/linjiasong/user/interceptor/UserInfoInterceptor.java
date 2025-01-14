package com.linjiasong.user.interceptor;

import com.linjiasong.user.constant.UserInfoContext;
import com.linjiasong.user.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author linjiasong
 * @date 2025/01/14 22:10
 */
@Component
public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的 Authorization 信息
        String token = request.getHeader("Authorization");
        // 打印 Authorization 信息


        UserInfoContext.set( TokenUtil.parseToken(token.substring("Bearer ".length())));

        return true;
    }
}
