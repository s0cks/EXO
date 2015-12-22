package exo.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import exo.api.ExoskeletonApi;
import exo.common.item.ItemExoskeletonCore;
import exo.common.item.ItemExoskeletonBoots;
import exo.common.item.ItemExoskeletonChestplate;
import exo.common.item.ItemExoskeletonHelmet;
import exo.common.item.ItemExoskeletonLeggings;
import exo.common.lib.ExoskeletonCore;
import net.minecraft.item.Item;

@Mod(modid = "exo", version = "1.0.0.0", name = "Exoskeleton")
public final class Exoskeleton{
    @Mod.Instance("exo")
    public static Exoskeleton instance;

    @SidedProxy(clientSide = "exo.client.ClientProxy", serverSide = "exo.common.CommonProxy")
    public static CommonProxy proxy;

    public static final Item itemExoskeletonHelmet = new ItemExoskeletonHelmet()
            .setUnlocalizedName("exo.helmet");
    public static final Item itemExoskeletonChestplate = new ItemExoskeletonChestplate()
            .setUnlocalizedName("exo.chestplate");
    public static final Item itemExoskeletonLeggings = new ItemExoskeletonLeggings()
            .setUnlocalizedName("exo.leggings");
    public static final Item itemExoskeletonBoots = new ItemExoskeletonBoots()
            .setUnlocalizedName("exo.boots");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        for(ExoskeletonCore core : ExoskeletonCore.values()){
            ExoskeletonApi.registerCore(core.name(), new ItemExoskeletonCore(core), core);
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        GameRegistry.registerItem(itemExoskeletonHelmet, "itemExoskeletonHelmet");
        GameRegistry.registerItem(itemExoskeletonChestplate, "itemExoskeletonChestplate");
        GameRegistry.registerItem(itemExoskeletonLeggings, "itemExoskeletonLeggings");
        GameRegistry.registerItem(itemExoskeletonBoots, "itemExoskeletonBoots");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        for(ExoskeletonApi.CorePair core : ExoskeletonApi.getCores()){
            GameRegistry.registerItem(core.item, "core_" + core.core.name());
        }
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent e){

    }
}