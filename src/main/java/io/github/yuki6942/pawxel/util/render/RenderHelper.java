package io.github.yuki6942.pawxel.util.render;

import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.platform.Lighting;
import org.lwjgl.opengl.GL11;
/*
    From: net.glasslauncher.mods.alwaysmoreitems.gui.RenderHelper;
    Added here to avoid having to add a dependency on the whole mod
 */
public class RenderHelper {
    public static ItemRenderer itemRenderer = new ItemRenderer();


    public static void enableItemLighting() {
        enableLighting();
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPushMatrix();
        GL11.glRotatef(120F, 1.0F, 0.0F, 0.0F);
        Lighting.turnOn();
        GL11.glPopMatrix();
    }

    public static void enableLighting() {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(2896 /*GL_LIGHTING*/);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
    }

    public static void disableLighting() {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896 /*GL_LIGHTING*/);
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
    }

    public static void disableItemLighting() {
        disableLighting();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        Lighting.turnOff();
    }
}

