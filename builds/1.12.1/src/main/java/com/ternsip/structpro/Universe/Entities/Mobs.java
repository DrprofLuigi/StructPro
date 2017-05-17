package com.ternsip.structpro.Universe.Entities;

import com.ternsip.structpro.Utils.Selector;
import com.ternsip.structpro.Structure.Biome;
import com.ternsip.structpro.Structure.Method;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.registries.GameData;

import java.util.Map;

/**
 * Mobs control class
 * @author  Ternsip
 * @since JDK 1.6
 */
@SuppressWarnings({"WeakerAccess", "deprecation"})
public class Mobs {

    /** Selector for all eggs mobs */
    public static final Selector<Class<? extends Entity>> eggs = new Selector<Class<? extends Entity>>(){{
        for (Map.Entry<ResourceLocation, EntityList.EntityEggInfo> e : EntityList.ENTITY_EGGS.entrySet()) {
            Class<? extends Entity> mob = EntityList.getClass(e.getKey());
            add(classToName(mob).getResourcePath(), mob);
        }
    }};

    /** Selector for all eggs mobs */
    public static final Selector<Class<? extends Entity>> mobs = new Selector<Class<? extends Entity>>(){{
        for (EntityEntry e : GameData.getEntityRegistry()) {
            Class<? extends Entity> mob = e.getEntityClass();
            add(classToName(mob).getResourcePath(), mob);
        }
    }};

    /** Selector for hostile mobs */
    public static final Selector<Class<? extends Entity>> hostile = new Selector<Class<? extends Entity>>(){{
        for (Biome biome : Biome.values()) {
            add(biome, EntitySkeleton.class);
            add(biome, EntityZombie.class);
            add(biome, EntitySpider.class);
            add(biome, EntityCreeper.class);
        }
        add(Biome.NETHER, EntityGhast.class);
        add(Biome.NETHER, EntityPigZombie.class);
        add(Biome.NETHER, EntityWitherSkull.class);
        add(Biome.NETHER, EntityBlaze.class);
        add(Biome.SNOW, EntityEnderman.class);
        add(Biome.SNOW, EntitySnowman.class);
        add(Biome.END, EntityEnderman.class);
        add(Method.UNDERGROUND, EntityCaveSpider.class);
        add(Method.UNDERGROUND, EntityBat.class);
        add(Method.AFLOAT, EntityWitch.class);
    }};

    /** Selector for village mobs */
    public static final Selector<Class<? extends Entity>> village = new Selector<Class<? extends Entity>>(){{
        add(EntityVillager.class);
    }};

    /**
     * Transform entity class to name
     * @param mob Target mob class
     * @return Name
     */
    public static ResourceLocation classToName(Class<? extends Entity> mob) {
        return EntityList.getKey(mob);
    }

    /**
     * Construct entity by class in the world
     * @param world Target world
     * @param mob Target mob class
     * @return Spawned entity
     * */
    public static Entity construct(World world, Class<? extends Entity> mob) {
        return EntityList.newEntity(mob, world);
    }

}
