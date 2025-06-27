package loetimecommand.loetimecommand.Events;

import loetimecommand.loetimecommand.Utils.OfflinePlayerManager;
import loetimecommand.loetimecommand.Utils.workTodo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

/**
 * @author Loe.
 * @project Plugin
 * @date 2025/6/27
 * @ClassInfo
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void joinCheck(PlayerJoinEvent pje){
        Player player = pje.getPlayer();
        UUID uniqueId = player.getUniqueId();
        if (OfflinePlayerManager.isContain(uniqueId)){
            //to do here
            OfflinePlayerManager.get(uniqueId).toDo();
            OfflinePlayerManager.del(uniqueId);
        }
    }
}
