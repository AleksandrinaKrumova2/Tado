package com.firebase.jobdispatcher;

import android.net.Uri;
import java.util.List;

public class TriggerReason {
    private final List<Uri> triggeredContentUris;

    TriggerReason(List<Uri> mTriggeredContentUris) {
        this.triggeredContentUris = mTriggeredContentUris;
    }

    public List<Uri> getTriggeredContentUris() {
        return this.triggeredContentUris;
    }
}
