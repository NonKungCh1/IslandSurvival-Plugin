package com.nonkungch.islandsurvival; // เปลี่ยน

import org.bukkit.Material;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.Random;

public class IslandChunkGenerator extends ChunkGenerator {

    private final int ISLAND_RADIUS = 200; 
    private final int SEA_LEVEL = 62;

    @Override
    public void generateSurface(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                
                int globalX = chunkX * 16 + x;
                int globalZ = chunkZ * 16 + z;

                double distanceFromSpawn = Math.sqrt(globalX * globalX + globalZ * globalZ);

                if (distanceFromSpawn <= ISLAND_RADIUS) {
                    chunkData.setBlock(x, SEA_LEVEL, z, Material.GRASS_BLOCK);
                    chunkData.setBlock(x, SEA_LEVEL - 1, z, Material.DIRT);
                    chunkData.setBlock(x, SEA_LEVEL - 2, z, Material.STONE);
                    for (int y = SEA_LEVEL - 3; y > worldInfo.getMinHeight(); y--) {
                        chunkData.setBlock(x, y, z, Material.STONE);
                    }
                } else {
                    for (int y = SEA_LEVEL; y > worldInfo.getMinHeight(); y--) {
                        chunkData.setBlock(x, y, z, Material.WATER);
                    }
                    chunkData.setBlock(x, worldInfo.getMinHeight() + 1, z, Material.SAND); 
                }
                chunkData.setBlock(x, worldInfo.getMinHeight(), z, Material.BEDROCK);
            }
        }
    }
    
    @Override
    public BiomeProvider getDefaultBiomeProvider(WorldInfo worldInfo) {
        // คลาสนี้ (IslandBiomeProvider) ต้องอยู่ใน package เดียวกัน
        return new IslandBiomeProvider(ISLAND_RADIUS);
    }
}
