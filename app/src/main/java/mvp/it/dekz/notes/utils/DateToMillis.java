package mvp.it.dekz.notes.utils;

import android.annotation.SuppressLint;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToMillis {

    private String relativeTime;

    public static CharSequence getRelativeTime(String srcDate){
        long now = System.currentTimeMillis();
        long date = getDateinMillis(srcDate);
        return DateUtils.getRelativeTimeSpanString(date,now,DateUtils.MINUTE_IN_MILLIS);
    }

    public static long getDateinMillis(String srcDate){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat desiredFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        long dateInMillis = 0;
        try {
            Date date = desiredFormat.parse(srcDate);
            dateInMillis = date.getTime();
            return dateInMillis;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
