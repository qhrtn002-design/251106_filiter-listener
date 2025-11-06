package org.example.filterlistener.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/")
public class CounterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-> CounterController 시작");
        // 접속했을 때 세션과 쿠키에 카운트를 늘려주는...
        // 세션으로 카운트를 늘리기
        HttpSession session = req.getSession(); // true -> 없으면 새로 만들겠다
        countBySession(session);
        countByCookie(req, resp);
        req.getRequestDispatcher("/WEB-INF/views/counter.jsp").forward(req, resp);
        System.out.println("-> CounterController 종료");
    }

    private void countBySession(HttpSession session) {
        Object counterValue = session.getAttribute("counter");
        int counter = 0;
        if (counterValue != null) {
            counter = (Integer) session.getAttribute("counter"); // 변환이 안되면 에러
        }
        counter++;
        session.setAttribute("counter", counter);
    }

    private void countByCookie(HttpServletRequest request, HttpServletResponse response) {
        // Request 내부에 쿠키가 있는 체크
        Cookie[] cookies = request.getCookies();
        int counter = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("counter")) {
                counter = Integer.parseInt(cookie.getValue());
                break; // 반복문 종료
            }
        }
        counter++;
        // forward 방식이라면... attribute에 해당 값을 넣는...
        request.setAttribute("counter", counter);
        // Response에다가 addCookie로 덮어씌우기를 함
        Cookie newCookie = new Cookie("counter", Integer.toString(counter));
        newCookie.setPath("/");
        newCookie.setSecure(true);
        newCookie.setMaxAge(60 * 60 * 24); // 86400
        newCookie.setHttpOnly(true);
        response.addCookie(newCookie); // 바꿔놓은 쿠키가 반영되는 시점? -> resp.
        // forward -> counter.jsp -> cookie - req.
        // 한 박자씩 늦은 결과물을 받게 됨
    }
}