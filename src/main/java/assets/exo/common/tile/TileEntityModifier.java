package assets.exo.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public final class TileEntityModifier
extends TileEntity
implements IInventory {
    private ItemStack[] inventory = new ItemStack[1];

    @Override
    public Packet getDescriptionPacket(){
        NBTTagCompound comp = new NBTTagCompound();
        this.writeToNBT(comp);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, comp);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pck){
        this.readFromNBT(pck.func_148857_g());
    }

    @Override
    public void readFromNBT(NBTTagCompound comp){
        super.readFromNBT(comp);
        NBTTagList list = comp.getTagList("Items", 10);
        this.inventory = new ItemStack[1];
        for(int i = 0; i < list.tagCount(); i++){
            NBTTagCompound c = list.getCompoundTagAt(i);
            byte b = c.getByte("Slot");

            if(b >= 0 && b < this.inventory.length){
                this.inventory[b] = ItemStack.loadItemStackFromNBT(c);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound comp){
        super.writeToNBT(comp);
        NBTTagList list = new NBTTagList();
        for(int i = 0; i < this.inventory.length; i++){
            if(this.inventory[i] != null){
                NBTTagCompound c = new NBTTagCompound();
                c.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(c);
                list.appendTag(c);
            }
        }
        comp.setTag("Items", list);
    }

    @Override
    public int getSizeInventory(){
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot){
        return this.inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int size){
        ItemStack stack = this.getStackInSlot(slot);
        if(stack != null){
            if(stack.stackSize > size){
                stack = stack.splitStack(size);
                this.markDirty();
            } else{
                this.setInventorySlotContents(slot, null);
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot){
        if(this.getStackInSlot(slot) != null){
            ItemStack stack = this.getStackInSlot(slot);
            this.inventory[slot] = null;
            return stack;
        } else{
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack){
        this.inventory[slot] = stack;
    }

    @Override
    public String getInventoryName(){
        return "Modifier";
    }

    @Override
    public boolean hasCustomInventoryName(){
        return false;
    }

    @Override
    public int getInventoryStackLimit(){
        return 1;
    }

    @Override
    public void markDirty(){
        for(int i = 0; i < this.getSizeInventory(); i++){
            if(this.getStackInSlot(i) != null && this.getStackInSlot(i).stackSize == 0){
                this.inventory[i] = null;
            }
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_){
        return true;
    }

    @Override
    public void openInventory(){

    }

    @Override
    public void closeInventory(){

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_){
        return true;
    }
}