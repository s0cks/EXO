package exo.core;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

import java.util.Arrays;

public final class ExoskeletonModContainer
extends DummyModContainer {
    public ExoskeletonModContainer(){
        super(new ModMetadata());
        ModMetadata meta = super.getMetadata();
        meta.modId = "Exoskeleton|Core";
        meta.name = "ExoskeletonCore";
        meta.version = "1.0.0.0";
        meta.credits = "Asyncronous";
        meta.authorList = Arrays.asList("Asyncronous", "CyanideX");
        meta.description = "";
        meta.url = "";
        meta.updateUrl = "";
        meta.screenshots = new String[0];
        meta.logoFile = "";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }
}