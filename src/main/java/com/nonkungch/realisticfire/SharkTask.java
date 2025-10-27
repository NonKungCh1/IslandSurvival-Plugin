package com.nonkungch.islandsurvival; // เปลี่ยน

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SharkTask extends BukkitRunnable {

    // เปลี่ยน Type ของ plugin ให้ตรงกับคลาสหลัก
    private final IslandSurvival plugin;

    // เปลี่ยน Type ใน Constructor
    public SharkTask(IslandSurvival plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if ((player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE)
                    && player.isInWater()) {
                
                for (Entity entity : player.getNearbyEntities(32, 32, 32)) {
                    if (entity instanceof Dolphin) {
                        Mob shark = (Mob) entity;
                        if (shark.getTarget() == null) {
                            shark.setTarget(player);
                        }
                    }
                }
            }
        }
    }
}
