package loetimecommand.loetimecommand.Utils;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Loe.
 * @project Plugin
 * @date 2025/6/27
 * @ClassInfo
 */
public class OfflinePlayerManager {
    public static HashMap<UUID,workTodo> opm = new HashMap<>();

    public static workTodo add(UUID uuid, workTodo wt){
        return opm.put(uuid,wt);
    }

    public static workTodo del(UUID uuid){
        return opm.remove(uuid);
    }

    public static boolean isContain(UUID uuid){
        return opm.containsKey(uuid);
    }

    public static workTodo get(UUID uuid){
        return opm.get(uuid);
    }
}
