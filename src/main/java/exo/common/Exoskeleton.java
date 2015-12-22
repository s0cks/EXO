package exo.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "exo", version = "1.0.0.0", name = "Exoskeleton")
public final class Exoskeleton{
    @Mod.Instance("exo")
    public static Exoskeleton instance;

    @SidedProxy(clientSide = "exo.client.ClientProxy", serverSide = "exo.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){

    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent e){

    }
}