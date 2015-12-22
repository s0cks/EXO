package exo.core;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.Name("Exoskeleton|Core")
@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions("exo.core.")
public final class ExoskeletonLoadingPlugin
implements IFMLLoadingPlugin{
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
            "exo.core.trans.EntityPlayerTransformer",
            "exo.core.trans.EndermanTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return "exo.core.ExoskeletonModContainer";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}