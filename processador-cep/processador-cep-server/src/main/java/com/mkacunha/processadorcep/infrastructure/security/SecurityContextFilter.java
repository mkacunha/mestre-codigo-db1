package com.mkacunha.processadorcep.infrastructure.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Order(1)
public class SecurityContextFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityContextFilter.class);

    @Autowired
    private SecutiryContextService secutiryContextService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Iniciando filtro de logging");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        Optional<String> token = this.getToken((HttpServletRequest) request);
//        if (token.isPresent()) {
//            if (secutiryContextService.hasToken(token.get())) {
//                chain.doFilter(request, response);
//            } else {
//                this.unauthorized((HttpServletResponse) response);
//            }
//        } else {
//            this.unauthorized((HttpServletResponse) response);
//        }
    }

    @Override
    public void destroy() {
        LOGGER.warn("Destruindo o filtro de logging");
    }

//    private Optional<String> getToken(HttpServletRequest req) {
//        Enumeration<String> headerNames = req.getHeaderNames();
//        if (headerNames != null) {
//            String element;
//            while ((element = headerNames.nextElement()) != null) {
//                if (element.equals("token")) {
//                    return Optional.ofNullable(req.getHeader(element));
//                }
//            }
//        }
//        return Optional.empty();
//    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        response.sendError(401, "Usuário não logado");
    }

}
