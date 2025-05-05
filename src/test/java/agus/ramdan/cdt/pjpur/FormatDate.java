package agus.ramdan.cdt.pjpur;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDate {
    public static void main(String ... args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Jakarta"));
        System.out.println("yyyy-MM-dd'T'HH:mm:ss.SSSXXX="+ zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")));
        System.out.println("yyyyMMddHHmmssSSSXXXX="+ zonedDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssXXXX")));
        System.out.println("yyyyMMddHHmmssSSS+XXXX="+ zonedDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss+XXXX")));
    }
}
