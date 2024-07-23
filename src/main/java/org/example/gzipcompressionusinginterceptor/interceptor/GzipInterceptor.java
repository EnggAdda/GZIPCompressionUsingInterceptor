/*
package org.example.gzipcompressionusinginterceptor.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GzipInterceptor implements HandlerInterceptor {

    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String acceptEncoding = request.getHeader("Accept-Encoding");
        if (acceptEncoding != null && acceptEncoding.contains("gzip")) {
            GzipHttpServletResponseWrapper wrappedResponse = new GzipHttpServletResponseWrapper(response);
            request.setAttribute("GZIP_WRAPPED_RESPONSE", wrappedResponse);
            return true; // Return true to continue processing the request
        }
        return true; // Continue processing the request even if gzip is not supported
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        GzipHttpServletResponseWrapper wrappedResponse = (GzipHttpServletResponseWrapper) request.getAttribute("GZIP_WRAPPED_RESPONSE");
        if (wrappedResponse != null) {
            wrappedResponse.finishResponse();
        }
    }
}
*/
