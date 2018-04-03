package fi.iki.elonen;

class NanoHTTPD$DefaultTempFileManagerFactory implements NanoHTTPD$TempFileManagerFactory {
    final /* synthetic */ NanoHTTPD this$0;

    private NanoHTTPD$DefaultTempFileManagerFactory(NanoHTTPD nanoHTTPD) {
        this.this$0 = nanoHTTPD;
    }

    public NanoHTTPD$TempFileManager create() {
        return new NanoHTTPD$DefaultTempFileManager();
    }
}
