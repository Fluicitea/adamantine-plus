package shadowvappy.adamantineplus.blocks.machines.weaver;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import shadowvappy.adamantineplus.data.APModReference;

import java.util.Objects;

public class GuiWaferWeaver extends GuiContainer {
    private static final ResourceLocation TEXTURES = new ResourceLocation(APModReference.MODID + ":textures/gui/gui_wafer_weaver.png");
    private final InventoryPlayer player;
    private final TileEntityWaferWeaver tileEntity;

    public GuiWaferWeaver(InventoryPlayer player, TileEntityWaferWeaver tileEntity) {
        super(new ContainerWaferWeaver(player, tileEntity));
        this.player = player;
        this.tileEntity = tileEntity;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        String tileName = Objects.requireNonNull(this.tileEntity.getDisplayName()).getUnformattedText();
        this.fontRenderer.drawString(tileName, 8, 6, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if(TileEntityWaferWeaver.isBurning(tileEntity)) {
            int k = this.getBurnLeftScaled();
            this.drawTexturedModalRect(this.guiLeft+81, this.guiTop+58+12-k, 176, 12-k, 14, k+1);
        }

        int l = this.getCookProgressScaled();
        this.drawTexturedModalRect(this.guiLeft+76, this.guiTop+18, 176, 14, l+1, 16);
    }

    /**
     * Gets the number of pixels of the fire animation to show.
     * @return The number of pixels height to render.
     */
    private int getBurnLeftScaled() {
        int i = this.tileEntity.getCurrentBurnTime();
        if(i == 0) i = 200;
        return this.tileEntity.getBurnTime()* 13 /i;
    }

    /**
     * Gets the number of pixels of the arrow animation to show.
     * @return The number of pixels width to render.
     */
    private int getCookProgressScaled() {
        int i = this.tileEntity.getCookTime();
        int j = this.tileEntity.getTotalCookTime();
        return j != 0 && i != 0 ? i* 24 /j : 0;
    }
}
