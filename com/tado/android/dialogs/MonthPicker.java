package com.tado.android.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.tado.C0676R;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u0000 52\u00020\u00012\u00020\u0002:\u000256B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J0\u0010!\u001a\u00020\u00192\f\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+H\u0002J\u0016\u0010,\u001a\u00020\u00192\f\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010#H\u0016J\u0010\u0010-\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u001a\u0010.\u001a\u00020\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010/\u001a\u00020\u0016H\u0002J\"\u00100\u001a\u00020\u00192\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u0005H\u0002J\u0016\u00103\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\u0005J\u0016\u00104\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0010X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0010X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/tado/android/dialogs/MonthPicker;", "Landroid/support/v4/app/DialogFragment;", "Landroid/widget/AdapterView$OnItemSelectedListener;", "()V", "DEFAULT_MONTH", "", "DEFAULT_YEAR", "displayedMonth", "displayedYear", "listener", "Lcom/tado/android/dialogs/MonthPicker$OnDateSelected;", "getListener", "()Lcom/tado/android/dialogs/MonthPicker$OnDateSelected;", "setListener", "(Lcom/tado/android/dialogs/MonthPicker$OnDateSelected;)V", "maxDate", "Lkotlin/Pair;", "minDate", "monthSpinner", "Landroid/widget/Spinner;", "yearSpinner", "getMonthsForYear", "Lkotlin/ranges/IntRange;", "year", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onItemSelected", "parent", "Landroid/widget/AdapterView;", "view", "Landroid/view/View;", "position", "id", "", "onMonthSelected", "month", "", "onNothingSelected", "onYearSelected", "populateMonths", "months", "populateYears", "minYear", "maxYear", "setMaxDate", "setMinDate", "Companion", "OnDateSelected", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: MonthPicker.kt */
public final class MonthPicker extends DialogFragment implements OnItemSelectedListener {
    public static final Companion Companion = new Companion();
    @NotNull
    private static final String KEY_MONTH = KEY_MONTH;
    @NotNull
    private static final String KEY_YEAR = KEY_YEAR;
    private final int DEFAULT_MONTH = 8;
    private final int DEFAULT_YEAR = 2017;
    private HashMap _$_findViewCache;
    private int displayedMonth = this.DEFAULT_MONTH;
    private int displayedYear = this.DEFAULT_YEAR;
    @Nullable
    private OnDateSelected listener;
    private Pair<Integer, Integer> maxDate = new Pair(Integer.valueOf(this.DEFAULT_YEAR), Integer.valueOf(this.DEFAULT_MONTH));
    private Pair<Integer, Integer> minDate = new Pair(Integer.valueOf(this.DEFAULT_YEAR), Integer.valueOf(this.DEFAULT_MONTH));
    private Spinner monthSpinner;
    private Spinner yearSpinner;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/tado/android/dialogs/MonthPicker$Companion;", "", "()V", "KEY_MONTH", "", "getKEY_MONTH", "()Ljava/lang/String;", "KEY_YEAR", "getKEY_YEAR", "newInstance", "Lcom/tado/android/dialogs/MonthPicker;", "year", "", "month", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: MonthPicker.kt */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String getKEY_YEAR() {
            return MonthPicker.KEY_YEAR;
        }

        @NotNull
        public final String getKEY_MONTH() {
            return MonthPicker.KEY_MONTH;
        }

        @NotNull
        public final MonthPicker newInstance(int year, int month) {
            MonthPicker dialog = new MonthPicker();
            dialog.setArguments(new Bundle());
            Bundle arguments = dialog.getArguments();
            if (arguments == null) {
                Intrinsics.throwNpe();
            }
            arguments.putInt(getKEY_YEAR(), year);
            Bundle arguments2 = dialog.getArguments();
            if (arguments2 == null) {
                Intrinsics.throwNpe();
            }
            arguments2.putInt(getKEY_MONTH(), month);
            return dialog;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/tado/android/dialogs/MonthPicker$OnDateSelected;", "", "onDateSelected", "", "year", "", "month", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: MonthPicker.kt */
    public interface OnDateSelected {
        void onDateSelected(int i, int i2);
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
        view = getView();
        if (view == null) {
            return null;
        }
        view = view.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Nullable
    public final OnDateSelected getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable OnDateSelected <set-?>) {
        this.listener = <set-?>;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.displayedMonth = arguments != null ? arguments.getInt(Companion.getKEY_MONTH()) : this.DEFAULT_MONTH;
        arguments = getArguments();
        this.displayedYear = arguments != null ? arguments.getInt(Companion.getKEY_YEAR()) : this.DEFAULT_YEAR;
    }

    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Spinner spinner;
        SpinnerAdapter adapter;
        View view = View.inflate(getContext(), C0676R.layout.dialog_month_picker, null);
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        AlertDialog alertDialog = new Builder(context).setView(view).setMessage((int) C0676R.string.energySavingsReport_title).setPositiveButton(17039370, (OnClickListener) new MonthPicker$onCreateDialog$builder$1(this)).create();
        if (view != null) {
            spinner = (Spinner) view.findViewById(C0676R.id.spinner_year);
        } else {
            spinner = null;
        }
        this.yearSpinner = spinner;
        populateYears(this.yearSpinner, ((Number) this.minDate.getFirst()).intValue(), ((Number) this.maxDate.getFirst()).intValue());
        spinner = this.yearSpinner;
        if (spinner != null) {
            adapter = spinner.getAdapter();
        } else {
            adapter = null;
        }
        if (adapter == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ArrayAdapter<kotlin.Int>");
        }
        int yearPosition = ((ArrayAdapter) adapter).getPosition(Integer.valueOf(this.displayedYear));
        spinner = this.yearSpinner;
        if (spinner != null) {
            spinner.setSelection(yearPosition);
        }
        if (view != null) {
            spinner = (Spinner) view.findViewById(C0676R.id.spinner_month);
        } else {
            spinner = null;
        }
        this.monthSpinner = spinner;
        populateMonths(this.monthSpinner, getMonthsForYear(this.displayedYear));
        spinner = this.monthSpinner;
        if (spinner != null) {
            adapter = spinner.getAdapter();
        } else {
            adapter = null;
        }
        if (adapter == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ArrayAdapter<kotlin.String>");
        }
        ArrayAdapter arrayAdapter = (ArrayAdapter) adapter;
        DateFormatSymbols instance = DateFormatSymbols.getInstance(Locale.getDefault());
        Intrinsics.checkExpressionValueIsNotNull(instance, "DateFormatSymbols.getInstance(Locale.getDefault())");
        int monthPosition = arrayAdapter.getPosition(instance.getMonths()[this.displayedMonth - 1]);
        if (monthPosition > -1) {
            spinner = this.monthSpinner;
            if (spinner != null) {
                spinner.setSelection(monthPosition);
            }
        }
        Spinner spinner2 = this.yearSpinner;
        if (spinner2 != null) {
            spinner2.setOnItemSelectedListener(this);
        }
        spinner = this.monthSpinner;
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        Intrinsics.checkExpressionValueIsNotNull(alertDialog, "alertDialog");
        return alertDialog;
    }

    private final void populateYears(Spinner yearSpinner, int minYear, int maxYear) {
        ArrayAdapter adapter = new ArrayAdapter(getContext(), 17367050, CollectionsKt___CollectionsKt.toList(new IntRange(minYear, maxYear)));
        if (yearSpinner != null) {
            yearSpinner.setAdapter(adapter);
        }
    }

    private final void populateMonths(Spinner monthSpinner, IntRange months) {
        Iterable $receiver$iv = months;
        Collection destination$iv$iv = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault($receiver$iv, 10));
        Iterator it = $receiver$iv.iterator();
        while (it.hasNext()) {
            int item$iv$iv = ((IntIterator) it).nextInt();
            DateFormatSymbols instance = DateFormatSymbols.getInstance(Locale.getDefault());
            Intrinsics.checkExpressionValueIsNotNull(instance, "DateFormatSymbols.getInstance(Locale.getDefault())");
            destination$iv$iv.add(instance.getMonths()[item$iv$iv - 1]);
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(), 17367050, (List) destination$iv$iv);
        if (monthSpinner != null) {
            monthSpinner.setAdapter(adapter);
        }
    }

    @NotNull
    public final IntRange getMonthsForYear(int year) {
        int minMonth = 1;
        int maxMonth = 12;
        if (year == ((Number) this.minDate.getFirst()).intValue()) {
            minMonth = ((Number) this.minDate.getSecond()).intValue();
        }
        if (year == ((Number) this.maxDate.getFirst()).intValue()) {
            maxMonth = ((Number) this.maxDate.getSecond()).intValue();
        }
        return new IntRange(minMonth, maxMonth);
    }

    public final void setMinDate(int year, int month) {
        this.minDate = new Pair(Integer.valueOf(year), Integer.valueOf(month));
    }

    public final void setMaxDate(int year, int month) {
        this.maxDate = new Pair(Integer.valueOf(year), Integer.valueOf(month));
    }

    public void onAttach(@Nullable Context context) {
        super.onAttach(context);
        if (getActivity() instanceof OnDateSelected) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.tado.android.dialogs.MonthPicker.OnDateSelected");
            }
            this.listener = (OnDateSelected) activity;
        }
    }

    private final void onYearSelected(int year) {
        String previousMonth;
        SpinnerAdapter spinnerAdapter = null;
        this.displayedYear = year;
        Spinner spinner = this.monthSpinner;
        if (spinner != null) {
            previousMonth = spinner.getSelectedItem();
        } else {
            previousMonth = null;
        }
        previousMonth = previousMonth;
        if (previousMonth != null) {
            SpinnerAdapter adapter;
            populateMonths(this.monthSpinner, getMonthsForYear(year));
            spinner = this.monthSpinner;
            if (spinner != null) {
                adapter = spinner.getAdapter();
            } else {
                adapter = null;
            }
            if (adapter == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.ArrayAdapter<kotlin.String>");
            }
            int previousPosition = ((ArrayAdapter) adapter).getPosition(previousMonth);
            spinner = this.monthSpinner;
            if (spinner != null) {
                if (previousPosition < 0) {
                    Spinner spinner2 = this.monthSpinner;
                    if (spinner2 != null) {
                        spinnerAdapter = spinner2.getAdapter();
                    }
                    if (spinnerAdapter == null) {
                        Intrinsics.throwNpe();
                    }
                    previousPosition = spinnerAdapter.getCount() - 1;
                }
                spinner.setSelection(previousPosition);
            }
        }
    }

    private final void onMonthSelected(String month) {
        DateFormatSymbols instance = DateFormatSymbols.getInstance(Locale.getDefault());
        Intrinsics.checkExpressionValueIsNotNull(instance, "DateFormatSymbols.getInstance(Locale.getDefault())");
        Object months = instance.getMonths();
        Intrinsics.checkExpressionValueIsNotNull(months, "DateFormatSymbols.getIns…cale.getDefault()).months");
        this.displayedMonth = ArraysKt___ArraysKt.indexOf((Object[]) months, (Object) month) + 1;
    }

    public void onNothingSelected(@Nullable AdapterView<?> parent) {
    }

    public void onItemSelected(@Nullable AdapterView<?> parent, @Nullable View view, int position, long id) {
        Integer valueOf = parent != null ? Integer.valueOf(parent.getId()) : null;
        Object item;
        if (valueOf != null && valueOf.intValue() == C0676R.id.spinner_year) {
            item = parent.getAdapter().getItem(position);
            if (item == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            onYearSelected(((Integer) item).intValue());
        } else if (valueOf != null && valueOf.intValue() == C0676R.id.spinner_month) {
            item = parent.getAdapter().getItem(position);
            if (item == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            onMonthSelected((String) item);
        }
    }
}
