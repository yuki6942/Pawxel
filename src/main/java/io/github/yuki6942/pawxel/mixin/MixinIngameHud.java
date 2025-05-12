package io.github.yuki6942.pawxel.mixin;

import io.github.yuki6942.pawxel.Pawxel;
import io.github.yuki6942.pawxel.util.color.WoolColor;
import io.github.yuki6942.pawxel.util.render.RenderHelper;
import net.minecraft.block.WoolBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResultType;
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

            RenderHelper.enableItemLighting();
            int blockId = minecraft.world.getBlockId(x, y, z);

            var block = Block.BLOCKS[blockId];

            if (block != null) {

                ItemRenderer itemRenderer = new ItemRenderer();
                ItemStack stack = new ItemStack(block, 1, minecraft.world.getBlockMeta(x, y, z));
                String blockName = block.getTranslatedName();

                // Yea it works, no idea why I can't use getBlockMeta/getItemMeta from the WoolBlock class
                if (block instanceof WoolBlock) {
                    int woolColorMeta = WoolBlock.getItemMeta(minecraft.world.getBlockMeta(x, y, z));

                    WoolColor woolColor = WoolColor.fromMeta(woolColorMeta);
                    String colorName = woolColor.getDisplayName();
                    blockName = colorName + blockName;
                }

                int iconWidth = 16;
                int spacing = 2;

                int startX = 10;
                int startY = 32;

                itemRenderer.renderGuiItem(textRenderer, minecraft.textureManager, stack, startX, startY);

                textRenderer.drawWithShadow(blockName, startX + iconWidth + spacing, startY + 5, Pawxel.color);
                RenderHelper.disableItemLighting();
            }
        }


    }

}
