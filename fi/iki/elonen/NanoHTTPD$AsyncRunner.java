package fi.iki.elonen;

public interface NanoHTTPD$AsyncRunner {
    void closeAll();

    void closed(NanoHTTPD$ClientHandler nanoHTTPD$ClientHandler);

    void exec(NanoHTTPD$ClientHandler nanoHTTPD$ClientHandler);
}
