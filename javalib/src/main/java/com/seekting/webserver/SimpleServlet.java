package com.seekting.webserver;

import java.io.OutputStream;

/**
 * Created by seekting on 17-8-4.
 */

public interface SimpleServlet {

    void doGet(SimpleRequest simpleRequest, OutputStream outputStream);

    void doPost(SimpleRequest simpleRequest, OutputStream outputStream);
}
