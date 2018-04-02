package com.google.android.gms.internal;

public abstract class zzaqa extends zzapz {
    private boolean zzdtb;

    protected zzaqa(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
    }

    public final void initialize() {
        zzvf();
        this.zzdtb = true;
    }

    public final boolean isInitialized() {
        return this.zzdtb;
    }

    protected abstract void zzvf();

    protected final void zzxf() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
