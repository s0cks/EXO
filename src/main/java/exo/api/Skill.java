package exo.api;

public final class Skill{
    public final int x;
    public final int y;
    public final String tag;
    public final Skill[] parents;

    public Skill(int x, int y, String tag, Skill... parents) {
        this.x = x;
        this.y = y;
        this.tag = tag;
        this.parents = parents;
    }
}