package fi.iki.elonen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class NanoHTTPD$DefaultTempFileManager implements NanoHTTPD$TempFileManager {
    private final List<NanoHTTPD$TempFile> tempFiles;
    private final File tmpdir = new File(System.getProperty("java.io.tmpdir"));

    public NanoHTTPD$DefaultTempFileManager() {
        if (!this.tmpdir.exists()) {
            this.tmpdir.mkdirs();
        }
        this.tempFiles = new ArrayList();
    }

    public void clear() {
        for (NanoHTTPD$TempFile file : this.tempFiles) {
            try {
                file.delete();
            } catch (Exception ignored) {
                NanoHTTPD.access$200().log(Level.WARNING, "could not delete file ", ignored);
            }
        }
        this.tempFiles.clear();
    }

    public NanoHTTPD$TempFile createTempFile(String filename_hint) throws Exception {
        NanoHTTPD$DefaultTempFile tempFile = new NanoHTTPD$DefaultTempFile(this.tmpdir);
        this.tempFiles.add(tempFile);
        return tempFile;
    }
}
