package com.ternsip.structpro.universe.items;

import com.ternsip.structpro.logic.Configurator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Item wrapper
 *
 * @author Ternsip
 */
@SuppressWarnings({"WeakerAccess", "deprecation"})
public class UItem {

    /**
     * Minecraft native item
     */
    
    if (Configurator.VANILLA_LOOT_TABLES) {
        LootTableManager.getLootTableFromLocation(LootTableList.getAll());
        private Item item = LootTable.generateLootForPool(Random, LootContext);
    } else {
        private Item item;
    }

    /**
     * Construct from minecraft native item
     */
    public UItem(Item item) {
        this.item = item;
    }

    /**
     * Resolve item state validity
     *
     * @param meta Item metadata
     * @return Is item state valid
     */
    @SuppressWarnings({"ConstantConditions"})
    public boolean isValidItemState(int meta) {
        if (!isValid()) {
            return false;
        }
        if (Configurator.ONLY_VANILLA_LOOT) {
            return true;
        }
        try {
            ItemModelMesher models = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
            TextureAtlasSprite sprite = models.getParticleIcon(item, meta);
            return sprite != null &&
                    sprite.getIconName() != null &&
                    !sprite.getIconName().equalsIgnoreCase("") &&
                    !sprite.getIconName().equalsIgnoreCase("missingno");
        } catch (NoClassDefFoundError ndf) {
            return true;
        } catch (Throwable ignored) {
            return false;
        }
    }

    /**
     * Get all possible items
     *
     * @return An array with all possible items
     */
    public static Collection<UItem> getItems() {
        ArrayList<UItem> uItems = new ArrayList<>();
        for (Item item : GameRegistry.findRegistry(Item.class).getValues()) {
            uItems.add(new UItem(item));
        }
        return uItems;
    }

    /**
     * Resolve item validity
     *
     * @return Is item valid
     */
    public boolean isValid() {
        return item != null && item.getRegistryName() != null && (item.getRegistryName().getResourceDomain().equalsIgnoreCase("minecraft") || !Configurator.ONLY_VANILLA_LOOT);
    }

    /**
     * Get item path
     *
     * @return Item resource path
     */
    public String getPath() {
        return item.getRegistryName() == null ? "" : item.getRegistryName().getResourcePath();
    }

    /**
     * Get item id
     *
     * @return Item global index
     */
    public int getId() {
        return Item.getIdFromItem(item);
    }

    /**
     * Get Item max metadata/stack value
     *
     * @return Max damage
     */
    public int getMaxDamage() {
        return item.getMaxDamage();
    }

    /**
     * Get item max stack size
     *
     * @return Item max stack size
     */
    public int getMaxStack() {
        return item.getItemStackLimit();
    }

    /**
     * Return minecraft native item
     */
    public Item getItem() {
        return item;
    }

}
