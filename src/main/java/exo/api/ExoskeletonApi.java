package exo.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class ExoskeletonApi{
    private static final Map<String, CorePair> cores = new HashMap<>();

    public static ItemStack getCoreItem(String name){
        for(Map.Entry<String, CorePair> core : cores.entrySet()){
            if(core.getKey().equals(name)){
                return new ItemStack(core.getValue().item);
            }
        }

        return null;
    }

    public static void registerCore(String name, Item item, Core core){
        if(cores.containsKey(name)){
           throw new IllegalStateException("Core already registered: " + name);
        }
        cores.put(name, new CorePair(core, item));
    }

    public static Core getCore(ItemStack stack){
        for(Map.Entry<String, CorePair> core : cores.entrySet()){
            if(core.getValue().item == stack.getItem()){
                return core.getValue().core;
            }
        }

        return null;
    }

    public static List<CorePair> getCores(){
        return new LinkedList<>(cores.values());
    }

    public static final class CorePair{
        public final Core core;
        public final Item item;

        private CorePair(Core core, Item item) {
            this.core = core;
            this.item = item;
        }
    }
}