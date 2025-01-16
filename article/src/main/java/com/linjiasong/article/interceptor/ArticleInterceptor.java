package com.linjiasong.article.interceptor;

import com.alibaba.fastjson.JSON;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.UserInfo;
import com.linjiasong.article.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author linjiasong
 * @date 2025/1/15 下午4:50
 */
@Component
@Slf4j
public class ArticleInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();

        if ("/api/article/all".equals(requestUri)) {
            return true;
        }

        // 获取请求头中的 Authorization 信息
        String token = request.getHeader("Authorization");
        if (token == null) {
            return true;
        }
        ArticleContext.set(JSON.parseObject(TokenUtil.parseToken(token.substring("Bearer ".length())), UserInfo.class));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ArticleContext.remove();
    }
}
