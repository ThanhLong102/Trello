//package com.example.trello.config;
//
//import io.jsonwebtoken.io.IOException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class SimpleCORSFilter implements Filter {
//
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException, java.io.IOException {
//
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//
//        response.setHeader("Access-control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, x-auth-token");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
//            try {
//                chain.doFilter(req, res);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        } else {
//            System.out.println("Pre-flight");
//            response.setHeader("Access-Control-Allowed-Methods", "POST, PUT, GET, DELETE");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type,x-auth-token, " +
//                    "access-control-request-headers, access-control-request-method, accept, origin, authorization, x-requested-with");
//
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
//    }
//}
