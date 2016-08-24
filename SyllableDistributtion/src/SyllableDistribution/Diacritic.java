package SyllableDistribution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author danhd
 */
public enum Diacritic {

    SAC, HUYEN, HOI, NGA, NANG, NGANG;

    public String getDiacriticValue() {
        switch (this) {
            case SAC:
                return "á ắ ấ é ế í ó ố ớ ú ứ ý";
            case HUYEN:
                return "à ằ ầ é ề ì ò ồ ờ ù ừ ỳ";
            case HOI:
                return "ả ẳ ẩ ẻ ể ỏ ổ ở ủ ử ỷ";
            case NGA:
                return "ã ẵ ẫ ẽ ễ õ ỗ ỡ ũ ữ ỹ";
            case NANG:
                return "ạ ặ ậ ẹ ệ ọ ộ ợ ụ ự ỵ";
            default://NGANG
                return "";
        }
    }

    public static Diacritic getTone(String s) {
        Pattern p;

        for (Diacritic diacritic : Diacritic.values()) {
            if (diacritic != NGANG) {
                p = Pattern.compile("\\w*[" + diacritic.getDiacriticValue() + "]+");
            } else {
                p = Pattern.compile("");
            }
            Matcher m = p.matcher(s);
            if (m.find()) {
                return diacritic;
            }
        }
        return NGANG;
    }
}
