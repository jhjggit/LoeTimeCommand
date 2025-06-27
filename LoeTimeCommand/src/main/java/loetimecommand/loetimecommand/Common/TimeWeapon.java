package loetimecommand.loetimecommand.Common;

import loetimecommand.loetimecommand.LoeTimeCommand;
import loetimecommand.loetimecommand.Utils.OfflinePlayerManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Loe.
 * @project Plugin
 * @date 2025/6/26
 * @ClassInfo
 */
public class TimeWeapon {
    public String internalId;
    public String Cmd;
    public long delayTime;
    public String toObject;
    public BukkitTask bukkitTask;

    public TimeWeapon(String internalId, String cmd, long delayTime, String toObject) {
        this.internalId = internalId;
        Cmd = cmd;
        this.delayTime = delayTime;
        this.toObject = toObject;
    }

    //start to do jobs
    public void toDo() {
        this.bukkitTask = LoeTimeCommand.ins.getServer().getScheduler().runTaskTimer(LoeTimeCommand.ins, () -> {
            toObjectTodo();
        }, 0, this.delayTime * 20L);
    }

    public void toObjectTodo(){
        //目前仅支持对所有离线玩家进行操作
        ConsoleCommandSender consoleSender = LoeTimeCommand.ins.getServer().getConsoleSender();
        String tmpCmd = this.Cmd;
        final String runCommand = tmpCmd;
        //获取操作对象
        if (this.toObject.equals("all")){
            //获取所有玩家
            OfflinePlayer[] offlinePlayers = LoeTimeCommand.ins.getServer().getOfflinePlayers();
            //获取在线玩家
            for (OfflinePlayer offlinePlayer : offlinePlayers) {

                //如果在线,则:
                if (offlinePlayer.isOnline()) {
                    //解析命令
                    if (this.Cmd.contains("<player>")){
                        tmpCmd = this.Cmd.replaceAll("<player>", offlinePlayer.getName());
                    }
                    LoeTimeCommand.commandRunner.runCommand(tmpCmd);
                }else{
                    //否则就将此玩家加入到待处理队列
                    UUID uniqueId = offlinePlayer.getUniqueId();
                    if (this.Cmd.contains("<player>")){
                        tmpCmd = this.Cmd.replaceAll("<player>", offlinePlayer.getName());
                    }

                    //将离线玩家添加到处理队列
                    OfflinePlayerManager.add(uniqueId,() ->{
                        LoeTimeCommand.commandRunner.runCommand(runCommand.replaceAll("<player>", offlinePlayer.getName()));
                    });
                }
            }
        }

        //获取操作对象
        if (this.toObject.equals("online")){
            //获取在线玩家
            Collection<? extends Player> onlinePlayers = LoeTimeCommand.ins.getServer().getOnlinePlayers();
            for (OfflinePlayer offlinePlayer : onlinePlayers) {

                //解析命令
                if (this.Cmd.contains("<player>")){
                    tmpCmd = this.Cmd.replaceAll("<player>", offlinePlayer.getName());
                }

                LoeTimeCommand.commandRunner.runCommand(tmpCmd);
            }
        }

        //获取操作对象
//        if (this.toObject.equals("offline")){
//            OfflinePlayer[] offlinePlayers = LoeTimeCommand.ins.getServer().getOfflinePlayers();
//            //获取在线玩家
//            for (OfflinePlayer offlinePlayer : offlinePlayers) {
//
//                //解析命令
//                if (this.Cmd.contains("<player>")){
//                    tmpCmd = this.Cmd.replaceAll("<player>", offlinePlayer.getName());
//                }
//
//                LoeTimeCommand.commandRunner.runCommand(tmpCmd);
//            }
//        }
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public void setCmd(String cmd) {
        Cmd = cmd;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public void setToObject(String toObject) {
        this.toObject = toObject;
    }
}
