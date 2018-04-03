package com.tado.android.location;

import android.provider.BaseColumns;

final class LocationDBContract {

    static class LocationEntry implements BaseColumns {
        static final String COLUMN_NAME_ACC = "acc";
        static final String COLUMN_NAME_ACQUISITION = "acq";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_LAT = "lat";
        static final String COLUMN_NAME_LON = "lon";
        static final String COLUMN_NAME_RESULT = "result";
        static final String COLUMN_NAME_TIME = "time";
        static final String TABLE_NAME = "locations";

        LocationEntry() {
        }
    }

    private LocationDBContract() {
    }
}
