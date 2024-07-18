/*
package org.example.gzipcompressionusinginterceptor;

import org.example.gzipcompressionusinginterceptor.GzipHttpServletResponseWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GzipInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // Check if the client accepts gzip encoding
        String acceptEncoding = request.getHeader("Accept-Encoding");
        if (acceptEncoding != null && acceptEncoding.contains("gzip")) {
            response.setHeader("Content-Encoding", "gzip");
            response.setHeader("Vary", "Accept-Encoding");
            GzipHttpServletResponseWrapper wrappedResponse = new GzipHttpServletResponseWrapper(response);
            request.setAttribute("GZIP_WRAPPED_RESPONSE", wrappedResponse);
        }
        return true;
    }
}
*/
