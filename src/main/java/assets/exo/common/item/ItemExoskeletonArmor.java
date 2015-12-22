package assets.exo.common.item;

import assets.exo.api.Core;
import assets.exo.api.ExoskeletonApi;
import assets.exo.api.ExoskeletonTag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemExoskeletonArmor
extends ItemArmor {
    public ItemExoskeletonArmor(int index){
        super(ArmorMaterial.IRON, 0, index);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag){
        NBTTagCompound comp = ExoskeletonTag.getTag(stack);
        if(comp.hasKey("core")){
            list.add("Core: " + comp.getString("core"));
        }
    }

    public static ItemStack loadCore(ItemStack armor){
        return ExoskeletonTag.hasTag(armor)
                ? ExoskeletonApi.getCoreItem(ExoskeletonTag.getTag(armor)
                                                           .getString("core"))
                : null;
    }

    public static void installCore(ItemStack armor, ItemStack c){
        Core core = ExoskeletonApi.getCore(c);
        if(core != null){
            NBTTagCompound comp = ExoskeletonTag.getTag(armor);
            comp.setString("core", core.name());
        }
    }
}