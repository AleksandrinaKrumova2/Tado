package me.relex.circleindicator;

import android.database.DataSetObserver;

class CircleIndicator$2 extends DataSetObserver {
    final /* synthetic */ CircleIndicator this$0;

    CircleIndicator$2(CircleIndicator circleIndicator) {
        this.this$0 = circleIndicator;
    }

    public void onChanged() {
        super.onChanged();
        if (CircleIndicator.access$100(this.this$0) != null) {
            int newCount = CircleIndicator.access$100(this.this$0).getAdapter().getCount();
            if (newCount != this.this$0.getChildCount()) {
                if (CircleIndicator.access$400(this.this$0) < newCount) {
                    CircleIndicator.access$402(this.this$0, CircleIndicator.access$100(this.this$0).getCurrentItem());
                } else {
                    CircleIndicator.access$402(this.this$0, -1);
                }
                CircleIndicator.access$700(this.this$0);
            }
        }
    }
}
