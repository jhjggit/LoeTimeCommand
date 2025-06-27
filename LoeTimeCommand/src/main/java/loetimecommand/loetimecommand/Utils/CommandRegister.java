package loetimecommand.loetimecommand.Utils;

import loetimecommand.loetimecommand.LoeTimeCommand;
import org.bukkit.command.CommandExecutor;

/**
 * @author Loe.
 * @project Plugin
 * @date 2025/6/26
 * @ClassInfo
 */
public class CommandRegister {


    public static boolean regCommand(String cmd, CommandExecutor ce){
        if (cmd.equals("")){
            return false;
        }

        if (ce == null){
            return false;
        }

        LoeTimeCommand.ins.getCommand(cmd).setExecutor(ce);
        return true;
    }
}
