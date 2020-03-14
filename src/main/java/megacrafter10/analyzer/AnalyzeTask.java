package megacrafter10.analyzer;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnalyzeTask extends BukkitRunnable {

    private Chunk[] chunks;
    private CommandSender sender;
    private int entityThreshold;

    public AnalyzeTask(CommandSender sender, Chunk[] chunks, int entityThreshold){
        this.chunks = chunks;
        this.sender = sender;
        this.entityThreshold = entityThreshold;
    }

    @Override
    public void run() {
        sender.sendMessage(ChatColor.YELLOW + "Analyzing " + chunks.length + " chunks...");
        List<ChunkData> loggedChunks = new ArrayList<>();

        for (Chunk chunk : chunks){
            if (loggedChunks.size() == 10){
                break;
            }
            int entities = chunk.getEntities().length;
            if (entities >= entityThreshold)
                loggedChunks.add(new ChunkData(chunk.getBlock(7,0,7).getLocation().getBlockX(),
                        chunk.getBlock(7,0,7).getLocation().getBlockZ(),entities));
        }

        if (loggedChunks.size() > 0) {
            sender.sendMessage(ChatColor.GREEN + "Finished analyzing chunks. Displaying the top 10 chunks with " +
                    entityThreshold + " or more entities...");
        }else{
            sender.sendMessage(ChatColor.GREEN + "Finished analyzing chunks. There are no chunks with "
                    + entityThreshold  + " or more entities");
        }

        Collections.sort(loggedChunks, Collections.reverseOrder());
        for (ChunkData data : loggedChunks){
            sender.sendMessage(ChatColor.YELLOW + "Chunk at X:" + data.x + " Z:" + data.z + " contains " +
                    ChatColor.RED + data.entities + ChatColor.YELLOW + " entities");
        }
    }
}
