package butterknife;

import android.support.annotation.UiThread;

public interface Unbinder {
    public static final Unbinder EMPTY = new C05001();

    static class C05001 implements Unbinder {
        C05001() {
        }

        public void unbind() {
        }
    }

    @UiThread
    void unbind();
}
