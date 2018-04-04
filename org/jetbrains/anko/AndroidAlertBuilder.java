package org.jetbrains.anko;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u00104\u001a\u00020\u0002H\u0016Ji\u00105\u001a\u000206\"\u0004\b\u0000\u001072\f\u00105\u001a\b\u0012\u0004\u0012\u0002H7082K\u00109\u001aG\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H7¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(?\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(@\u0012\u0004\u0012\u0002060:H\u0016JN\u00105\u001a\u0002062\f\u00105\u001a\b\u0012\u0004\u0012\u00020%0826\u00109\u001a2\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(@\u0012\u0004\u0012\u0002060AH\u0016J3\u0010B\u001a\u0002062\u0006\u0010C\u001a\u00020\u001a2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J3\u0010B\u001a\u0002062\u0006\u0010F\u001a\u00020G2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J3\u0010H\u001a\u0002062\u0006\u0010C\u001a\u00020\u001a2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J3\u0010H\u001a\u0002062\u0006\u0010F\u001a\u00020G2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J\u001c\u0010I\u001a\u0002062\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002060EH\u0016JU\u0010K\u001a\u0002062K\u0010J\u001aG\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110M¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(N\u0012\u0004\u0012\u00020 0:H\u0016J3\u0010O\u001a\u0002062\u0006\u0010C\u001a\u00020\u001a2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J3\u0010O\u001a\u0002062\u0006\u0010F\u001a\u00020G2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J\b\u0010P\u001a\u00020\u0002H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8W@VX\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8W@VX\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u00148W@VX\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u001a8W@VX\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010!\u001a\u00020 2\u0006\u0010\n\u001a\u00020 8W@VX\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010&\u001a\u00020%2\u0006\u0010\n\u001a\u00020%8W@VX\u000e¢\u0006\f\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R$\u0010+\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u001a8W@VX\u000e¢\u0006\f\u001a\u0004\b,\u0010\u001d\"\u0004\b-\u0010\u001fR$\u0010.\u001a\u00020%2\u0006\u0010\n\u001a\u00020%8W@VX\u000e¢\u0006\f\u001a\u0004\b/\u0010(\"\u0004\b0\u0010*R$\u00101\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u001a8W@VX\u000e¢\u0006\f\u001a\u0004\b2\u0010\u001d\"\u0004\b3\u0010\u001f¨\u0006Q"}, d2 = {"Lorg/jetbrains/anko/AndroidAlertBuilder;", "Lorg/jetbrains/anko/AlertBuilder;", "Landroid/app/AlertDialog;", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "builder", "Landroid/app/AlertDialog$Builder;", "getCtx", "()Landroid/content/Context;", "value", "Landroid/view/View;", "customTitle", "getCustomTitle", "()Landroid/view/View;", "setCustomTitle", "(Landroid/view/View;)V", "customView", "getCustomView", "setCustomView", "Landroid/graphics/drawable/Drawable;", "icon", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "", "iconResource", "getIconResource", "()I", "setIconResource", "(I)V", "", "isCancelable", "()Z", "setCancelable", "(Z)V", "", "message", "getMessage", "()Ljava/lang/CharSequence;", "setMessage", "(Ljava/lang/CharSequence;)V", "messageResource", "getMessageResource", "setMessageResource", "title", "getTitle", "setTitle", "titleResource", "getTitleResource", "setTitleResource", "build", "items", "", "T", "", "onItemSelected", "Lkotlin/Function3;", "Landroid/content/DialogInterface;", "Lkotlin/ParameterName;", "name", "dialog", "item", "index", "Lkotlin/Function2;", "negativeButton", "buttonTextResource", "onClicked", "Lkotlin/Function1;", "buttonText", "", "neutralPressed", "onCancelled", "handler", "onKeyPressed", "keyCode", "Landroid/view/KeyEvent;", "e", "positiveButton", "show", "commons_release"}, k = 1, mv = {1, 1, 5})
/* compiled from: AndroidAlertBuilder.kt */
public final class AndroidAlertBuilder implements AlertBuilder<AlertDialog> {
    private final Builder builder = new Builder(getCtx());
    @NotNull
    private final Context ctx;

    public AndroidAlertBuilder(@NotNull Context ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        this.ctx = ctx;
    }

