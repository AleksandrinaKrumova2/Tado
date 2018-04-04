package okio;

final class AsyncTimeout$Watchdog extends Thread {
    AsyncTimeout$Watchdog() {
        super("Okio Watchdog");
        setDaemon(true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r3 = this;
    L_0x0000:
        r2 = okio.AsyncTimeout.class;
        monitor-enter(r2);	 Catch:{ InterruptedException -> 0x000e }
        r0 = okio.AsyncTimeout.awaitTimeout();	 Catch:{ all -> 0x000b }
        if (r0 != 0) goto L_0x0010;
    L_0x0009:
        monitor-exit(r2);	 Catch:{ all -> 0x000b }
        goto L_0x0000;
    L_0x000b:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x000b }
        throw r1;	 Catch:{ InterruptedException -> 0x000e }
    L_0x000e:
        r1 = move-exception;
        goto L_0x0000;
    L_0x0010:
        r1 = okio.AsyncTimeout.head;	 Catch:{ all -> 0x000b }
        if (r0 != r1) goto L_0x0019;
    L_0x0014:
        r1 = 0;
        okio.AsyncTimeout.head = r1;	 Catch:{ all -> 0x000b }
        monitor-exit(r2);	 Catch:{ all -> 0x000b }
        return;
    L_0x0019:
        monitor-exit(r2);	 Catch:{ all -> 0x000b }
        r0.timedOut();	 Catch:{ InterruptedException -> 0x000e }
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout$Watchdog.run():void");
    }
}
