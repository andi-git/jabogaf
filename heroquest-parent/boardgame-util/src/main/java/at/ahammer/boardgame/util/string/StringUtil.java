package at.ahammer.boardgame.util.string;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StringUtil {

    public String repeatedString(char c, int count) {
        return new String(new char[count]).replace('\0', c);
    }

    public String padLeft(String string, int size) {
        if (string == null) {
            return repeatedString(' ', size);
        }
        return String.format("%1$-" + size + "s", string);
    }

    public String padLeftFixSize(String string, int size) {
        if (string != null && string.length() > size) {
            string = string.substring(0, size);
        }
        return padLeft(string, size);
    }

    public String padRight(String string, int size) {
        if (string == null) {
            return repeatedString(' ', size);
        }
        return String.format("%1$" + size + "s", string);
    }

    public String padRightFixSize(String string, int size) {
        if (string != null && string.length() > size) {
            string = string.substring(0, size);
        }
        return padRight(string, size);
    }
}
