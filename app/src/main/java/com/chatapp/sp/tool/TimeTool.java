package com.chatapp.sp.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTool {
    private static final String TIME_FORMAT = "HH:mm";

    private TimeTool() {
        // util
    }

    public static String getFormattedTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        return sdf.format(date);
    }
}
