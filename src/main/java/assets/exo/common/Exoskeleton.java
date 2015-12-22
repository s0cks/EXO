package assets.exo.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import assets.exo.api.ExoskeletonApi;
import assets.exo.client.gui.GuiModifier;
import assets.exo.common.gui.ContainerModifier;
import assets.exo.common.item.ItemExoskeletonArmor;
import assets.exo.common.item.ItemExoskeletonBoots;
import assets.exo.common.item.ItemExoskeletonChestplate;
import assets.exo.common.item.ItemExoskeletonCore;
import assets.exo.common.item.ItemExoskeletonHelmet;
import assets.exo.common.item.ItemExoskeletonLeggings;
import assets.exo.common.lib.ExoskeletonCore;
import assets.exo.common.tile.TileEntityModifier;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@Mod(modid = "exo", version = "1.0.0.0", name = "Exoskeleton")
public final class Exoskeleton
implements IGuiHandler{
    @Mod.Instance("exo")
    public static Exoskeleton instance;

    @SidedProxy(clientSide = "assets.exo.client.ClientProxy", serverSide = "assets.exo.common.CommonProxy")
    public static CommonProxy proxy;

    public static final Item itemExoskeletonHelmet = new ItemExoskeletonHelmet()
            .setUnlocalizedName("assets.exo.helmet");
    public static final Item itemExoskeletonChestplate = new ItemExoskeletonChestplate()
            .setUnlocalizedName("assets.exo.chestplate");
    public static final Item itemExoskeletonLeggings = new ItemExoskeletonLeggings()
            .setUnlocalizedName("assets.exo.leggings");
    public static final Item itemExoskeletonBoots = new ItemExoskeletonBoots()
            .setUnlocalizedName("assets.exo.boots");

    public static final int GUI_ARMOR_PIECE = 0x00;
    public static final int GUI_MODIFIER = 0x01;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        for(ExoskeletonCore core : ExoskeletonCore.values()){
            ExoskeletonApi.registerCore(core.name().toLowerCase(), new ItemExoskeletonCore(core), core);
        }

        Exoskeleton.proxy.registerHandlers();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        GameRegistry.registerItem(itemExoskeletonHelmet, "itemExoskeletonHelmet");
        GameRegistry.registerItem(itemExoskeletonChestplate, "itemExoskeletonChestplate");
        GameRegistry.registerItem(itemExoskeletonLeggings, "itemExoskeletonLeggings");
        GameRegistry.registerItem(itemExoskeletonBoots, "itemExoskeletonBoots");

        NetworkRegistry.INSTANCE.registerGuiHandler(Exoskeleton.instance, this);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        for(ExoskeletonApi.CorePair core : ExoskeletonApi.getCores()){
            GameRegistry.registerItem(core.item, "core_" + core.core.name());
        }
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent e){
        e.registerServerCommand(new CommandBase() {
            @Override
            public String getCommandName() {
                return "exo_debug";
            }

            @Override
            public String getCommandUsage(ICommandSender p_71518_1_) {
                return "exo_debug";
            }

            @Override
            public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
                ItemStack item = ((EntityPlayer) p_71515_1_).getHeldItem();
                if(item.getItem() instanceof ItemExoskeletonArmor){
                    ItemExoskeletonArmor.installCore(item, ExoskeletonApi.getCoreItem("recon"));
                }
            }
        });
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        switch(ID){
            case GUI_MODIFIER:{
                return new ContainerModifier(((TileEntityModifier) tile), player);
            }
            default:{
                return null;
            }
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        switch(ID){
            case GUI_MODIFIER:{
                return new GuiModifier(((TileEntityModifier) tile), player);
            }
            default:{
                return null;
            }
        }
    }
}