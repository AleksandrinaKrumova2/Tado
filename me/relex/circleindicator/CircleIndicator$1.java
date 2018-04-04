package me.relex.circleindicator;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

class CircleIndicator$1 implements OnPageChangeListener {
    final /* synthetic */ CircleIndicator this$0;

    CircleIndicator$1(CircleIndicator circleIndicator) {
        this.this$0 = circleIndicator;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
        if (CircleIndicator.access$100(this.this$0).getAdapter() != null && CircleIndicator.access$100(this.this$0).getAdapter().getCount() > 0) {
            if (CircleIndicator.access$200(this.this$0).isRunning()) {
                CircleIndicator.access$200(this.this$0).end();
                CircleIndicator.access$200(this.this$0).cancel();
            }
            if (CircleIndicator.access$300(this.this$0).isRunning()) {
                CircleIndicator.access$300(this.this$0).end();
                CircleIndicator.access$300(this.this$0).cancel();
            }
            if (CircleIndicator.access$400(this.this$0) >= 0) {
                View currentIndicator = this.this$0.getChildAt(CircleIndicator.access$400(this.this$0));
                if (currentIndicator != null) {
                    currentIndicator.setBackgroundResource(CircleIndicator.access$500(this.this$0));
                    CircleIndicator.access$200(this.this$0).setTarget(currentIndicator);
                    CircleIndicator.access$200(this.this$0).start();
                }
            }
            View selectedIndicator = this.this$0.getChildAt(position);
            if (selectedIndicator != null) {
                selectedIndicator.setBackgroundResource(CircleIndicator.access$600(this.this$0));
                CircleIndicator.access$300(this.this$0).setTarget(selectedIndicator);
                CircleIndicator.access$300(this.this$0).start();
            }
            CircleIndicator.access$402(this.this$0, position);
        }
    }

    public void onPageScrollStateChanged(int state) {
    }
}
