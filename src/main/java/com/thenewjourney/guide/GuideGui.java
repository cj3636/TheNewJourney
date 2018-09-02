package com.thenewjourney.guide;

import com.cj3636.lib.Ref;
import com.thenewjourney.sound.ModSounds;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class GuideGui extends GuiScreen {
    public static World worldIn;
    private static int bookTotalPages = 12;
    private static ResourceLocation[] bookPageTextures = new ResourceLocation[bookTotalPages];
    private static String[] stringPageText = new String[bookTotalPages];
    private final int bookImageHeight = 540;
    private final int bookImageWidth = 960;
    private int currPage = 0;
    private GuiButton buttonDone;
    private ContentButton contentButton;
    private ContentButton contentButton1;
    private ContentButton contentButton2;
    private ContentButton contentButton3;
    private ContentButton contentButton4;
    private ContentButton contentButton5;
    private ContentButton contentButton6;
    private ContentButton contentButton7;
    private ContentButton contentButton8;
    private ContentButton contentButton9;
    private ContentButton contentButton10;
    private ContentButton contentButton11;

    public GuideGui(World worldIn) {
        GuideGui.worldIn = worldIn;

        bookPageTextures[0] = new ResourceLocation(Ref.MODID + ":textures/gui/book/index.png");
        bookPageTextures[1] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankTiers.png");
        bookPageTextures[2] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankFireRuin.png");
        bookPageTextures[3] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankInfuser.png");
        bookPageTextures[4] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankCrystal.png");
        bookPageTextures[5] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankDimension.png");
        bookPageTextures[6] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankStructures.png");
        bookPageTextures[7] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankInfusion.png");
        bookPageTextures[8] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankVisceon.png");
        bookPageTextures[9] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankConstructs.png");
        bookPageTextures[10] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankArcane.png");
        bookPageTextures[11] = new ResourceLocation(Ref.MODID + ":textures/gui/book/indexBlankItems.png");

        stringPageText[0] = "Home";
        stringPageText[1] = "Progression Tiers" + "\n"
                + "Copper -> Tin -> Primitive = Iron/Ruby | Bronze -> Diamond/Cobalt -> Distortion -> Fire -> Shadow -> Aquis" + "\n"
                + "If the bronze age is enabled, the player must craft a Primitive Pickaxe using Copper and Tin in order to collect 4 Iron" + "\n"
                + "The player can either use the Iron to make Steel and craft a blast furnace to produce Bronze or simply make an Iron Pickaxe." + "\n"
                + "Bronze is a very useful material that has some perks over Iron." + "\n"
                + "* The player needs a fair amount of all materials to progress through The New Journey, but" + "\n"
                + "The player can only mine the next tier with the previous tiers pickaxe.";
        stringPageText[2] = "Fire Ruins" + "\n"
                + "Fire Ruins can be found randomly in the world." + "\n"
                + "They provide all of the necessary resources to produce wonderful magic." + "\n"
                + "Be careful when collecting them: Fire Ore and Fire BLocks need Distortion to collect." + "\n"
                + "Each Fire Ruin provides 4 Crypic Blocks, the exact amount needed to make a Crystal Binder. These binders are very useful...";
        stringPageText[3] = "Archaic Crafting" + "\n"
                + "The Archaic Crafting Table is a base crafting device to create Visceon Magic Crystals." + "\n"
                + "In order to acquire the Arcane Artifact to craft this device, the player must either find it in a Fire Ruin or craft it:" + "\n"
                + "Right Click a Bookshelf with a Distortion Wand." + "\n"
                + "Use JEI to see the recipes for this crafting table.";
        stringPageText[4] = "Crystal Power" + "\n"
                + "Crystal blocks are the sole power source for Visceon Magic Blocks in TNJ." + "\n"
                + "Certain magic machines require a certain Power Tier" + "\n"
                + "Others also need the total number of Crystals in the world to meet certain requirements." + "\n"
                + "The two variables at play are displayed when the player RClicks a Crystal:" + "\n"
                + "Power Tier is the current tier of power for the world." + "\n"
                + "The rotating Crystal also reflects this number based on its color." + "\n"
                + "Power Number is simply the total amount of Crystal Blocks in the world." + "\n"
                + "Certain machines need a certain amount to work, while others are simply sped up by adding more Crystals to the world." + "\n"
                + "* Crystal Power requires no cables or transfer mediums. You simply need to place Crystals into the world." + "\n"
                + "Crystals Power Tiers are upgraded per world by activating a Crystal Block with the next tiers Upgrade Ion.";
        stringPageText[5] = "Dimensions" + "\n"
                + "TNJ hosts two new realms: The Fire Dimension and The Florus Dimension. Each host their respective Ores and Blocks. Each Dimension also has its own Fire Ruin." + "\n"
                + "You will need 4 Visceon Cores and 1 Substrate to create a portal. (See Arcane Pillar Infusion)" + "\n"
                + "Activating the Portal with either a Fire Light or the Atrium light will teleport the player to the respective dimensions.";
        stringPageText[6] = "";
        stringPageText[7] = "Arcane Pillar Infusion" + "\n"
                + "Various items require high concentration of magic to create. This construct allows the player to infuse items with Visceon Magic." + "\n"
                + "Refer to a particular item in JEI's description for its recipe and remember that the ingredients can be placed in any pillar." + "\n"
                + "Activate the center pillar with a Distortion Wand to complete a recipe.";
        stringPageText[8] = "Visceon Magic";
        stringPageText[9] = "";
        stringPageText[10] = "Arcane Magic";
        stringPageText[11] = "Magic Items and Tools";
    }

    @Override
    public void initGui() {
        buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        int offsetFromScreenTop = height - (height / 4);
        int buttonWidth = 75;
        int buttonHeight = 20;

        buttonDone = new GuiButton(0, width - buttonWidth, height - 20, buttonWidth, buttonHeight, I18n.format("gui.done"));

        buttonList.add(buttonDone);
        buttonList.add(contentButton = new ContentButton(1, 0, height - 20, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.home")));
        buttonList.add(contentButton1 = new ContentButton(2, (buttonWidth), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.tiers")));
        buttonList.add(contentButton2 = new ContentButton(3, (buttonWidth * 2), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.fireruin")));
        buttonList.add(contentButton3 = new ContentButton(4, (buttonWidth * 3), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.crafting")));
        buttonList.add(contentButton4 = new ContentButton(5, (buttonWidth * 4), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.crystals")));
        buttonList.add(contentButton5 = new ContentButton(6, (buttonWidth * 5), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.dimensions")));
        buttonList.add(contentButton6 = new ContentButton(7, (buttonWidth * 6), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.structures")));
        buttonList.add(contentButton7 = new ContentButton(8, (buttonWidth * 7), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.infusion")));
        buttonList.add(contentButton8 = new ContentButton(9, (buttonWidth * 8), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.visceon")));
        buttonList.add(contentButton9 = new ContentButton(10, (buttonWidth * 9), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.inversion")));
        buttonList.add(contentButton10 = new ContentButton(11, (buttonWidth * 10), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.arcane")));
        buttonList.add(contentButton11 = new ContentButton(12, (buttonWidth * 11), offsetFromScreenTop, buttonWidth, buttonHeight, I18n.format("thenewjourney.gui.magicitems")));
        //buttonList.add(contentButton4 = new ContentButton(5, offsetFromScreenLeft - 28, 97, 50, 20, I18n.format("thenewjourney.gui.inversion", new Object[0])));
    }

    @Override
    public void updateScreen() {
        buttonDone.visible = true;
        contentButton.visible = true;
        contentButton1.visible = true;
        contentButton2.visible = true;
        contentButton3.visible = true;
        contentButton4.visible = true;
        contentButton5.visible = true;
        contentButton6.visible = true;
        contentButton7.visible = true;
        contentButton8.visible = true;
        contentButton9.visible = true;
        contentButton10.visible = true;
        contentButton11.visible = false;

        contentButton.enabled = currPage != 0;
        contentButton1.enabled = currPage != 1;
        contentButton2.enabled = currPage != 2;
        contentButton3.enabled = currPage != 3;
        contentButton4.enabled = currPage != 4;
        contentButton5.enabled = currPage != 5;
        contentButton6.enabled = currPage != 6;
        contentButton7.enabled = currPage != 7;
        contentButton8.enabled = currPage != 8;
        contentButton9.enabled = currPage != 9;
        contentButton10.enabled = currPage != 10;
        contentButton11.enabled = currPage != 11;
    }

    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(bookPageTextures[currPage]);
        //int offsetFromScreenLeft = (width - bookImageWidth) / 2;
        //this.drawTexturedModalRect((width - bookImageWidth) / 2, (height - bookImageHeight) / 2, 0, 0, bookImageWidth, bookImageHeight);
        drawScaledCustomSizeModalRect((width - bookImageWidth) / 2, (height - bookImageHeight) / 2, 0F, 0F, bookImageWidth, bookImageHeight, bookImageWidth, bookImageHeight, 960, 540);
        fontRenderer.drawSplitString(stringPageText[currPage], width / 4, 25, width / 2, 16777215);
        super.drawScreen(parWidth, parHeight, p_73863_3_);
        //this.drawCenteredString(fontRendererObj, stringPageText[currPage], width / 2, 25, 0);


    }

    @Override
    protected void mouseClickMove(int parMouseX, int parMouseY, int parLastButtonClicked, long parTimeSinceMouseClick) {
    }

    Random random = new Random();

    @Override
    protected void actionPerformed(GuiButton parButton) {
        if (parButton == buttonDone) {
            mc.displayGuiScreen(null);
            for (EntityPlayer playerEntity : worldIn.playerEntities) {
                playerEntity.playSound(ModSounds.BookClose, 1.0F, MathHelper.clamp(random.nextFloat(), 0.5F, 1F));
                return;
            }
        } else if (parButton == contentButton) {
            currPage = 0;
        } else if (parButton == contentButton1) {
            currPage = 1;
        } else if (parButton == contentButton2) {
            currPage = 2;
        } else if (parButton == contentButton3) {
            currPage = 3;
        } else if (parButton == contentButton4) {
            currPage = 4;
        } else if (parButton == contentButton5) {
            currPage = 5;
        } else if (parButton == contentButton6) {
            currPage = 6;
        } else if (parButton == contentButton7) {
            currPage = 7;
        } else if (parButton == contentButton8) {
            currPage = 8;
        } else if (parButton == contentButton9) {
            currPage = 9;
        } else if (parButton == contentButton10) {
            currPage = 10;
        } else if (parButton == contentButton11) {
            currPage = 11;
        }
        for (EntityPlayer playerEntity : worldIn.playerEntities) {

            if (random.nextBoolean()) {
                playerEntity.playSound(ModSounds.PageBack, 1.0F, MathHelper.clamp(random.nextFloat(), 0.5F, 1F));
            } else {
                playerEntity.playSound(ModSounds.PageForward, 1.0F, MathHelper.clamp(random.nextFloat(), 0.5F, 1F));
            }
        }
    }

    @Override
    public void onGuiClosed() {
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    static class ContentButton extends GuiButton {

        public ContentButton(int parButtonId, int parPosX, int parPosY, int width, int height, String text) {
            super(parButtonId, parPosX, parPosY, width, height, text);
            this.visible = false;
        }
    }
}
