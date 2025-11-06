package org.example.filterlistener.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/") // 등록
public class CounterFilter implements Filter { // 구현
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("-> CounterFilter 시작");
        // req.dispatcher -> 포워드 (주소를 유지한채로 다른 리소스로)
        // resp.redirect -> 리다이렉트 (그쪽으로 GET)
        // filterChain.doFilter -> 가던 길 가게 만든다
        System.out.println("-> CounterFilter 통과");
        filterChain.doFilter(servletRequest, servletResponse);
        // 위에 셋 중에 하나가 없으면... 그냥 처리가 안 된다
        System.out.println("-> CounterFilter 종료");
    }
}