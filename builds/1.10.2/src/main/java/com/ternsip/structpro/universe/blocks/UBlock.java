package com.ternsip.structpro.universe.blocks;

import net.minecraft.block.Block;

/**
 * Block wrapper
 * @author  Ternsip
 */
@SuppressWarnings({"WeakerAccess", "unused", "deprecation"})
public class UBlock {

    /** Native minecraft block */
    private Block block;

    /** Construct block from native minecraft block */
    public UBlock(Block block) {
        this.block = block;
    }

    /**
     * Get block from block id
     * @param blockID Block index
     * @return Converted block
     */
    public static UBlock getById(int blockID) {
        return new UBlock(Block.getBlockById(blockID));
    }

    /**
     * Get Block state from block
     * @return Block state
     */
    public final UBlockState getState() {
        return new UBlockState(block.getDefaultState());
    }

    /**
     * Get Block state from block and metadata
     * @param meta Block metadata
     * @return Converted block state
     */
    public UBlockState getState(int meta) {
        UBlockState result = getState();
        try {
            result = getStateFromMeta(meta);
        } catch (Throwable ignored) {}
        return result;
    }

    /**
     * Get block state from metadata
     * @param meta Block metadata
     * @return Block state
     */
    public UBlockState getStateFromMeta(int meta) {
        return new UBlockState(block.getStateFromMeta(meta));
    }

    /**
     * Block id from block
     * @return Block index
     */
    public int getID() {
        return Block.getIdFromBlock(block);
    }

    /**
     * Check if current block valid
     * @return is block valid
     */
    public boolean isValid() {
        return block != null && block.getRegistryName() != null;
    }

    /**
     * Get resource path
     * @return Resource path
     */
    public String getPath() {
        return block.getRegistryName() == null ? "" : block.getRegistryName().getResourcePath();
    }

    /**
     * Get light level that emits block
     * @return block light value
     */
    public int getLight() {
        return getState().getLight();
    }

    /**
     * Get light level that emits block
     * @return Block opacity level
     */
    public int getOpacity() {
        return getState().getOpacity();
    }

    /**
     * Get native minecraft block
     * @return Native block
     */
    public Block getBlock() {
        return block;
    }


}
