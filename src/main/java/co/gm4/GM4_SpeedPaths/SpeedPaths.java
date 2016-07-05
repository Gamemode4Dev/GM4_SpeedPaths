package co.gm4.GM4_SpeedPaths;

/**
 * Project: GM4_SpeedPaths
 * Author: SpiderRobotMan
 * Date: Jul 04 2016
 * Website: http://www.spiderrobotman.com
 */

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpeedPaths extends JavaPlugin implements Listener {

    private float SPEED_MODIFIER;
    private List<UUID> ON_PATH = new ArrayList<>();

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        saveDefaultConfig();

        SPEED_MODIFIER = (float) getConfig().getDouble("speed-modifier", 0);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Block below = e.getPlayer().getLocation().getBlock();
        if(below != null && below.getType() == Material.GRASS_PATH) {
            ON_PATH.add(e.getPlayer().getUniqueId());
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 0));
        } else if(e.getPlayer().isOnGround() && ON_PATH.contains(e.getPlayer().getUniqueId())){
            ON_PATH.remove(e.getPlayer().getUniqueId());
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1, 0));
        }
    }
}
