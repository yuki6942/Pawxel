package io.github.yuki6942.pawxel.mixin;

import io.github.yuki6942.pawxel.Pawxel;
import net.glasslauncher.mods.alwaysmoreitems.gui.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResultType;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.block.Block;

@Mixin(InGameHud.class)
public class MixinIngameHud {

    @Shadow private Minecraft minecraft;

    @Inject(method = "render", at = @At("TAIL"))
    private void render(float screenOpen, boolean mouseX, int mouseY, int par4, CallbackInfo ci) {
        TextRenderer textRenderer = this.minecraft.textRenderer;


        String debugText = this.minecraft.debugText;
        String fpsOnly = debugText.split(",")[0].trim();
        String client = Pawxel.name + " " + Pawxel.version;
        textRenderer.drawWithShadow(client, 10, 10, Pawxel.color);
        textRenderer.drawWithShadow(fpsOnly, 10, 20, Pawxel.color);

        if (minecraft.crosshairTarget != null && minecraft.crosshairTarget.type == HitResultType.BLOCK) {
            int x = minecraft.crosshairTarget.blockX;
            int y = minecraft.crosshairTarget.blockY;
            int z = minecraft.crosshairTarget.blockZ;

            int blockId = minecraft.world.getBlockId(x, y, z);
            var block = Block.BLOCKS[blockId];

            if (block != null) {
                String blockName = block.getTranslatedName();
                int textWidth = textRenderer.getWidth(blockName);
                int iconWidth = 16;
                int spacing = 2;

                int startX = 10; // Align left with FPS
                int startY = 32; // Just below FPS (was at 20)

                // Render icon

                ItemRenderer itemRenderer = new ItemRenderer();
                ItemStack stack = new ItemStack(block);
                itemRenderer.renderGuiItem(textRenderer, minecraft.textureManager, stack, startX, startY);

                // Render text to the right of icon
                textRenderer.drawWithShadow(blockName, startX + iconWidth + spacing, startY + 5, Pawxel.color);
            }
        }


    }

}
