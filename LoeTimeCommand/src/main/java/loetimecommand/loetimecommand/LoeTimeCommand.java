package loetimecommand.loetimecommand;

import loetimecommand.loetimecommand.Commands.MainCommand;
import loetimecommand.loetimecommand.Common.TimeWeapon;
import loetimecommand.loetimecommand.Events.PlayerJoin;
import loetimecommand.loetimecommand.Utils.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public final class LoeTimeCommand extends JavaPlugin {

    public static JavaPlugin ins;
    public static ConfigUtils configUtils;
    public static CommandRunner commandRunner;

    @Override
    public void onEnable() {
        // Plugin startup logic
        ins = this;
        //reg
        CommandRegister.regCommand("ltc",new MainCommand());

        //reg events
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(),this);

        //read config
        try {
            configUtils = new ConfigUtils();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        //add tasks
        addTimeWeaponToVec();

        //set the cr
        commandRunner = new CommandRunner();

        //run task
        Vector<TimeWeapon> timeWeapons = TimeWeaponManager.timeWeapons;
        for (TimeWeapon timeWeapon : timeWeapons) {
            timeWeapon.toDo();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        //cancel the tasks
        Vector<TimeWeapon> timeWeapons = TimeWeaponManager.timeWeapons;
        for (TimeWeapon timeWeapon : timeWeapons) {
            timeWeapon.bukkitTask.cancel();
        }
    }

    public static void addTimeWeaponToVec(){
        FileConfiguration config = ins.getConfig();

        //获取所有task
        Set<String> allTask = configUtils.getAllTask();
        Iterator<String> iterator = allTask.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();

            //内部名
            //指令
            //时间
            //toObject
            TimeWeaponManager.add(new TimeWeapon(next, (String) config.get("Tasks."+next+".command"), TimeSolve.parseToSec((String) config.get("Tasks."+next+".excuteTime")), (String) config.get("Tasks."+next+".toObject")));
        }
    }
}
