package com.nonkungch.islandsurvival; // เปลี่ยน

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

// เปลี่ยนชื่อคลาส
public class IslandSurvival extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("IslandSurvival Plugin Enabled! (By NonKungCh)"); // เปลี่ยน

        // คลาสเหล่านี้ต้องอยู่ใน package เดียวกัน
        new SharkTask(this).runTaskTimer(this, 0L, 40L);
        this.getCommand("createislandworld").setExecutor(new CreateWorldCommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("IslandSurvival Plugin Disabled."); // เปลี่ยน
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        // ส่งต่อไปให้คลาสที่เราสร้างขึ้นเพื่อจัดการการสร้างโลก
        return new IslandChunkGenerator();
    }
}
