package com.wdullaer.materialdatetimepicker.time;

import java.util.ArrayList;
import java.util.Iterator;

class TimePickerDialog$Node {
    private ArrayList<TimePickerDialog$Node> mChildren = new ArrayList();
    private int[] mLegalKeys;

    public TimePickerDialog$Node(int... legalKeys) {
        this.mLegalKeys = legalKeys;
    }

    public void addChild(TimePickerDialog$Node child) {
        this.mChildren.add(child);
    }

    public boolean containsKey(int key) {
        for (int legalKey : this.mLegalKeys) {
            if (legalKey == key) {
                return true;
            }
        }
        return false;
    }

    public TimePickerDialog$Node canReach(int key) {
        if (this.mChildren == null) {
            return null;
        }
        Iterator it = this.mChildren.iterator();
        while (it.hasNext()) {
            TimePickerDialog$Node child = (TimePickerDialog$Node) it.next();
            if (child.containsKey(key)) {
                return child;
            }
        }
        return null;
    }
}
