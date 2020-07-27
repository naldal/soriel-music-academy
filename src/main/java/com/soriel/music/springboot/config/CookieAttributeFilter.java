package com.soriel.music.springboot.config;

import org.springframework.http.HttpHeaders;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class CookieAttributeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        chain.doFilter(request, response);
        addSameSite(httpServletResponse, "None");

    }

    private void addSameSite(HttpServletResponse httpServletResponse, String sameSite) {
        Collection<String> headers = httpServletResponse.getHeaders(HttpHeaders.SET_COOKIE);
        boolean firstHeader = true;
        for (String header : headers) {
            if (firstHeader){
                httpServletResponse.setHeader(HttpHeaders.SET_COOKIE, String.format("%sl Secure; %s", header, "SameSite="+sameSite));
                firstHeader = false;
                continue;
            }

            httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite="+sameSite));
        }
    }
}
