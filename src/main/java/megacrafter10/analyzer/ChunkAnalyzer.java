package megacrafter10.analyzer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ChunkAnalyzer extends JavaPlugin implements CommandExecutor {

    private final Logger logger = Bukkit.getLogger();
    private int entityThreshold;

    @Override
    public void onEnable() {
        logger.info(getDescription().getName() + " enabled");
        this.saveDefaultConfig();
        entityThreshold = this.getConfig().getInt("entity-threshold", 20);
        this.getCommand("analyze").setExecutor(this);
    }

    @Override
    public void onDisable() {
        logger.info(getDescription().getName() + " disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("chunkanalyzer.analyze")){
            sender.sendMessage(ChatColor.RED + "You don't have permission to use that command");
            return true;
        }

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command");
            return true;
        }

        Player player = (Player) sender;
        Chunk[] loaded = player.getWorld().getLoadedChunks();

        new AnalyzeTask(sender, loaded, entityThreshold).runTask(this);
        return true;
    }
}
