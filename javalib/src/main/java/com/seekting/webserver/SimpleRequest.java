package com.seekting.webserver;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by seekting on 17-8-4.
 */

public class SimpleRequest {
    public String mMethod;
    public String mPath;
    public String mHttpVersion;
    public Map<String, String> mHeads = new LinkedHashMap<>();
    public String mBody;
    public byte[] mBytes;
    public static final byte[] BYTES = "\r\n\r\n".getBytes();


    public SimpleRequest(byte[] entry) {
        this.mBytes = entry;
        String readStr = new String(entry);
        String[] lines = readStr.split("\r\n");
        for (String line : lines) {
            System.out.println(line);

        }
        if (lines[0] != null) {
            String[] protl = lines[0].split(" ");
            if (protl != null) {
                if (protl.length > 0) {
                    mMethod = protl[0];
                }
                if (protl.length > 1) {
                    mPath = protl[1];
                }
                if (protl.length > 2) {
                    mHttpVersion = protl[2];
                }
            }

        }

        int entryLength = entry.length;
        //header
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            if (line != null) {
                if (line.equals("\r\n")) {
                    break;
                } else {
                    String[] split = line.split(":");
                    if (split != null && split.length > 1) {
                        mHeads.put(split[0], split[1]);
                    }

                }
            }

        }
        //body
        int i;
        if (entryLength > 4) {
            for (i = 0; i < entry.length - 4; i++) {
                if (entry[i + 0] == BYTES[0]) {
                    if (entry[i + 1] == BYTES[1]) {
                        if (entry[i + 2] == BYTES[2]) {
                            if (entry[i + 3] == BYTES[3]) {
                                break;
                            }
                        }
                    }

                }
            }

            if (i < entryLength - 4) {
                int from = i + 4;
                int offset = entry.length - from;
                mBody = new String(entry, from, offset);
            }
        }
    }
}