    @NotNull
    public Context getCtx() {
        return this.ctx;
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public CharSequence getTitle() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public void setTitle(@NotNull CharSequence value) {
        Intrinsics.checkParameterIsNotNull(value, Param.VALUE);
        this.builder.setTitle(value);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public int getTitleResource() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public void setTitleResource(int value) {
        this.builder.setTitle(value);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public CharSequence getMessage() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public void setMessage(@NotNull CharSequence value) {
        Intrinsics.checkParameterIsNotNull(value, Param.VALUE);
        this.builder.setMessage(value);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public int getMessageResource() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public void setMessageResource(int value) {
        this.builder.setMessage(value);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public Drawable getIcon() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public void setIcon(@NotNull Drawable value) {
        Intrinsics.checkParameterIsNotNull(value, Param.VALUE);
        this.builder.setIcon(value);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public int getIconResource() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public void setIconResource(int value) {
        this.builder.setIcon(value);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public View getCustomTitle() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public void setCustomTitle(@NotNull View value) {
        Intrinsics.checkParameterIsNotNull(value, Param.VALUE);
        this.builder.setCustomTitle(value);
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public View getCustomView() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public void setCustomView(@NotNull View value) {
        Intrinsics.checkParameterIsNotNull(value, Param.VALUE);
        this.builder.setView(value);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Property does not have a getter")
    public boolean isCancelable() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    public void setCancelable(boolean value) {
        this.builder.setCancelable(value);
    }

    public void onCancelled(@NotNull Function1<? super DialogInterface, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.builder.setOnCancelListener(handler == null ? null : new AndroidAlertBuilderKt$sam$OnCancelListener$cc652675(handler));
    }

    public void onKeyPressed(@NotNull Function3<? super DialogInterface, ? super Integer, ? super KeyEvent, Boolean> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.builder.setOnKeyListener(handler == null ? null : new AndroidAlertBuilderKt$sam$OnKeyListener$4694417a(handler));
    }

    public void positiveButton(@NotNull String buttonText, @NotNull Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(buttonText, "buttonText");
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setPositiveButton(buttonText, new AndroidAlertBuilder$positiveButton$1(onClicked));
    }

    public void positiveButton(int buttonTextResource, @NotNull Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setPositiveButton(buttonTextResource, new AndroidAlertBuilder$positiveButton$2(onClicked));
    }

    public void negativeButton(@NotNull String buttonText, @NotNull Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(buttonText, "buttonText");
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setNegativeButton(buttonText, new AndroidAlertBuilder$negativeButton$1(onClicked));
    }

    public void negativeButton(int buttonTextResource, @NotNull Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setNegativeButton(buttonTextResource, new AndroidAlertBuilder$negativeButton$2(onClicked));
    }

    public void neutralPressed(@NotNull String buttonText, @NotNull Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(buttonText, "buttonText");
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setNeutralButton(buttonText, new AndroidAlertBuilder$neutralPressed$1(onClicked));
    }

    public void neutralPressed(int buttonTextResource, @NotNull Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setNeutralButton(buttonTextResource, new AndroidAlertBuilder$neutralPressed$2(onClicked));
    }

    public void items(@NotNull List<? extends CharSequence> items, @NotNull Function2<? super DialogInterface, ? super Integer, Unit> onItemSelected) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onItemSelected, "onItemSelected");
        Builder builder = this.builder;
        int size = items.size();
        Object[] result$iv = new String[size];
        int i$iv = 0;
        size--;
        if (0 <= size) {
            while (true) {
                result$iv[i$iv] = items.get(i$iv).toString();
                if (i$iv == size) {
                    break;
                }
                i$iv++;
            }
        }
        builder.setItems((CharSequence[]) result$iv, new AndroidAlertBuilder$items$2(onItemSelected));
    }

    public <T> void items(@NotNull List<? extends T> items, @NotNull Function3<? super DialogInterface, ? super T, ? super Integer, Unit> onItemSelected) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onItemSelected, "onItemSelected");
        Builder builder = this.builder;
        int size = items.size();
        Object[] result$iv = new String[size];
        int i$iv = 0;
        size--;
        if (0 <= size) {
            while (true) {
                result$iv[i$iv] = String.valueOf(items.get(i$iv));
                if (i$iv == size) {
                    break;
                }
                i$iv++;
            }
        }
        builder.setItems((CharSequence[]) result$iv, new AndroidAlertBuilder$items$4(onItemSelected, items));
    }

    @NotNull
    public AlertDialog build() {
        AlertDialog create = this.builder.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "builder.create()");
        return create;
    }

    @NotNull
    public AlertDialog show() {
        AlertDialog show = this.builder.show();
        Intrinsics.checkExpressionValueIsNotNull(show, "builder.show()");
        return show;
    }
}
