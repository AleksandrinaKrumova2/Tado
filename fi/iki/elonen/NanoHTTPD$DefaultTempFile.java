package fi.iki.elonen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NanoHTTPD$DefaultTempFile implements NanoHTTPD$TempFile {
    private final File file;
    private final OutputStream fstream = new FileOutputStream(this.file);

    public NanoHTTPD$DefaultTempFile(File tempdir) throws IOException {
        this.file = File.createTempFile("NanoHTTPD-", "", tempdir);
    }

    public void delete() throws Exception {
        NanoHTTPD.access$000(this.fstream);
        if (!this.file.delete()) {
            throw new Exception("could not delete temporary file: " + this.file.getAbsolutePath());
        }
    }

    public String getName() {
        return this.file.getAbsolutePath();
    }

    public OutputStream open() throws Exception {
        return this.fstream;
    }
}
