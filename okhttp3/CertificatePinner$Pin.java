package okhttp3;

import okio.ByteString;

final class CertificatePinner$Pin {
    private static final String WILDCARD = "*.";
    final String canonicalHostname;
    final ByteString hash;
    final String hashAlgorithm;
    final String pattern;

    CertificatePinner$Pin(String pattern, String pin) {
        String host;
        this.pattern = pattern;
        if (pattern.startsWith(WILDCARD)) {
            host = HttpUrl.parse("http://" + pattern.substring(WILDCARD.length())).host();
        } else {
            host = HttpUrl.parse("http://" + pattern).host();
        }
        this.canonicalHostname = host;
        if (pin.startsWith("sha1/")) {
            this.hashAlgorithm = "sha1/";
            this.hash = ByteString.decodeBase64(pin.substring("sha1/".length()));
        } else if (pin.startsWith("sha256/")) {
            this.hashAlgorithm = "sha256/";
            this.hash = ByteString.decodeBase64(pin.substring("sha256/".length()));
        } else {
            throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + pin);
        }
        if (this.hash == null) {
            throw new IllegalArgumentException("pins must be base64: " + pin);
        }
    }

    boolean matches(String hostname) {
        if (!this.pattern.startsWith(WILDCARD)) {
            return hostname.equals(this.canonicalHostname);
        }
        int firstDot = hostname.indexOf(46);
        if ((hostname.length() - firstDot) - 1 != this.canonicalHostname.length()) {
            return false;
        }
        if (hostname.regionMatches(false, firstDot + 1, this.canonicalHostname, 0, this.canonicalHostname.length())) {
            return true;
        }
        return false;
    }

    public boolean equals(Object other) {
        return (other instanceof CertificatePinner$Pin) && this.pattern.equals(((CertificatePinner$Pin) other).pattern) && this.hashAlgorithm.equals(((CertificatePinner$Pin) other).hashAlgorithm) && this.hash.equals(((CertificatePinner$Pin) other).hash);
    }

    public int hashCode() {
        return ((((this.pattern.hashCode() + 527) * 31) + this.hashAlgorithm.hashCode()) * 31) + this.hash.hashCode();
    }

    public String toString() {
        return this.hashAlgorithm + this.hash.base64();
    }
}
