package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class FileUtils {


    public static void main(String[] args) throws IOException {
        readFile("D:\\program_1.txt");
    }

    public static String readFile(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        StringBuilder sb = new StringBuilder();
//        String s = "";
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {   // 1000
            sb.append(line);
            System.out.println(line);
//            s=s+line;
        }

        fileInputStream.close();
        return sb.toString();
    }

    public static String handleFiledValue(Object param) {
        String result = param.toString();
        if (param == null) {
            return null;
        }
        if (param.getClass() == Date.class) {
            result = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", (Date) param);
        }
        return ("\"" + result + "\"");
    }

//    public static <T> T handleFiledValue(T param) {
//
//        if (param == null) {
//            return null;
//        }
//        return (T) ("\"" + param+ "\"");
//    }

    public static String handleFiledValueWiithComma(String param) {
        return param = ", \"" + param + "\"";
    }

    public static String underscoreToLowerCamel(String str) {
        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        while (i < str.length()) {

            if (str.charAt(i) == '_') {
                i++;
                stringBuilder.append(String.valueOf(str.charAt(i)).toUpperCase());
                i++;
            } else {
                stringBuilder.append(str.charAt(i));
                i++;
            }
        }

        return stringBuilder.toString();
    }

    public static String lowerCamelToUpperCamel(String str) {

        String first = String.valueOf(str.charAt(0)).toUpperCase();
        return first + str.substring(1);
    }

    public static String objAttriConvertTotbField(String str) {
        StringBuilder fieldName = new StringBuilder(str);

        int count = str.length();
        for (int i = 0; i < count; i++) {
            if (Character.isUpperCase(fieldName.charAt(i))) {
                fieldName.replace(i, i + 1, String.valueOf(Character.toLowerCase(fieldName.charAt(i))));
                fieldName.insert(i, '_');
                count++;
            }

        }

        return fieldName.toString();
    }
}
