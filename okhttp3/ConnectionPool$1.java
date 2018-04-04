package okhttp3;

class ConnectionPool$1 implements Runnable {
    final /* synthetic */ ConnectionPool this$0;

    ConnectionPool$1(ConnectionPool this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        while (true) {
            long waitNanos = this.this$0.cleanup(System.nanoTime());
            if (waitNanos != -1) {
                if (waitNanos > 0) {
                    long waitMillis = waitNanos / 1000000;
                    waitNanos -= waitMillis * 1000000;
                    synchronized (this.this$0) {
                        try {
                            this.this$0.wait(waitMillis, (int) waitNanos);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            } else {
                return;
            }
        }
    }
}
