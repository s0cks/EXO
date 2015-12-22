package assets.exo.common.item;

import assets.exo.api.Core;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class ItemExoskeletonCore
extends Item {
    public ItemExoskeletonCore(Core core){
        this.setUnlocalizedName("core." + core.name());
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
}