package assets.exo.common.lib;

import assets.exo.api.Skill;
import assets.exo.api.Tree;
import net.minecraft.util.ResourceLocation;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum ExoskeletonTree
implements Tree{
    ASSASSIN(0xFF0000),
    BERZERK(0xFF0000),
    BULLDOZER(0xFF0000),
    FROST(0xFF0000),
    GHOST(0xFF0000),
    INFERNO(0xFF0000),
    MEDIC(0xFFFFFF),
    MYSTIC(0xFF0000),
    RECON(0xFF0000),
    REFLEX(0xFF0000),
    SKYBOUND(0xFF000);

    private final List<Skill> skills;
    private final int color;

    ExoskeletonTree(int color){
        this.color = color;
        try(TreeParser parser = new TreeParser(new ResourceLocation("exo", "trees/" + this.name().toLowerCase() + ".tree"))){
            this.skills = parser.parse();
        } catch (IOException e) {
            throw new RuntimeException("Error parsing tree for '" + this.name().toLowerCase() + "'", e);
        }
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public List<Skill> skills() {
        return this.skills;
    }

    @Override
    public String getName(){
        return this.name().charAt(0) + this.name().substring(1);
    }

    private static final class TreeParser
    implements Closeable {
        private final InputStream in;
        private final Map<String, Skill> skills = new HashMap<>();

        private TreeParser(InputStream in){
            if(in == null) throw new NullPointerException("stream == null");
            this.in = in;
        }

        private TreeParser(ResourceLocation loc){
            this(System.class.getResourceAsStream("/assets/" + loc.getResourceDomain() + "/" + loc.getResourcePath()));
        }

        private List<Skill> parse()
        throws IOException {
            String line;
            while((line = this.nextLine()) != null){
                String[] args = line.split(":");
                String name = args[0];
                String pair = args[1];
                String pars;
                String spec;

                try{
                    pars = args[2];
                } catch(Exception e){
                    pars = null;
                }

                try{
                    spec = args[3];
                } catch(Exception e){
                    spec = null;
                }

                if(this.skills.containsKey(name)){
                    throw new IllegalStateException("Duplicate skill found: " + name);
                }

                int x = Integer.parseInt(pair.split(",")[0].trim());
                int y = Integer.parseInt(pair.split(",")[1].trim());
                boolean special = spec == null || Boolean.parseBoolean(spec.trim());
                Skill[] parents = this.collectParents(pars == null ? null : pars.split(","));

                this.skills.put(name, new Skill(x, y, name, parents));
            }

            return new LinkedList<>(this.skills.values());
        }

        private Skill[] collectParents(String... pars){
            if(pars == null){
                return null;
            }

            Skill[] skills = new Skill[pars.length];
            for(int i = 0; i < pars.length; i++){
                String par = pars[i].trim();
                if(!this.skills.containsKey(par)){
                    throw new IllegalStateException("Parent not found: " + par);
                }
                skills[i] = this.skills.get(par);
            }
            return skills;
        }

        private String nextLine()
        throws IOException{
            String line = "";
            int i;
            it: while((i = this.in.read()) != -1){
                char c = (char) i;
                switch(c)
                {
                    case '\r':{
                        // Fall through
                    }
                    case '\n':{
                        break it;
                    }
                    default:{
                        line += c;
                    }
                }
            }
            if(!line.equalsIgnoreCase("") && !line.isEmpty()){
                return line;
            } else{
                return null;
            }
        }

        @Override
        public void close()
        throws IOException {
            this.in.close();
        }
    }
}