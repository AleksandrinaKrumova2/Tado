package com.tado.android.views.loadingindicator;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.utils.Snitcher;
import org.joda.time.DateTimeConstants;

public class LoadingView extends FrameLayout {
    private final String TAG = "LoadingView";
    private Drawable doneIcon;
    private ImageView doneIconImageView;
    private Drawable errorIcon;
    private ValueAnimator expandAnimator;
    private int expandDelay = 7000;
    private int firstStageDelay = 1000;
    private float firstStagePercentage = 35.0f;
    private boolean isExpanded = false;
    private float maxHeight = 16.0f;
    private ObjectAnimator progressAnimator;
    private ProgressBar progressBar;
    private int secondStageDelay = DateTimeConstants.MILLIS_PER_MINUTE;
    private float secondStagePercentage = 95.0f;
    private long startTime = -1;
    private String text;
    private TextView updatingTextView;

    class C12911 implements AnimatorListener {
        boolean cancelled = false;

        C12911() {
        }

        public void onAnimationStart(Animator animation) {
            LoadingView.this.createShowAnimator().start();
            LoadingView.this.isExpanded = false;
            this.cancelled = false;
        }

        public void onAnimationEnd(Animator animation) {
            Snitcher.start().log("LoadingView", "animation ended. cancelled: %b", Boolean.valueOf(this.cancelled));
            if (!this.cancelled) {
                LoadingView.this.progressAnimator = LoadingView.this.createProgressAnimatorMiddle();
                LoadingView.this.progressAnimator.start();
            }
        }

