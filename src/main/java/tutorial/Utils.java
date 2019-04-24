package tutorial;

public class Utils {

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
//    public static String getMessage(String[] strings){
//        if (strings.length < 1)
//            return "info: Hello World!";
//        return joinStrings(strings, " ");
//    }

    public static String getSeverity(String[] strings){
        if (strings.length < 1)
            return "info";
        return strings[0];
    }

    public static String getMessage(String[] strings){
        if (strings.length < 2)
            return "Hello World!";
        return joinStrings(strings, " ", 1);
    }

    public static String joinStrings(String[] strings, String delimiter, int startIndex) {
        int length = strings.length;
        if (length == 0 ) return "";
        if (length < startIndex ) return "";
        StringBuilder words = new StringBuilder(strings[startIndex]);
        for (int i = startIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }

//    private static String joinStrings(String[] strings, String delimiter, int startIndex) {
//        int length = strings.length;
//        if (length == 0 ) return "";
//        if (length < startIndex ) return "";
//        StringBuilder words = new StringBuilder(strings[startIndex]);
//        for (int i = startIndex + 1; i < length; i++) {
//            words.append(delimiter).append(strings[i]);
//        }
//        return words.toString();
//    }

}
