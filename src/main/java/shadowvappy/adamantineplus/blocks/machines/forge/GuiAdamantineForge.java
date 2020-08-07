package shadowvappy.adamantineplus.blocks.machines.forge;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import shadowvappy.adamantineplus.data.APModItems;
import shadowvappy.adamantineplus.data.APModReference;

import java.util.Objects;

public class GuiAdamantineForge extends GuiContainer {
    private static final ResourceLocation TEXTURES = new ResourceLocation(APModReference.MODID + ":textures/gui/gui_adamantine_forge.png");
    private final InventoryPlayer player;
    private final TileEntityAdamantineForge tileEntity;

    public GuiAdamantineForge(InventoryPlayer player, TileEntityAdamantineForge tileEntity) {
        super(new ContainerAdamantineForge(player, tileEntity));
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
        this.fontRenderer.drawString(tileName, 5, 5, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);

        int inputCount = AdamantineForgeRecipes.getInstance().getInputCount(this.tileEntity.result);
        if(inputCount < 10)
            this.fontRenderer.drawString("x" + inputCount, 67, 61, 16777215);
        else
            this.fontRenderer.drawString("x" + inputCount, 64, 61, 16777215);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if(TileEntityAdamantineForge.isBurning(tileEntity)) {
            int k = this.getBurnLeftScaled();
            this.drawTexturedModalRect(this.guiLeft+36, this.guiTop+49+12-k, 176, 12-k, 14, k+1);
        }

        int l = this.getCookProgressScaled();
        this.drawTexturedModalRect(this.guiLeft+32, this.guiTop+23, 176, 14, l+1, 16);

        this.drawHoveredButton(mouseX, mouseY);
        this.drawCurrentlyActive();

        TileEntityAdamantineForge.mouseX = mouseX;
        TileEntityAdamantineForge.mouseY = mouseY;
    }

    protected void drawCurrentlyActive() {
        if(tileEntity.isCurrentlyActive())
            this.drawTexturedModalRect(this.guiLeft+62, this.guiTop+50, 176, 131, 22,10);
        if(tileEntity.getResult().getItem() == APModItems.adamantineSword)
            this.drawTexturedModalRect(this.guiLeft+94, this.guiTop+10, 176, 31, 35,11);
        else if(tileEntity.getResult().getItem() == APModItems.adamantinePickaxe)
            this.drawTexturedModalRect(this.guiLeft+94, this.guiTop+23, 176, 42, 35,11);
        else if(tileEntity.getResult().getItem() == APModItems.adamantineAxe)
            this.drawTexturedModalRect(this.guiLeft+94, this.guiTop+36, 176, 53, 35,11);
        else if(tileEntity.getResult().getItem() == APModItems.adamantineShovel)
            this.drawTexturedModalRect(this.guiLeft+94, this.guiTop+49, 176, 64, 35,11);
        else if(tileEntity.getResult().getItem() == APModItems.adamantineHoe)
            this.drawTexturedModalRect(this.guiLeft+94, this.guiTop+62, 176, 75, 35,11);
        else if(tileEntity.getResult().getItem() == APModItems.adamantineHelmet)
            this.drawTexturedModalRect(this.guiLeft+132, this.guiTop+17, 176, 86, 35,11);
        else if(tileEntity.getResult().getItem() == APModItems.adamantineChestplate)
            this.drawTexturedModalRect(this.guiLeft+132, this.guiTop+30, 176, 97, 35,11);
        else if(tileEntity.getResult().getItem() == APModItems.adamantineLeggings)
            this.drawTexturedModalRect(this.guiLeft+132, this.guiTop+43, 176, 108, 35,11);
        else if(tileEntity.getResult().getItem() == APModItems.adamantineBoots)
            this.drawTexturedModalRect(this.guiLeft+132, this.guiTop+56, 176, 119, 35,11);
    }

    protected void drawHoveredButton(int mouseX, int mouseY) {
        if((mouseX > this.guiLeft+62 && mouseX < this.guiLeft+85) && (mouseY > this.guiTop+50 && mouseY < this.guiTop+61))
            this.drawTexturedModalRect(this.guiLeft+62, this.guiTop+50, 198, 131, 22,10);
        else if(mouseX > this.guiLeft+94 && mouseX < this.guiLeft+130) {
            if(mouseY > this.guiTop+10 && mouseY < this.guiTop+22)
                this.drawTexturedModalRect(this.guiLeft+94, this.guiTop+10, 211, 31, 35,11);
            else if(mouseY > this.guiTop+23 && mouseY < this.guiTop+35)
                this.drawTexturedModalRect(this.guiLeft+94, this.guiTop+23, 211, 42, 35,11);
            else if(mouseY > this.guiTop+36 && mouseY < this.guiTop+48)
                this.drawTexturedModalRect(this.guiLeft+94, this.guiTop+36, 211, 53, 35,11);
            else if(mouseY > this.guiTop+49 && mouseY < this.guiTop+61)
                this.drawTexturedModalRect(this.guiLeft+94, this.guiTop+49, 211, 64, 35,11);
            else if(mouseY > this.guiTop+62 && mouseY < this.guiTop+74)
                this.drawTexturedModalRect(this.guiLeft+94, this.guiTop+62, 211, 75, 35,11);
        }else if(mouseX > this.guiLeft+132 && mouseX < this.guiLeft+168) {
            if(mouseY > this.guiTop+17 && mouseY < this.guiTop+29)
                this.drawTexturedModalRect(this.guiLeft+132, this.guiTop+17, 211, 86, 35,11);
            else if(mouseY > this.guiTop+30 && mouseY < this.guiTop+42)
                this.drawTexturedModalRect(this.guiLeft+132, this.guiTop+30, 211, 97, 35,11);
            else if(mouseY > this.guiTop+43 && mouseY < this.guiTop+55)
                this.drawTexturedModalRect(this.guiLeft+132, this.guiTop+43, 211, 108, 35,11);
            else if(mouseY > this.guiTop+56 && mouseY < this.guiTop+68)
                this.drawTexturedModalRect(this.guiLeft+132, this.guiTop+56, 211, 119, 35,11);
        }
    }

    /**
     * Gets the number of pixels of the fire animation to show.
     * @return The number of pixels height to render.
     */
    private int getBurnLeftScaled() {
        int i = this.tileEntity.getCurrentBurnTime();
        if(i == 0) i = 200;
        return this.tileEntity.getBurnTime()*13/i;
    }

    /**
     * Gets the number of pixels of the arrow animation to show.
     * @return The number of pixels width to render.
     */
    private int getCookProgressScaled() {
        int i = this.tileEntity.getCookTime();
        int j = this.tileEntity.getTotalCookTime();
        return j != 0 && i != 0 ? i*24/j : 0;
    }
}
