package org.example.gzipcompressionusinginterceptor;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class GzipHttpServletRequestWrapper extends HttpServletRequestWrapper {


    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public GzipHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        decompressRequest(request);
    }

    private void decompressRequest(HttpServletRequest request) throws IOException {
        System.out.println("request before decompressing" + request);
        try (InputStream gzipStream = new GZIPInputStream(request.getInputStream())) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                // No-op
            }
        };
    }
}

