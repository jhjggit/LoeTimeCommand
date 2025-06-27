package loetimecommand.loetimecommand.Utils;

import loetimecommand.loetimecommand.LoeTimeCommand;

/**
 * @author Loe.
 * @project Plugin
 * @date 2025/6/26
 * @ClassInfo
 */
public class TimeSolve {
    public static final long SEC = 1L;
    public static final long MIN = 60L;
    public static final long HOUR = 3600L;
    public static final long DAY = 86400L;
    public static final int SEC_INDEX = 0;
    public static final int MIN_INDEX = 1;
    public static final int HOUR_INDEX = 2;
    public static final int DAY_INDEX = 3;



    public static long parseToSec(String str){
        long sec = 0;
        char[] chars = str.toCharArray();

        //get sec
        sec += (chars[SEC_INDEX] == '*' ? 0 : Long.parseLong(String.valueOf(chars[SEC_INDEX])) * SEC);
        sec += (chars[MIN_INDEX] == '*' ? 0 : Long.parseLong(String.valueOf(chars[MIN_INDEX])) * MIN);
        sec += (chars[HOUR_INDEX] == '*' ? 0 : Long.parseLong(String.valueOf(chars[HOUR_INDEX])) * HOUR);
        sec += (chars[DAY_INDEX] == '*' ? 0 : Long.parseLong(String.valueOf(chars[DAY_INDEX])) * DAY);

        return sec;
    }

    public static boolean checkStr(String str){
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(!(chars[i] >= '1' && chars[i] <= '9' || chars[i] == '*')){
                return false;
            }
        }
        return true;
    }
}