        public void onAnimationCancel(Animator animation) {
            this.cancelled = true;
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    class C12922 implements AnimatorListener {
        boolean cancelled;

        C12922() {
        }

        public void onAnimationStart(Animator animation) {
            this.cancelled = false;
        }

        public void onAnimationEnd(Animator animation) {
            if (!this.cancelled) {
                LoadingView.this.createHideAnimator(0).start();
            }
        }

        public void onAnimationCancel(Animator animation) {
            this.cancelled = true;
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    class C12944 implements Runnable {
        C12944() {
        }

        public void run() {
            LoadingView.this.updatingTextView.setVisibility(8);
            LoadingView.this.updatingTextView.setTranslationY(0.0f);
        }
    }

    class C12955 implements Runnable {
        C12955() {
        }

        public void run() {
            LoadingView.this.updatingTextView.setTranslationY(0.0f);
            LoadingView.this.updatingTextView.setVisibility(0);
        }
    }

    class C12966 implements Runnable {
        C12966() {
        }

        public void run() {
            LoadingView.this.createHideAnimator(300).start();
        }
    }

    class C12988 implements Runnable {
        C12988() {
        }

        public void run() {
            LoadingView.this.doneIconImageView.setTranslationY(-LoadingView.this.maxHeight);
            LoadingView.this.doneIconImageView.setVisibility(8);
            LoadingView.this.isExpanded = false;
            Snitcher.start().log("LoadingView", "start hide animator isExpanded %b", Boolean.valueOf(LoadingView.this.isExpanded));
        }
    }

    class C12999 implements AnimatorUpdateListener {
        C12999() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            LayoutParams layoutParams = LoadingView.this.getLayoutParams();
            layoutParams.height = (int) (((float) ((Integer) animation.getAnimatedValue()).intValue()) * LoadingView.this.getResources().getDisplayMetrics().density);
            LoadingView.this.setLayoutParams(layoutParams);
        }
    }

    public LoadingView(Context context) {
        super(context);
        init(null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, C0676R.styleable.LoadingView, 0, 0);
            try {
                this.maxHeight = a.getDimension(5, getResources().getDimension(C0676R.dimen.loading_view_max_height));
                this.text = a.getString(8);
                this.doneIcon = a.getDrawable(0);
                this.errorIcon = a.getDrawable(1);
                this.firstStagePercentage = a.getFloat(4, 35.0f);
                this.secondStagePercentage = a.getFloat(7, 95.0f);
                this.firstStageDelay = a.getInt(3, 1000);
                this.secondStageDelay = a.getInt(6, DateTimeConstants.MILLIS_PER_MINUTE);
                this.expandDelay = a.getInt(2, 7000);
            } catch (Exception e) {
                Snitcher.start().logException("LoadingView", "init", e);
            } finally {
                a.recycle();
            }
        }
        this.text = this.text == null ? getResources().getString(C0676R.string.components_loadingIndicator_refreshingLoadingLabel) : this.text;
        this.doneIcon = this.doneIcon == null ? ContextCompat.getDrawable(getContext(), C0676R.drawable.ic_done_white_24dp) : this.doneIcon;
        View view = LayoutInflater.from(getContext()).inflate(C0676R.layout.loading_view, this, true);
        this.progressBar = (ProgressBar) view.findViewById(C0676R.id.progressBar);
        this.progressBar.setMax(100);
        this.updatingTextView = (TextView) view.findViewById(C0676R.id.updatingText);
        this.updatingTextView.setText(this.text);
        this.doneIconImageView = (ImageView) view.findViewById(C0676R.id.done);
        this.doneIconImageView.setImageDrawable(this.doneIcon);
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        Snitcher.start().log("LoadingView", "%d start %d", Integer.valueOf(System.identityHashCode(this)), Long.valueOf(this.startTime));
        if (this.progressAnimator == null || !this.progressAnimator.isRunning()) {
            this.expandAnimator = createExpandDelayed(this.expandDelay);
            this.progressAnimator = createProgressAnimatorFirstPart();
            if (!this.expandAnimator.isRunning()) {
                this.expandAnimator.cancel();
            }
            LayoutParams lp = getLayoutParams();
            lp.height = (int) getResources().getDimension(C0676R.dimen.loading_view_min_height);
            setLayoutParams(lp);
            this.updatingTextView.setVisibility(8);
            this.isExpanded = false;
            this.progressAnimator = createProgressAnimatorFirstPart();
            this.progressAnimator.start();
            this.expandAnimator.start();
            return;
        }
        Snitcher.start().log("LoadingView", "cancel", new Object[0]);
    }

    public void error() {
        finish(true);
    }

    public void finish() {
        finish(false);
    }

    private void finish(boolean withError) {
        Snitcher.start().log(4, "LoadingView", "elapsed %d ms", Long.valueOf(System.currentTimeMillis() - this.startTime));
        if (this.progressAnimator != null) {
            this.progressAnimator.cancel();
            if (!this.expandAnimator.isRunning()) {
                this.expandAnimator.cancel();
            }
            this.progressAnimator = createProgressAnimatorToEnd(withError);
            this.progressAnimator.start();
        }
    }

    protected ObjectAnimator createProgressAnimatorFirstPart() {
        ObjectAnimator firstStageAnimator = ObjectAnimator.ofInt(this.progressBar, NotificationCompat.CATEGORY_PROGRESS, new int[]{0, (int) this.firstStagePercentage});
        firstStageAnimator.setDuration((long) this.firstStageDelay);
        firstStageAnimator.addListener(new C12911());
        return firstStageAnimator;
    }

    protected ObjectAnimator createProgressAnimatorMiddle() {
        ObjectAnimator secondStageAnimator = ObjectAnimator.ofInt(this.progressBar, NotificationCompat.CATEGORY_PROGRESS, new int[]{(int) this.firstStagePercentage, (int) this.secondStagePercentage});
        secondStageAnimator.setDuration((long) this.secondStageDelay);
        secondStageAnimator.addListener(new C12922());
        return secondStageAnimator;
    }

    protected ObjectAnimator createProgressAnimatorToEnd(final boolean withError) {
        ObjectAnimator thirdStageAnimator = ObjectAnimator.ofInt(this.progressBar, NotificationCompat.CATEGORY_PROGRESS, new int[]{this.progressBar.getProgress(), 100});
        thirdStageAnimator.setDuration(300);
        thirdStageAnimator.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                Snitcher.start().log("LoadingView", "end progress animator to end isExpanded %b", Boolean.valueOf(LoadingView.this.isExpanded));
                if (LoadingView.this.isExpanded) {
                    LoadingView.this.startTextAnimation(withError);
                } else {
                    LoadingView.this.createHideAnimator(0).start();
                }
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        return thirdStageAnimator;
    }

    protected void startTextAnimation(final boolean withError) {
        ViewPropertyAnimatorCompat textAnimation = ViewCompat.animate(this.updatingTextView).setDuration(100).withStartAction(new C12955()).withEndAction(new C12944()).translationY((float) this.updatingTextView.getHeight());
        ViewPropertyAnimatorCompat iconAnimation = ViewCompat.animate(this.doneIconImageView).setDuration(100).withStartAction(new Runnable() {
            public void run() {
                LoadingView.this.doneIconImageView.setTranslationY(-30.0f);
                LoadingView.this.doneIconImageView.setVisibility(0);
                if (withError) {
                    LoadingView.this.doneIconImageView.setImageDrawable(LoadingView.this.errorIcon);
                } else {
                    LoadingView.this.doneIconImageView.setImageDrawable(LoadingView.this.doneIcon);
                }
            }
        }).withEndAction(new C12966()).translationY(0.0f);
        textAnimation.start();
        iconAnimation.start();
    }

    protected ViewPropertyAnimatorCompat createShowAnimator() {
        return ViewCompat.animate(this).alpha(1.0f).scaleY(1.0f).setDuration(200);
    }

    protected ViewPropertyAnimatorCompat createHideAnimator(int delay) {
        return ViewCompat.animate(this).alpha(0.0f).scaleY(0.0f).setStartDelay((long) (delay + 1)).withStartAction(new C12988()).setDuration(200);
    }

    protected ValueAnimator createExpandDelayed(int delay) {
        int from = (int) getResources().getDimension(C0676R.dimen.loading_view_min_height);
        int to = (int) getResources().getDimension(C0676R.dimen.loading_view_max_height);
        ValueAnimator expandAnimator = ValueAnimator.ofInt(new int[]{from, to});
        expandAnimator.setDuration(100);
        expandAnimator.setStartDelay((long) delay);
        expandAnimator.addUpdateListener(new C12999());
        expandAnimator.addListener(new AnimatorListener() {
            boolean cancelled = false;

            public void onAnimationStart(Animator animation) {
                LoadingView.this.updatingTextView.setVisibility(0);
                LoadingView.this.isExpanded = false;
                this.cancelled = false;
                Snitcher.start().log("LoadingView", "start expand animator isExpanded %b", Boolean.valueOf(LoadingView.this.isExpanded));
            }

            public void onAnimationEnd(Animator animation) {
                if (!this.cancelled) {
                    LoadingView.this.isExpanded = true;
                    Snitcher.start().log("LoadingView", "end expand animator isExpanded %b", Boolean.valueOf(LoadingView.this.isExpanded));
                }
            }

            public void onAnimationCancel(Animator animation) {
                this.cancelled = true;
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        return expandAnimator;
    }
}
