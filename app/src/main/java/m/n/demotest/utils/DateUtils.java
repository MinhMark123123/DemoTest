package m.n.demotest.utils;

import java.util.Calendar;

public class DateUtils {
    public static java.util.Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
