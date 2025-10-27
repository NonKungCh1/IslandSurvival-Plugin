package com.nonkungch.islandsurvival; // เปลี่ยน

import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;

public class IslandBiomeProvider extends BiomeProvider {

    private final int islandRadius;

    public IslandBiomeProvider(int islandRadius) {
        this.islandRadius = islandRadius;
    }

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        
        double distanceFromSpawn = Math.sqrt(x * x + z * z);

        if (distanceFromSpawn <= islandRadius) {
            return Biome.PLAINS;
        } else {
            return Biome.DEEP_OCEAN;
        }
    }

    @Override
    public java.util.List<Biome> getBiomes(WorldInfo worldInfo) {
        return java.util.Arrays.asList(Biome.PLAINS, Biome.DEEP_OCEAN);
    }
}
