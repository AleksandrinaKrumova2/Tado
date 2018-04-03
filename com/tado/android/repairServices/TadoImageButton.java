package com.tado.android.repairServices;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tado.C0676R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001BC\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/tado/android/repairServices/TadoImageButton;", "Landroid/widget/RelativeLayout;", "title", "", "backgroundDrawable", "Landroid/graphics/drawable/Drawable;", "clickHandler", "Lkotlin/Function0;", "", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Ljava/lang/String;Landroid/graphics/drawable/Drawable;Lkotlin/jvm/functions/Function0;Landroid/content/Context;Landroid/util/AttributeSet;I)V", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: TadoImageButton.kt */
public final class TadoImageButton extends RelativeLayout {
    private HashMap _$_findViewCache;
    private final Function0<Unit> clickHandler;

    @JvmOverloads
    public TadoImageButton(@NotNull String title, @NotNull Drawable backgroundDrawable, @NotNull Function0<Unit> clickHandler, @NotNull Context context) {
        this(title, backgroundDrawable, clickHandler, context, null, 0, 48, null);
    }

    @JvmOverloads
    public TadoImageButton(@NotNull String title, @NotNull Drawable backgroundDrawable, @NotNull Function0<Unit> clickHandler, @NotNull Context context, @Nullable AttributeSet attrs) {
        this(title, backgroundDrawable, clickHandler, context, attrs, 0, 32, null);
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    @JvmOverloads
    public /* synthetic */ TadoImageButton(String str, Drawable drawable, Function0 function0, Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, drawable, function0, context, (i2 & 16) != 0 ? (AttributeSet) null : attributeSet, (i2 & 32) != 0 ? 0 : i);
    }

    @JvmOverloads
    public TadoImageButton(@NotNull String title, @NotNull Drawable backgroundDrawable, @NotNull Function0<Unit> clickHandler, @NotNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(backgroundDrawable, "backgroundDrawable");
        Intrinsics.checkParameterIsNotNull(clickHandler, "clickHandler");
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attrs, defStyleAttr);
        this.clickHandler = clickHandler;
        RelativeLayout.inflate(context, C0676R.layout.tado_image_button_view, this);
        TextView textView = (TextView) _$_findCachedViewById(C0676R.id.tadoButtonTitle);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tadoButtonTitle");
        textView.setText(title);
        if (VERSION.SDK_INT >= 16) {
            textView = (TextView) _$_findCachedViewById(C0676R.id.tadoButtonTitle);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tadoButtonTitle");
            textView.setBackground(backgroundDrawable);
        } else {
            ((TextView) _$_findCachedViewById(C0676R.id.tadoButtonTitle)).setBackgroundDrawable(backgroundDrawable);
        }
        int padding = getResources().getDimensionPixelSize(C0676R.dimen.tado_image_button_padding);
        ((TextView) _$_findCachedViewById(C0676R.id.tadoButtonTitle)).setPadding(padding, padding, padding, padding);
        ((TextView) _$_findCachedViewById(C0676R.id.tadoButtonTitle)).setOnClickListener(new OnClickListener() {
            public final void onClick(View it) {
                this.clickHandler.invoke();
            }
        });
    }
}
