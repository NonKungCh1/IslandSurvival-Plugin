package com.nonkungch.islandsurvival; // เปลี่ยน

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CreateWorldCommand implements CommandExecutor {

    // เปลี่ยน Type ของ plugin
    private final IslandSurvival plugin;

    // เปลี่ยน Type ใน Constructor
    public CreateWorldCommand(IslandSurvival plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // เปลี่ยน Permission string
        if (!sender.hasPermission("islandsurvival.createworld")) {
            sender.sendMessage("§cYou don't have permission to do this.");
            return true;
        }

        String worldName = "IslandSurvivalWorld";

        if (Bukkit.getWorld(worldName) != null) {
            sender.sendMessage("§eWorld '" + worldName + "' already exists!");
            return true;
        }

        sender.sendMessage("§aGenerating your custom island world: '" + worldName + "'. This might take a moment...");

        WorldCreator wc = new WorldCreator(worldName);
        wc.environment(World.Environment.NORMAL);

        // ตรงนี้จะทำงานถูกต้องอัตโนมัติ
        // เพราะ plugin.getName() จะได้ค่า "IslandSurvival" จาก plugin.yml
        wc.generator(plugin.getName()); 

        World newWorld = wc.createWorld();

        if (newWorld != null) {
            newWorld.setSpawnLocation(0, 65, 0); 
            sender.sendMessage("§aSuccessfully created island world: " + worldName);
            sender.sendMessage("§eTeleport yourself with: /tp 0 70 0 -w " + worldName);
        } else {
            sender.sendMessage("§cFailed to create world!");
        }

        return true;
    }
}
