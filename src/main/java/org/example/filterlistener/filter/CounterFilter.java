package org.example.filterlistener.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter("/") // 등록
@WebFilter("/*") // /(경로)에 연결
//@WebFilter("/user/*") // /user/(경로)에 연결
public class CounterFilter implements Filter { // 구현
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // HttpServletRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // HttpServletResponse
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 세션 처리
        HttpSession session = httpServletRequest.getSession();
        int sessionCounter = 0;
        if (session.getAttribute("counter") != null) {
            sessionCounter = (Integer) session.getAttribute("counter"); // 객체(Object) -> Integer(int)
        }
        session.setAttribute("counter", ++sessionCounter);

        // 쿠키 처리
        Cookie[] cookies = httpServletRequest.getCookies();
        int cookieCounter = 0;
        for (Cookie c : cookies) {
            if (c.getName().equals("counter")) {
                cookieCounter = Integer.parseInt(c.getValue()); // 문자열 -> int
                break;
            }
        }
        httpServletRequest.setAttribute("counter", ++cookieCounter); // resp 쿠키 업데이트 이전에...
        Cookie newCookie = new Cookie("counter", Integer.toString(cookieCounter));
        newCookie.setMaxAge(86400);
        httpServletResponse.addCookie(newCookie);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}