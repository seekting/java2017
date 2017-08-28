package com.seekting.webserver;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by seekting on 17-8-4.
 */

public class BodySimpleResponse implements SimpleResponse{
    public static final String SPACE = " ";
    public static final String RN = "\r\n";
    public static final String HEAD_SPLIT = ":";
    public int mStatusCode = 200;
    public String mHttpVersion = "HTTP/1.1";
    public String mStatusMsg = "OK";
    public Map<String, String> mHeads = new HashMap<>();
    public String mBody;
    public BodySimpleResponse() {
    }
    public BodySimpleResponse(String body) {
        mBody = body;
    }

    public void write(OutputStream outputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mHttpVersion);
        stringBuilder.append(SPACE);
        stringBuilder.append(mStatusCode);
        stringBuilder.append(SPACE);
        stringBuilder.append(mStatusMsg);
        stringBuilder.append(RN);
        if (mBody == null) {
            mBody = "";
        }
        int contentLength = mBody.getBytes().length;
        mHeads.put("Content-Length", contentLength + "");
        mHeads.put("Content-Type", "text/html");
        Iterator<Map.Entry<String, String>> iterator = mHeads.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuilder.append(key);
            stringBuilder.append(HEAD_SPLIT);
            stringBuilder.append(value);
            stringBuilder.append(RN);
        }
        stringBuilder.append(RN);
        stringBuilder.append(mBody);

        try {
//            outputStream.write("test!!!".getBytes());
            outputStream.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


