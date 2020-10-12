package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getDate() {
        String dateFormat = "YYYY-mm-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        return sdf.format(new Date());
    }

    public static String formatDate(String format, Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(date);
    }

}
