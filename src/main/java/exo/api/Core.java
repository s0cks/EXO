package exo.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BlockEvent;

public interface Core{
    public void onAttacked(LivingAttackEvent e, EntityPlayer player, DamageSource source);
    public void onUpdate(LivingEvent.LivingUpdateEvent e);
    public void onHarvest(BlockEvent.HarvestDropsEvent e);
    public void onJump(LivingEvent.LivingJumpEvent e);
    public Tree tree();
    public String name();
}