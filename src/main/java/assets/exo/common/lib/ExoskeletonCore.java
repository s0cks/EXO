package assets.exo.common.lib;

import assets.exo.api.Core;
import assets.exo.api.Tree;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BlockEvent;

public enum ExoskeletonCore
implements Core{
    ASSASSIN,
    BERZERK,
    BULLDOZER,
    FROST,
    GHOST,
    INFERNO,
    MEDIC,
    MYSTIC,
    RECON,
    REFLEX,
    SKYBOUND;

    @Override
    public Tree tree(){
        return ExoskeletonTree.valueOf(this.name().toUpperCase());
    }

    @Override
    public void onAttacked(LivingAttackEvent e, EntityPlayer player, DamageSource source) {

    }

    @Override
    public void onUpdate(LivingEvent.LivingUpdateEvent e) {

    }

    @Override
    public void onHarvest(BlockEvent.HarvestDropsEvent e) {

    }

    @Override
    public void onJump(LivingEvent.LivingJumpEvent e) {

    }
}