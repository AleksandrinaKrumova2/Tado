package org.joda.time.base;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationFieldType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;

public abstract class AbstractPartial implements ReadablePartial, Comparable<ReadablePartial> {
    protected abstract DateTimeField getField(int i, Chronology chronology);

    protected AbstractPartial() {
    }

    public DateTimeFieldType getFieldType(int i) {
        return getField(i, getChronology()).getType();
    }

    public DateTimeFieldType[] getFieldTypes() {
        DateTimeFieldType[] dateTimeFieldTypeArr = new DateTimeFieldType[size()];
        for (int i = 0; i < dateTimeFieldTypeArr.length; i++) {
            dateTimeFieldTypeArr[i] = getFieldType(i);
        }
        return dateTimeFieldTypeArr;
    }

    public DateTimeField getField(int i) {
        return getField(i, getChronology());
    }

    public DateTimeField[] getFields() {
        DateTimeField[] dateTimeFieldArr = new DateTimeField[size()];
        for (int i = 0; i < dateTimeFieldArr.length; i++) {
            dateTimeFieldArr[i] = getField(i);
        }
        return dateTimeFieldArr;
    }

    public int[] getValues() {
        int[] iArr = new int[size()];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = getValue(i);
        }
        return iArr;
    }

    public int get(DateTimeFieldType dateTimeFieldType) {
        return getValue(indexOfSupported(dateTimeFieldType));
    }

    public boolean isSupported(DateTimeFieldType dateTimeFieldType) {
        return indexOf(dateTimeFieldType) != -1;
    }

    public int indexOf(DateTimeFieldType dateTimeFieldType) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (getFieldType(i) == dateTimeFieldType) {
                return i;
            }
        }
        return -1;
    }

    protected int indexOfSupported(DateTimeFieldType dateTimeFieldType) {
        int indexOf = indexOf(dateTimeFieldType);
        if (indexOf != -1) {
            return indexOf;
        }
        throw new IllegalArgumentException("Field '" + dateTimeFieldType + "' is not supported");
    }

    protected int indexOf(DurationFieldType durationFieldType) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (getFieldType(i).getDurationType() == durationFieldType) {
                return i;
            }
        }
        return -1;
    }

    protected int indexOfSupported(DurationFieldType durationFieldType) {
        int indexOf = indexOf(durationFieldType);
        if (indexOf != -1) {
            return indexOf;
        }
        throw new IllegalArgumentException("Field '" + durationFieldType + "' is not supported");
    }

    public DateTime toDateTime(ReadableInstant readableInstant) {
        Chronology instantChronology = DateTimeUtils.getInstantChronology(readableInstant);
        return new DateTime(instantChronology.set(this, DateTimeUtils.getInstantMillis(readableInstant)), instantChronology);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadablePartial)) {
            return false;
        }
        ReadablePartial readablePartial = (ReadablePartial) obj;
        if (size() != readablePartial.size()) {
            return false;
        }
        int size = size();
        int i = 0;
        while (i < size) {
            if (getValue(i) != readablePartial.getValue(i) || getFieldType(i) != readablePartial.getFieldType(i)) {
                return false;
            }
            i++;
        }
        return FieldUtils.equals(getChronology(), readablePartial.getChronology());
    }

    public int hashCode() {
        int i = 157;
        for (int i2 = 0; i2 < size(); i2++) {
            i = (((i * 23) + getValue(i2)) * 23) + getFieldType(i2).hashCode();
        }
        return getChronology().hashCode() + i;
    }

    public int compareTo(ReadablePartial readablePartial) {
        if (this == readablePartial) {
            return 0;
        }
        if (size() != readablePartial.size()) {
            throw new ClassCastException("ReadablePartial objects must have matching field types");
        }
        int i;
        int size = size();
        for (i = 0; i < size; i++) {
            if (getFieldType(i) != readablePartial.getFieldType(i)) {
                throw new ClassCastException("ReadablePartial objects must have matching field types");
            }
        }
        size = size();
        for (i = 0; i < size; i++) {
            if (getValue(i) > readablePartial.getValue(i)) {
                return 1;
            }
            if (getValue(i) < readablePartial.getValue(i)) {
                return -1;
            }
        }
        return 0;
    }

    public boolean isAfter(ReadablePartial readablePartial) {
        if (readablePartial != null) {
            return compareTo(readablePartial) > 0;
        } else {
            throw new IllegalArgumentException("Partial cannot be null");
        }
    }

    public boolean isBefore(ReadablePartial readablePartial) {
        if (readablePartial != null) {
            return compareTo(readablePartial) < 0;
        } else {
            throw new IllegalArgumentException("Partial cannot be null");
        }
    }

    public boolean isEqual(ReadablePartial readablePartial) {
        if (readablePartial != null) {
            return compareTo(readablePartial) == 0;
        } else {
            throw new IllegalArgumentException("Partial cannot be null");
        }
    }

    public String toString(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null) {
            return toString();
        }
        return dateTimeFormatter.print(this);
    }
}
