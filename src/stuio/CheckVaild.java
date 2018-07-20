package stuio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckVaild {

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    // 判断一个字符串是否含有数字
    public boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    //判断字符串是不是以数字开头
    private static boolean isStartWithNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str.charAt(0)+"");
        return isNum.matches();
    }

    //判断字符串是不是以数字开头
    private static boolean isStartWithAlpha(String str) {
        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        Matcher isNum = pattern.matcher(str.charAt(0)+"");
        return isNum.matches();
    }

    //判断字符串长度
    private boolean CheckLength(String str, int num){
        return !str.isEmpty() && str.length() <= num;
    }

    public boolean CheckIDVaild(String str, int num){
        return isNumeric(str) && CheckLength(str, num);
    }

    public boolean CheckStrVaild(String str, int num){
        return isStartWithAlpha(str) && CheckLength(str, num);
    }
}
