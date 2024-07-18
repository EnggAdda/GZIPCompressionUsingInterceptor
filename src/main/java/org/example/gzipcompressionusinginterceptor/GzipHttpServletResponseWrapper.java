package org.example.gzipcompressionusinginterceptor;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

public class GzipHttpServletResponseWrapper extends HttpServletResponseWrapper {
    private GZIPServletOutputStream gzipOutputStream = null;
    private PrintWriter printWriter = null;

    public GzipHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (this.printWriter != null) {
            throw new IllegalStateException("PrintWriter obtained already - cannot get OutputStream");
        }
        if (this.gzipOutputStream == null) {
            this.gzipOutputStream = new GZIPServletOutputStream(getResponse().getOutputStream());
        }
        return this.gzipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (this.gzipOutputStream != null) {
            throw new IllegalStateException("OutputStream obtained already - cannot get PrintWriter");
        }
        if (this.printWriter == null) {
            this.gzipOutputStream = new GZIPServletOutputStream(getResponse().getOutputStream());
            this.printWriter = new PrintWriter(new OutputStreamWriter(this.gzipOutputStream, getResponse().getCharacterEncoding()));
        }
        return this.printWriter;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (this.printWriter != null) {
            this.printWriter.flush();
        } else if (this.gzipOutputStream != null) {
            this.gzipOutputStream.flush();
        }
        super.flushBuffer();
    }

    @Override
    public void setContentLength(int len) {
        // Do not set content length, since content is being compressed
    }

    private class GZIPServletOutputStream extends ServletOutputStream {
        private final GZIPOutputStream gzipOutputStream;

        public GZIPServletOutputStream(ServletOutputStream outputStream) throws IOException {
            super();
            this.gzipOutputStream = new GZIPOutputStream(outputStream);
        }

        @Override
        public void write(int b) throws IOException {
            this.gzipOutputStream.write(b);
        }

        @Override
        public void close() throws IOException {
            this.gzipOutputStream.finish();
            this.gzipOutputStream.close();
        }

        @Override
        public void flush() throws IOException {
            this.gzipOutputStream.flush();
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(jakarta.servlet.WriteListener writeListener) {
            // No-op
        }
    }
}
