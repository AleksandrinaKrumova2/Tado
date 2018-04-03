package com.tado.android.times.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.nhaarman.supertooltips.ToolTipView;
import com.tado.android.rest.model.Block;
import com.tado.android.schedule.model.ScheduleDayEnum;
import com.tado.android.times.view.BubbleDrawable.BubbleListener;
import com.tado.android.times.view.interfaces.ITimeBlocksView;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.TimeUtils;
import com.tado.android.utils.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TimeBlocksView2 extends LinearLayout implements ITimeBlocksView {
    private static final float HIGH_FRICTION = 0.1f;
    private static final int KEY_EDITED_BLOCK_INDEX = 1;
    private static final int KEY_ORIGINAL_WIDTH = 0;
    public static final int LONG_PRESS_LEFT_OVERLAP = -16;
    public static final int LONG_PRESS_SELECTION_WIDTH = 60;
    private static final float NO_FRICTION = 1.0f;
    private OnClickListener _onBlockClickListener;
    private List<Block> blockList;
    private SparseArray<String> blockViewMap;
    private BubbleListener bubbleListener;
    private ScheduleDayEnum day;
    private float density;
    private int draggingOriginX;
    private int draggingOriginY;
    private BlockView editedBlock;
    private final Handler handler;
    private boolean hasChanges;
    private boolean hasDeletion;
    private Rect hit;
    private boolean is24HoursFormat;
    private boolean isAddingMode;
    private boolean isEditingMode;
    private boolean isZoomedIn;
    private boolean isZoomedWithLongPress;
    Runnable longPressRunnable;
    private OnBlockClickListener onBlockClickListener;
    private OnBlockSplitListener onBlockSplitListener;
    private OnBlockZoomed onBlockZoomedListener;
    private OnEditModeListener onEditModeListener;
    private SparseIntArray tempValues;
    private BubbleDrawable timeBubble;
    private String[] timeBubbleString;

    public interface OnBlockClickListener {
        void onBlockClick(String str, ScheduleDayEnum scheduleDayEnum);
    }

    public interface OnBlockSplitListener {
        void onBlockSplit(BlockView blockView, Block block, ScheduleDayEnum scheduleDayEnum, List<Block> list);
    }

    public interface OnBlockZoomed {
        void onDayZoomedIn();

        void onDayZoomedOut();
    }

    public interface OnEditModeListener {
        void onEditModeEnd(ScheduleDayEnum scheduleDayEnum, List<Block> list, boolean z, boolean z2);

        void onEditModeStart(ScheduleDayEnum scheduleDayEnum);
    }

    class C12281 implements BubbleListener {
        C12281() {
        }

        public void onUpdate() {
            TimeBlocksView2.this.invalidate();
        }

        public void onReverseStart() {
        }

        public void onReverseEnd() {
            TimeBlocksView2.this.editedBlock = null;
            TimeBlocksView2.this.setParentTreeClippingEnabled(TimeBlocksView2.this, true);
        }

        public void onStart() {
            TimeBlocksView2.this.setParentTreeClippingEnabled(TimeBlocksView2.this, false);
        }

        public void onEnd() {
        }
    }

    class C12292 implements OnClickListener {
        C12292() {
        }

        public void onClick(View v) {
            BlockView blockView = (BlockView) v;
            if (!blockView.shouldExpand()) {
                if (TimeBlocksView2.this.isAddingMode) {
                    if (blockView.canSplit()) {
                        TimeBlocksView2.this.split(blockView).setOnClickListener(this);
                        blockView.setOnClickListener(this);
                    }
                } else if (TimeBlocksView2.this.onBlockClickListener != null) {
                    TimeBlocksView2.this.onBlockClickListener.onBlockClick((String) TimeBlocksView2.this.blockViewMap.get(v.getId()), TimeBlocksView2.this.day);
                }
            }
        }
    }

    class C12303 implements AnimatorUpdateListener {
        C12303() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            LayoutParams params = TimeBlocksView2.this.getLayoutParams();
            params.width = ((Integer) animation.getAnimatedValue()).intValue();
            TimeBlocksView2.this.setLayoutParams(params);
        }
    }

    class C12314 implements AnimatorUpdateListener {
        C12314() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            LayoutParams params = TimeBlocksView2.this.getLayoutParams();
            params.width = ((Integer) animation.getAnimatedValue()).intValue();
            TimeBlocksView2.this.setLayoutParams(params);
        }
    }

    class C12325 implements Runnable {
        C12325() {
        }

        public void run() {
            if (TimeBlocksView2.this.shouldZoomIn((float) TimeBlocksView2.this.draggingOriginX)) {
                Snitcher.start().log("TimeBlocksView", "longpress zoom in %f", Integer.valueOf(TimeBlocksView2.this.draggingOriginX));
                TimeBlocksView2.this.zoomIn((float) TimeBlocksView2.this.draggingOriginX);
                TimeBlocksView2.this.isZoomedWithLongPress = true;
                return;
            }
            TimeBlocksView2.this.isZoomedWithLongPress = false;
            BlockView blockView = TimeBlocksView2.this.getBlockAt(TimeBlocksView2.this.draggingOriginX, (int) (-16.0f * TimeBlocksView2.this.density), (int) (BitmapDescriptorFactory.HUE_YELLOW * TimeBlocksView2.this.density));
            if (blockView == null || TimeBlocksView2.this.indexOfChild(blockView) <= 0) {
                Snitcher.start().log("TimeBlocksView", "longpress context menu %f", Integer.valueOf(TimeBlocksView2.this.draggingOriginX));
                return;
            }
            Snitcher.start().log("TimeBlocksView", "longpress edit %f", Integer.valueOf(TimeBlocksView2.this.draggingOriginX));
            TimeBlocksView2.this.setEditingMode(true, blockView);
            TimeBlocksView2.this.setParentTreeClippingEnabled(TimeBlocksView2.this, false);
            TimeBlocksView2.this.timeBubble.startAnimation();
        }
    }

    public TimeBlocksView2(Context context) {
        super(context);
        this.isAddingMode = false;
        this.isZoomedIn = false;
        this.hasChanges = false;
        this.hasDeletion = false;
        this.density = getResources().getDisplayMetrics().density;
        this.isZoomedWithLongPress = false;
        this.handler = new Handler();
        this.is24HoursFormat = false;
        this.hit = new Rect();
        this.timeBubbleString = new String[2];
        this.bubbleListener = new C12281();
        this._onBlockClickListener = new C12292();
        this.longPressRunnable = new C12325();
        init(context);
    }

    public TimeBlocksView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isAddingMode = false;
        this.isZoomedIn = false;
        this.hasChanges = false;
        this.hasDeletion = false;
        this.density = getResources().getDisplayMetrics().density;
        this.isZoomedWithLongPress = false;
        this.handler = new Handler();
        this.is24HoursFormat = false;
        this.hit = new Rect();
        this.timeBubbleString = new String[2];
        this.bubbleListener = new C12281();
        this._onBlockClickListener = new C12292();
        this.longPressRunnable = new C12325();
        init(context);
    }

    public TimeBlocksView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.isAddingMode = false;
        this.isZoomedIn = false;
        this.hasChanges = false;
        this.hasDeletion = false;
        this.density = getResources().getDisplayMetrics().density;
        this.isZoomedWithLongPress = false;
        this.handler = new Handler();
        this.is24HoursFormat = false;
        this.hit = new Rect();
        this.timeBubbleString = new String[2];
        this.bubbleListener = new C12281();
        this._onBlockClickListener = new C12292();
        this.longPressRunnable = new C12325();
        init(context);
    }

    public TimeBlocksView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.isAddingMode = false;
        this.isZoomedIn = false;
        this.hasChanges = false;
        this.hasDeletion = false;
        this.density = getResources().getDisplayMetrics().density;
        this.isZoomedWithLongPress = false;
        this.handler = new Handler();
        this.is24HoursFormat = false;
        this.hit = new Rect();
        this.timeBubbleString = new String[2];
        this.bubbleListener = new C12281();
        this._onBlockClickListener = new C12292();
        this.longPressRunnable = new C12325();
        init(context);
    }

    void init(Context context) {
        this.blockViewMap = new SparseArray();
        this.tempValues = new SparseIntArray(4);
        this.timeBubble = new BubbleDrawable(context, this.bubbleListener);
        BlockView.PIXELS_PER_MINUTE = ((float) ((getResources().getDisplayMetrics().widthPixels - getPaddingLeft()) - getPaddingRight())) / 1440.0f;
        setWillNotDraw(false);
        this.is24HoursFormat = DateFormat.is24HourFormat(context);
    }

    public void setBlockList(List<Block> blockList, ScheduleDayEnum day) {
        this.blockList = blockList;
        this.day = day;
        for (Block block : this.blockList) {
            boolean z;
            Context context = getContext();
            int startSeconds = block.getStartSeconds() / 60;
            int endSeconds = block.getEndSeconds() / 60;
            if (block.isGeolocationOverride()) {
                z = false;
            } else {
                z = true;
            }
            BlockView blockView = new BlockView(context, startSeconds, endSeconds, false, z, block.getSetting());
            this.blockViewMap.put(blockView.getId(), block.getId());
            addBlock(blockView);
        }
    }

    public void addBlock(BlockView blockView) {
        removeOverlappedViews(blockView.getStartTime(), blockView.getEndTime());
        int position = getPositionIndexForStartTime(blockView.getStartTime());
        if (position == 0) {
            addAtBeginning(blockView);
        } else if (position == -1) {
            addAtEnd(blockView);
        } else {
            addAt(blockView, position);
        }
        blockView.setOnClickListener(this._onBlockClickListener);
    }

    private void addAtBeginning(BlockView blockView) {
        ((BlockView) getChildAt(0)).setStartTime(blockView.getEndTime());
        addView(blockView, 0);
    }

    private void addAtEnd(BlockView blockView) {
        BlockView replaced = (BlockView) getChildAt(getChildCount() - 1);
        if (replaced != null) {
            replaced.setEndTime(blockView.getStartTime());
        }
        addView(blockView);
    }

    private void addAt(BlockView blockView, int position) {
        BlockView before = (BlockView) getChildAt(position - 1);
        if (before != null) {
            before.setEndTime(blockView.getStartTime());
        }
        BlockView after = (BlockView) getChildAt(position);
        if (after != null) {
            after.setStartTime(blockView.getEndTime());
        }
        addView(blockView, position);
    }

    private int getPositionIndexForStartTime(int startTime) {
        if (getChildCount() == 0) {
            return -1;
        }
        if (startTime == 0) {
            return 0;
        }
        int max = getChildCount();
        for (int index = 0; index < max; index++) {
            if (((BlockView) getChildAt(index)).getStartTime() >= startTime) {
                return index;
            }
        }
        return -1;
    }

    private void removeOverlappedViews(int startTime, int endTime) {
        List<View> dirty = new ArrayList();
        int max = getChildCount();
        for (int index = 0; index < max; index++) {
            BlockView view = (BlockView) getChildAt(index);
            if (view.getStartTime() >= startTime && view.getEndTime() <= endTime) {
                dirty.add(view);
            }
        }
        for (View view2 : dirty) {
            removeView(view2);
        }
    }

    public void setAddMode(boolean enabled) {
        this.isAddingMode = enabled;
        int max = getChildCount();
        for (int index = 0; index < max; index++) {
            ((BlockView) getChildAt(index)).setAddMode(this.isAddingMode);
        }
    }

    public boolean isAddModeEnabled() {
        return this.isAddingMode;
    }

    public boolean isZoomedIn() {
        return this.isZoomedIn;
    }

    public BlockView split(BlockView view) throws UnsupportedOperationException {
        Block dataBlock = getBlockWithViewId(view.getId());
        if (dataBlock == null || !dataBlock.canSplit()) {
            return null;
        }
        Block newDataBlock = dataBlock.splitBlock((this.blockList.size() + 1) + "");
        BlockView newBlockView = BlockView.copy(view);
        view.setEndTime(dataBlock.getEndSeconds() / 60);
        newBlockView.setStartTime(newDataBlock.getStartSeconds() / 60);
        addBlock(newBlockView);
        if (this.onBlockSplitListener == null) {
            return newBlockView;
        }
        this.onBlockSplitListener.onBlockSplit(view, newDataBlock, this.day, this.blockList);
        return newBlockView;
    }

    public void clear() {
        removeAllViews();
    }

    private Block getBlockWithViewId(int id) {
        String blockId = (String) this.blockViewMap.get(id);
        for (Block block : this.blockList) {
            if (block.getId().equalsIgnoreCase(blockId)) {
                return block;
            }
        }
        return null;
    }

    private void setZoomedIn(boolean isZoomedIn) {
        this.isZoomedIn = isZoomedIn;
        if (this.onBlockZoomedListener == null) {
            return;
        }
        if (isZoomedIn) {
            this.onBlockZoomedListener.onDayZoomedIn();
        } else {
            this.onBlockZoomedListener.onDayZoomedOut();
        }
    }

    public boolean shouldZoomIn(float x) {
        for (BlockView view : getBlocksAround((int) x, (int) (((double) (this.density * 48.0f)) / 1.5d), 0)) {
            if (view.shouldExpand()) {
                return true;
            }
        }
        return false;
    }

    public boolean zoomIn(float x) {
        boolean zoomed = false;
        List<BlockView> blocks = getBlocksAround((int) x, (int) (((double) (this.density * 48.0f)) / 1.5d), 0);
        List<Animator> animators = new ArrayList();
        if (blocks.size() > 0) {
            for (BlockView view : blocks) {
                if (view.shouldExpand()) {
                    animators.add(view.getExpandAnimator());
                    zoomed = true;
                }
                view.dispatchTouchEvent(MotionEvent.obtain(0, 0, 3, x, 0.0f, 0));
            }
        }
        if (zoomed) {
            float percetangeBasedOnScreenX = x / ((float) ((ViewGroup) getParent()).getWidth());
            int growFactor = Math.round(75.0f * this.density);
            float targetTranslationX = ((-1.0f * percetangeBasedOnScreenX) * ((float) animators.size())) * ((float) growFactor);
            animators.add(ObjectAnimator.ofObject(this, ToolTipView.TRANSLATION_X_COMPAT, new FloatEvaluator(), new Object[]{Float.valueOf(getTranslationX()), Float.valueOf(targetTranslationX)}));
            this.tempValues.put(0, getWidth());
            ValueAnimator grow = ValueAnimator.ofObject(new IntEvaluator(), new Object[]{Integer.valueOf(originalWidth), Integer.valueOf((numberOfBlocksThatHaveGrown * growFactor) + originalWidth)});
            grow.addUpdateListener(new C12303());
            animators.add(grow);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animators);
            set.start();
            setZoomedIn(true);
        }
        return zoomed;
    }

    public void zoomOut() {
        List<Animator> animators = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            BlockView view = (BlockView) getChildAt(i);
            if (view.isExpanded) {
                animators.add(view.getCollapseAnimator());
            }
        }
        if (animators.size() > 0) {
            animators.add(ObjectAnimator.ofObject(this, ToolTipView.TRANSLATION_X_COMPAT, new FloatEvaluator(), new Object[]{Float.valueOf(getTranslationX()), Integer.valueOf(0)}));
            int width = getWidth();
            int originalWidth = this.tempValues.get(0);
            ValueAnimator grow = ValueAnimator.ofObject(new IntEvaluator(), new Object[]{Integer.valueOf(width), Integer.valueOf(originalWidth)});
            grow.addUpdateListener(new C12314());
            animators.add(grow);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animators);
            set.start();
            setZoomedIn(false);
            LayoutParams params = getLayoutParams();
            params.width = (int) (((float) getWidth()) - (((float) (animators.size() * 75)) * this.density));
            setLayoutParams(params);
        }
    }

    private List<BlockView> getBlocksAround(int x, int extension, int offset) {
        int start = (x - extension) + offset;
        int end = (x + extension) + offset;
        List<BlockView> candidate = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.getLeft() > start && view.getRight() < end) {
                candidate.add((BlockView) view);
            } else if (view.getRight() > start && view.getRight() < end) {
                candidate.add((BlockView) view);
            } else if (view.getLeft() > start && view.getLeft() < end) {
                candidate.add((BlockView) view);
            }
        }
        return candidate;
    }

    private BlockView getBlockAt(int x, int offset, int width) {
        Rect hit = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.getHitRect(hit);
            hit.left += offset;
            if (width > 0) {
                hit.right = hit.left + width;
            }
            if (hit.contains(x, 0)) {
                return (BlockView) view;
            }
        }
        return null;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Snitcher.start().log("TimeBlocksView", "Touch %s actionmasked %d", ev.toString(), Integer.valueOf(MotionEventCompat.getActionMasked(ev)));
        View view = getBlockAt((int) ev.getX(), 0, 0);
        if (view == null) {
            if (this.editedBlock != null) {
                view = this.editedBlock;
            } else {
                view = new View(getContext());
            }
        }
        switch (MotionEventCompat.getActionMasked(ev)) {
            case 0:
                this.draggingOriginX = (int) ev.getX();
                this.draggingOriginY = (int) ev.getY();
                if (!this.isAddingMode) {
                    this.handler.postDelayed(this.longPressRunnable, (long) ViewConfiguration.getLongPressTimeout());
                }
                this.isZoomedWithLongPress = false;
                if (this.isEditingMode) {
                    requestDisallowInterceptTouchEvent(true);
                }
                view.setOnClickListener(this._onBlockClickListener);
                return view.onTouchEvent(ev);
            case 1:
                this.handler.removeCallbacks(this.longPressRunnable);
                if (this.isEditingMode) {
                    this.timeBubble.reverseAnimation();
                    setEditingMode(false, this.editedBlock);
                    return true;
                } else if (shouldZoomIn(ev.getX())) {
                    zoomIn(ev.getX());
                    view.setOnClickListener(null);
                    view.onTouchEvent(ev);
                    return true;
                } else if (this.isZoomedWithLongPress) {
                    this.isZoomedWithLongPress = false;
                    view.setOnClickListener(null);
                    view.onTouchEvent(ev);
                    return true;
                } else if (this.isAddingMode) {
                    view.onTouchEvent(ev);
                    return true;
                } else {
                    view.onTouchEvent(ev);
                    return true;
                }
            case 2:
                if (Math.abs(((float) this.draggingOriginX) - ev.getX()) > ((float) ViewConfiguration.get(getContext()).getScaledTouchSlop())) {
                    this.handler.removeCallbacks(this.longPressRunnable);
                }
                if (this.isEditingMode) {
                    return handleDragging(ev);
                }
                return true;
            case 3:
                this.handler.removeCallbacks(this.longPressRunnable);
                view.onTouchEvent(ev);
                return true;
            default:
                return true;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    private boolean handleDragging(MotionEvent ev) {
        int minutes = (int) Util.roundToStep((getFriction(ev) * (((float) this.draggingOriginX) - ev.getX())) / BlockView.PIXELS_PER_MINUTE, 5.0f);
        if (((float) Math.abs(minutes)) >= 5.0f) {
            int indexOfView = this.tempValues.get(1);
            if (indexOfView > 0) {
                BlockView previousSibling = (BlockView) getChildAt(indexOfView - 1);
                if (previousSibling.getDuration() - minutes >= 15 && this.editedBlock.getDuration() + minutes >= 15) {
                    previousSibling.setEndTime(previousSibling.getEndTime() - minutes);
                    this.editedBlock.setStartTime(this.editedBlock.getStartTime() - minutes);
                    updateBubbleText(false);
                    this.draggingOriginX = (int) ev.getX();
                } else if (this.editedBlock.getDuration() + minutes <= 5) {
                    this.editedBlock.setStartTime(this.editedBlock.getEndTime());
                    previousSibling.setEndTime(this.editedBlock.getStartTime());
                    updateBubbleText(true);
                    this.draggingOriginX = (int) ev.getX();
                }
            }
        }
        return true;
    }

    private float getFriction(MotionEvent ev) {
        getHitRect(this.hit);
        return (ev.getY() < ((float) this.hit.top) || ev.getY() > ((float) this.hit.bottom)) ? HIGH_FRICTION : NO_FRICTION;
    }

    private void updateBubbleText(boolean showDeleteIcon) {
        if (showDeleteIcon) {
            this.timeBubble.setShowDeleteIcon(true);
            return;
        }
        this.timeBubble.setShowDeleteIcon(false);
        String localizedTime = TimeUtils.formatTimeToAmPm(TimeUtils.getHours(this.editedBlock.getStartTime() * 60), TimeUtils.getMinutesRemainder(this.editedBlock.getStartTime() * 60), this.is24HoursFormat);
        if (this.is24HoursFormat) {
            this.timeBubbleString[0] = localizedTime;
            this.timeBubbleString[1] = null;
            return;
        }
        this.timeBubbleString[0] = localizedTime.substring(0, localizedTime.length() - 3);
        this.timeBubbleString[1] = localizedTime.substring(localizedTime.length() - 2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.editedBlock != null) {
            this.timeBubble.setBounds(this.editedBlock.getLeft() - (this.timeBubble.getIntrinsicWidth() / 2), (int) ((this.density * 25.0f) - ((float) this.timeBubble.getIntrinsicHeight())), this.editedBlock.getLeft() + (this.timeBubble.getIntrinsicWidth() / 2), (int) (this.density * 25.0f));
            this.timeBubble.draw(canvas, this.timeBubbleString, this.editedBlock.getX(), (int) (((float) this.editedBlock.getTop()) - (Constants.MAX_OFFSET_CELSIUS * this.density)), this.is24HoursFormat);
        }
    }

    public synchronized void setEditingMode(boolean editingMode, BlockView block) {
        Snitcher.start().log("TimeBlocksView", "setEditingMode %b", Boolean.valueOf(editingMode));
        if (editingMode) {
            this.editedBlock = block;
            if (this.editedBlock != null) {
                int index = indexOfChild(this.editedBlock);
                if (index > 0) {
                    getChildAt(index - 1).dispatchTouchEvent(MotionEvent.obtain(0, 0, 3, (float) this.draggingOriginX, (float) this.draggingOriginY, 0));
                    this.isEditingMode = true;
                    this.tempValues.put(1, index);
                    this.editedBlock.setEditingMode(true);
                    if (VERSION.SDK_INT >= 21) {
                        this.timeBubble.setTint(this.editedBlock.color.darkColor);
                    } else {
                        this.timeBubble.setColorFilter(this.editedBlock.color.darkColor, Mode.SRC_ATOP);
                    }
                    updateBubbleText(false);
                    this.hasDeletion = false;
                    if (this.onEditModeListener != null) {
                        this.onEditModeListener.onEditModeStart(this.day);
                    }
                } else {
                    throw new IllegalArgumentException("The edited block can't be the first child");
                }
            }
        }
        this.isEditingMode = false;
        if (this.editedBlock != null) {
            this.editedBlock.setEditingMode(false);
            updateBlockList(this.editedBlock);
            updateBlockList((BlockView) getChildAt(this.tempValues.get(1) - 1));
            if (this.onEditModeListener != null) {
                this.onEditModeListener.onEditModeEnd(this.day, this.blockList, this.hasChanges, this.hasDeletion);
            }
        }
        requestDisallowInterceptTouchEvent(editingMode);
        invalidate();
    }

    private void setParentTreeClippingEnabled(ViewGroup view, boolean enabled) {
        ViewGroup parent = view;
        parent.setClipToPadding(enabled);
        parent.setClipChildren(enabled);
        if (view.getParent() instanceof ViewGroup) {
            parent = (ViewGroup) view.getParent();
            parent.setClipToPadding(enabled);
            parent.setClipChildren(enabled);
        }
    }

    private synchronized void updateBlockList(BlockView blockView) {
        Iterator<Block> it = this.blockList.iterator();
        Block block = getBlockWithViewId(blockView.getId());
        while (it.hasNext()) {
            Block modelBlock = (Block) it.next();
            if (modelBlock.equals(block)) {
                if (blockView.getDuration() == 0) {
                    it.remove();
                    ((ViewGroup) blockView.getParent()).removeView(blockView);
                    this.hasChanges = true;
                    this.hasDeletion = true;
                    break;
                }
                if (modelBlock.getStartSeconds() != blockView.getStartTime() * 60) {
                    modelBlock.setStartSeconds(blockView.getStartTime() * 60);
                    this.hasChanges = true;
                }
                if (modelBlock.getEndSeconds() != blockView.getEndTime() * 60) {
                    modelBlock.setEndSeconds(blockView.getEndTime() * 60);
                    this.hasChanges = true;
                }
            }
        }
    }

    public void setOnBlockSplitListener(OnBlockSplitListener listener) {
        this.onBlockSplitListener = listener;
    }

    public void setOnBlockClickListener(OnBlockClickListener listener) {
        this.onBlockClickListener = listener;
    }

    public void setOnBlockZoomedListener(OnBlockZoomed onBlockZoomedListener) {
        this.onBlockZoomedListener = onBlockZoomedListener;
    }

    public void setOnEditModeListener(OnEditModeListener onEditModeListener) {
        this.onEditModeListener = onEditModeListener;
    }

    protected void onDetachedFromWindow() {
        this.handler.removeCallbacks(this.longPressRunnable);
        super.onDetachedFromWindow();
    }
}
