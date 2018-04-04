package okhttp3;

import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.Util;

public final class Headers$Builder {
    final List<String> namesAndValues = new ArrayList(20);

    Headers$Builder addLenient(String line) {
        int index = line.indexOf(":", 1);
        if (index != -1) {
            return addLenient(line.substring(0, index), line.substring(index + 1));
        }
        if (line.startsWith(":")) {
            return addLenient("", line.substring(1));
        }
        return addLenient("", line);
    }

    public Headers$Builder add(String line) {
        int index = line.indexOf(":");
        if (index != -1) {
            return add(line.substring(0, index).trim(), line.substring(index + 1));
        }
        throw new IllegalArgumentException("Unexpected header: " + line);
    }

    public Headers$Builder add(String name, String value) {
        checkNameAndValue(name, value);
        return addLenient(name, value);
    }

    Headers$Builder addLenient(String name, String value) {
        this.namesAndValues.add(name);
        this.namesAndValues.add(value.trim());
        return this;
    }

    public Headers$Builder removeAll(String name) {
        int i = 0;
        while (i < this.namesAndValues.size()) {
            if (name.equalsIgnoreCase((String) this.namesAndValues.get(i))) {
                this.namesAndValues.remove(i);
                this.namesAndValues.remove(i);
                i -= 2;
            }
            i += 2;
        }
        return this;
    }

    public Headers$Builder set(String name, String value) {
        checkNameAndValue(name, value);
        removeAll(name);
        addLenient(name, value);
        return this;
    }

    private void checkNameAndValue(String name, String value) {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (name.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        } else {
            int i;
            char c;
            int length = name.length();
            for (i = 0; i < length; i++) {
                c = name.charAt(i);
                if (c <= ' ' || c >= '') {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", new Object[]{Integer.valueOf(c), Integer.valueOf(i), name}));
                }
            }
            if (value == null) {
                throw new NullPointerException("value for name " + name + " == null");
            }
            i = 0;
            length = value.length();
            while (i < length) {
                c = value.charAt(i);
                if ((c > '\u001f' || c == '\t') && c < '') {
                    i++;
                } else {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", new Object[]{Integer.valueOf(c), Integer.valueOf(i), name, value}));
                }
            }
        }
    }

    public String get(String name) {
        for (int i = this.namesAndValues.size() - 2; i >= 0; i -= 2) {
            if (name.equalsIgnoreCase((String) this.namesAndValues.get(i))) {
                return (String) this.namesAndValues.get(i + 1);
            }
        }
        return null;
    }

    public Headers build() {
        return new Headers(this);
    }
}
