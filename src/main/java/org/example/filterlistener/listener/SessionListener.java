package org.example.filterlistener.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener // path, url 연결할 필요 X.
public class SessionListener implements HttpSessionListener { // 어떤 이벤트가 연결될지는 listener가 결정
    private static int activeSessions = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
        System.out.println("세션 생성 : %s".formatted(se.getSession().getId()));
        System.out.println("세션 활성화 수 : %d".formatted(activeSessions));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        activeSessions--;
        System.out.println("세션 종료 : %s".formatted(se.getSession().getId()));
        System.out.println("세션 활성화 수 : %d".formatted(activeSessions));
    }
}