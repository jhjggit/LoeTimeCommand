package loetimecommand.loetimecommand.Utils;

import loetimecommand.loetimecommand.LoeTimeCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author Loe.
 * @project Plugin
 * @date 2025/6/26
 * @ClassInfo
 */
public class ConfigUtils {
    public YamlConfiguration mainConfig = new YamlConfiguration();

    public ConfigUtils() throws IOException, InvalidConfigurationException {
        //save the default config
        if (!LoeTimeCommand.ins.getDataFolder().exists()){
            LoeTimeCommand.ins.saveDefaultConfig();
        }
        mainConfig.load(new File(LoeTimeCommand.ins.getDataFolder(),"config.yml"));
    }

    public Set<String> getAllTask(){
        ConfigurationSection tasks = LoeTimeCommand.ins.getConfig().getConfigurationSection("Tasks");
        if (tasks != null){
            return tasks.getKeys(false);
        }
        return null;
    }
}
