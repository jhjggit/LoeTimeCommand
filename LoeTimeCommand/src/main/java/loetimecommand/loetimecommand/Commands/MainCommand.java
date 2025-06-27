package loetimecommand.loetimecommand.Commands;

import loetimecommand.loetimecommand.Common.TimeWeapon;
import loetimecommand.loetimecommand.LoeTimeCommand;
import loetimecommand.loetimecommand.Utils.ConfigUtils;
import loetimecommand.loetimecommand.Utils.TimeWeaponManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;
import java.sql.Time;
import java.util.Objects;
import java.util.Vector;

import static loetimecommand.loetimecommand.LoeTimeCommand.addTimeWeaponToVec;

/**
 * @author Loe.
 * @project Plugin
 * @date 2025/6/26
 * @ClassInfo
 */
public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean flag = false;
        if (sender.hasPermission("loetimecommand.command.use")){
            if(args.length < 1){
                sendHelp(sender);
                flag = true;
            }else if (Objects.equals(args[0], "reload")) {
                try {
                    LoeTimeCommand.configUtils = new ConfigUtils();
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }

                //cancel all
                for (TimeWeapon timeWeapon : TimeWeaponManager.timeWeapons) {
                    timeWeapon.bukkitTask.cancel();
                }

                //clear manager
                TimeWeaponManager.timeWeapons.clear();

                LoeTimeCommand.ins.reloadConfig();

                //add tasks
                addTimeWeaponToVec();

                //run task
                Vector<TimeWeapon> timeWeapons = TimeWeaponManager.timeWeapons;
                for (TimeWeapon timeWeapon : timeWeapons) {
                    timeWeapon.toDo();
                }
                flag = true;
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7[&c!&7] &c重载成功!"));
        }
        return flag;
    }


    public void sendHelp(CommandSender sender){
        String[] mes = {"ltc reload 重载插件"};
        sender.sendMessage(mes);
    }
}
