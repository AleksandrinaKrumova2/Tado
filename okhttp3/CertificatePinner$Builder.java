package okhttp3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public final class CertificatePinner$Builder {
    private final List<CertificatePinner$Pin> pins = new ArrayList();

    public CertificatePinner$Builder add(String pattern, String... pins) {
        if (pattern == null) {
            throw new NullPointerException("pattern == null");
        }
        for (String pin : pins) {
            this.pins.add(new CertificatePinner$Pin(pattern, pin));
        }
        return this;
    }

    public CertificatePinner build() {
        return new CertificatePinner(new LinkedHashSet(this.pins), null);
    }
}
