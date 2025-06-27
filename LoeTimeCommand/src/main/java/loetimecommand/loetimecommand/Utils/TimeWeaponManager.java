package loetimecommand.loetimecommand.Utils;

import loetimecommand.loetimecommand.Common.TimeWeapon;

import java.util.Vector;

/**
 * @author Loe.
 * @project Plugin
 * @date 2025/6/26
 * @ClassInfo
 */
public class TimeWeaponManager {
    public static Vector<TimeWeapon> timeWeapons = new Vector<>();

    public static void add(TimeWeapon tw){
        timeWeapons.add(tw);
    }

    public static void del(TimeWeapon tw){
        timeWeapons.remove(tw);
    }
}
