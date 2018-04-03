package fi.iki.elonen;

public interface NanoHTTPD$TempFileManager {
    void clear();

    NanoHTTPD$TempFile createTempFile(String str) throws Exception;
}
