package com.tado.android.demo.phone.view;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tado.C0676R;
import com.tado.android.demo.phone.model.AbstractPhoneNumberListItem;
import com.tado.android.demo.phone.presenter.PhoneListPresenter;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.IntentsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\tH\u0016J\b\u0010\u0015\u001a\u00020\tH\u0016J\b\u0010\u0016\u001a\u00020\tH\u0016J\u0016\u0010\u0017\u001a\u00020\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/tado/android/demo/phone/view/PhoneListFragment;", "Landroid/support/v4/app/DialogFragment;", "Lcom/tado/android/demo/phone/view/PhoneListView;", "()V", "dialogPresenter", "Lcom/tado/android/demo/phone/presenter/PhoneListPresenter;", "assets", "Landroid/content/res/AssetManager;", "dialNumber", "", "phoneNumber", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onStart", "preparePhoneList", "items", "", "Lcom/tado/android/demo/phone/model/AbstractPhoneNumberListItem;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: PhoneListFragment.kt */
public final class PhoneListFragment extends DialogFragment implements PhoneListView {
    private HashMap _$_findViewCache;
    private final PhoneListPresenter dialogPresenter = new PhoneListPresenter();

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
        view = getView();
        if (view == null) {
            return null;
        }
        view = view.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        this.dialogPresenter.bindView((PhoneListView) this);
        if (getDialog() != null) {
            getDialog().requestWindowFeature(1);
        }
        View view = inflater.inflate(C0676R.layout.dialog_phone_list, container, false);
        Toolbar toolbar = view != null ? (Toolbar) view.findViewById(C0676R.id.toolbar) : null;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
        }
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);
        if (toolbar != null) {
            Toolbar it = toolbar;
            Toolbar $receiver = toolbar;
            $receiver.setTitle((int) C0676R.string.demoMode_selectPhoneNumber_callUs_title);
            $receiver.setTitleTextColor(ContextCompat.getColor($receiver.getContext(), C0676R.color.white));
            $receiver.setNavigationIcon((int) C0676R.drawable.ic_close_white_24dp);
            $receiver.setNavigationOnClickListener(new PhoneListFragment$onCreateView$$inlined$let$lambda$1(this, toolbar));
        }
        return view;
    }

    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
            dialog.getWindow().setLayout(-1, -1);
        }
    }

    public void onResume() {
        super.onResume();
        this.dialogPresenter.onResumeDialog();
    }

    public void onDestroyView() {
        this.dialogPresenter.unbindView();
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void preparePhoneList(@NotNull List<? extends AbstractPhoneNumberListItem> items) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C0676R.id.call_recycler_view);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        recyclerView = (RecyclerView) _$_findCachedViewById(C0676R.id.call_recycler_view);
        if (recyclerView != null) {
            recyclerView.setAdapter(new PhoneListRecyclerViewAdapter(items, new PhoneListFragment$preparePhoneList$1(this.dialogPresenter)));
        }
    }

    @Nullable
    public AssetManager assets() {
        FragmentActivity activity = getActivity();
        return activity != null ? activity.getAssets() : null;
    }

    public void dialNumber(@NotNull String phoneNumber) {
        Intrinsics.checkParameterIsNotNull(phoneNumber, "phoneNumber");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            IntentsKt.browse$default((Context) activity, "tel:" + phoneNumber, false, 2, null);
        }
    }
}
