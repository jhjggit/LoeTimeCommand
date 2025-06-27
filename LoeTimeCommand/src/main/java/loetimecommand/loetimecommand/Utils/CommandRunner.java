package loetimecommand.loetimecommand.Utils;

import loetimecommand.loetimecommand.LoeTimeCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

/**
 * @author Loe.
 * @project Plugin
 * @date 2025/6/27
 * @ClassInfo
 */
public class CommandRunner {
    public  ConsoleCommandSender ccs = LoeTimeCommand.ins.getServer().getConsoleSender();

    public  boolean runCommand(String cmd){
        return Bukkit.dispatchCommand(ccs, cmd);
    }
}
