package DataControl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter {
    private String datetime;
    public DateTimeConverter() {

    }

    public String getFormattedDate(String datetime) {
        this.datetime = datetime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = null;
        try {
            d = sdf.parse(datetime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String formattedTime = output.format(d);

        return formattedTime;
    }
}
