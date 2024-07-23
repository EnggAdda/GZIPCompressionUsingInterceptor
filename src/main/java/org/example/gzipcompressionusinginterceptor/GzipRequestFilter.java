package org.example.gzipcompressionusinginterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GzipRequestFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(GzipRequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization necessary
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String contentEncoding = httpRequest.getHeader("Content-Encoding");
        if (contentEncoding != null && contentEncoding.contains("gzip")) {
            // Log the compressed request
            logger.info("Compressed request received: {}", httpRequest.getRequestURI());
            GzipHttpServletRequestWrapper gzipRequest = new GzipHttpServletRequestWrapper(httpRequest);

            // Log the decompressed request
            String decompressedPayload = new String(gzipRequest.getInputStream().readAllBytes(), httpRequest.getCharacterEncoding());
            logger.info("Decompressed request body: {}", decompressedPayload);

            chain.doFilter(gzipRequest, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // No cleanup necessary
    }
}
