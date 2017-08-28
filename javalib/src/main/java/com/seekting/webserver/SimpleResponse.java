package com.seekting.webserver;

import java.io.OutputStream;

/**
 * Created by seekting on 17-8-4.
 */

public interface SimpleResponse {
    void write(OutputStream outputStream);
}
