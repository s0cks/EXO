package assets.exo.client.gui;

import assets.exo.api.Core;
import assets.exo.api.ExoskeletonApi;
import assets.exo.api.Skill;
import assets.exo.api.Tree;
import assets.exo.common.gui.ContainerModifier;
import assets.exo.common.tile.TileEntityModifier;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public final class GuiModifier
extends GuiContainer {
    private final TileEntityModifier modifier;

    public static final int TREE_XPAD = 0;
    public static final int TREE_YPAD = 37;

    public static final ResourceLocation LEFT = new ResourceLocation("exo", "textures/gui/modifier_left.png");
    public static final ResourceLocation RIGHT = new ResourceLocation("exo", "textures/gui/modifier_right.png");
    public static final ResourceLocation COMPONENTS = new ResourceLocation("exo", "textures/gui/modifier_comps.png");

    public GuiModifier(TileEntityModifier modifier, EntityPlayer player){
        super(new ContainerModifier(modifier, player));
        this.modifier = modifier;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        this.mc.renderEngine.bindTexture(LEFT);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 107, 0, 256 - 107, this.ySize);
        this.mc.renderEngine.bindTexture(RIGHT);
        this.drawTexturedModalRect(this.guiLeft + (256 - 107), this.guiTop, 0, 0, this.xSize - 170, this.ySize);
        this.drawTree();
    }

    private void drawTree(){
        Core core = ExoskeletonApi.getCore(this.modifier.getStackInSlot(0));
        if(core != null){
            Tree tree = core.tree();
            for(Skill skill : tree.skills()){
                if(skill.parents != null){
                    for(Skill parent : skill.parents){
                        this.drawLine(parent.x, parent.y, skill.x, skill.y);
                    }
                }

                this.drawColoredQuad(tree.getColor(), this.guiLeft + skill.x + TREE_XPAD, this.guiTop + skill.y + TREE_YPAD, 8, 8);
            }
        }
    }

    private void drawColoredQuad(int color, double x, double y, double width, double height){
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        int r = (color >> 16 & 0xFF);
        int g = (color >> 8 & 0xFF);
        int b = (color & 0xFF);
        double halfW = width / 2;
        double halfH = height / 2;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tess.setColorRGBA(r, g, b, 255);
        tess.addVertex(x - halfW, y - halfH, 1);
        tess.addVertex(x - halfW, y + halfH, 1);
        tess.addVertex(x + halfW, y + halfH, 1);
        tess.addVertex(x + halfW, y - halfH, 1);
        tess.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    private void drawLine(int x1, int y1, int x2, int y2){
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glLineWidth(3);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(this.guiLeft + x1 + TREE_XPAD, this.guiTop + y1 + TREE_YPAD, 1);
        GL11.glVertex3f(this.guiLeft + x2 + TREE_XPAD, this.guiTop + y2 + TREE_YPAD, 1);
        GL11.glEnd();

        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
}