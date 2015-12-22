package assets.exo.common.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import assets.exo.common.tile.TileEntityModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public final class ContainerModifier
extends Container {
    private final TileEntityModifier modifier;

    public ContainerModifier(TileEntityModifier modifier, EntityPlayer player){
        this.addSlotToContainer(new Slot(modifier, 0, 44, 185));
        this.bindPlayerInventory(player);
        this.bindPlayerArmorInventory(player);
        this.modifier = modifier;
    }

    private void bindPlayerInventory(EntityPlayer player){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 9; j++){
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 115 + j * 18, 170 + i * 18));
            }
        }

        for(int i = 0; i < 9; i++){
            this.addSlotToContainer(new Slot(player.inventory, i, 115 + i * 18, 228));
        }
    }

    private void bindPlayerArmorInventory(final EntityPlayer player){
        for (int i = 0; i < 4; ++i){
            final int k = i;
            this.addSlotToContainer(
                                           new Slot(player.inventory, player.inventory.getSizeInventory() - 1 - i, 79, 170 + i * 18){
                                               public int getSlotStackLimit(){
                                                   return 1;
                                               }

                                               public boolean isItemValid(ItemStack stack){
                                                   return stack != null && stack.getItem().isValidArmor(stack, k, player);
                                               }

                                               @SideOnly(Side.CLIENT)
                                               public IIcon getBackgroundIconIndex(){
                                                   return ItemArmor.func_94602_b(k);
                                               }
                                           }
            );
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player){
        return this.modifier.isUseableByPlayer(player);
    }
}