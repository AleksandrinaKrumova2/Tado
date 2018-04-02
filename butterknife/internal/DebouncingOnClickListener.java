package butterknife.internal;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class DebouncingOnClickListener implements OnClickListener {
    private static final Runnable ENABLE_AGAIN = new C05011();
    static boolean enabled = true;

    static class C05011 implements Runnable {
        C05011() {
        }

        public void run() {
            DebouncingOnClickListener.enabled = true;
        }
    }

    public abstract void doClick(View view);

    public final void onClick(View v) {
        if (enabled) {
            enabled = false;
            v.post(ENABLE_AGAIN);
            doClick(v);
        }
    }
}
