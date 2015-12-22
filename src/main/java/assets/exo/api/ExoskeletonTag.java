package assets.exo.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class ExoskeletonTag{
    public static final String IDENTIFIER = "EXO";

    public static NBTTagCompound getTag(ItemStack stack){
        if(stack == null){
            return null;
        }

        if(!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound comp = stack.getTagCompound();
        if(!comp.hasKey(IDENTIFIER)){
            comp.setTag(IDENTIFIER, new NBTTagCompound());
        }

        return comp.getCompoundTag(IDENTIFIER);
    }

    public static boolean hasTag(ItemStack stack){
        return stack.hasTagCompound() && stack.getTagCompound().hasKey(IDENTIFIER);
    }
}