package com.firebase.jobdispatcher;

import android.net.Uri;
import android.support.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ObservedUri {
    private final int flags;
    private final Uri uri;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
        public static final int FLAG_NOTIFY_FOR_DESCENDANTS = 1;
    }

    public ObservedUri(@NonNull Uri uri, int flags) {
        if (uri == null) {
            throw new IllegalArgumentException("URI must not be null.");
        }
        this.uri = uri;
        this.flags = flags;
    }

    public Uri getUri() {
        return this.uri;
    }

    public int getFlags() {
        return this.flags;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObservedUri)) {
            return false;
        }
        ObservedUri otherUri = (ObservedUri) o;
        if (this.flags == otherUri.flags && this.uri.equals(otherUri.uri)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.uri.hashCode() ^ this.flags;
    }
}
