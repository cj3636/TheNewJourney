package com.thenewjourney.blocks.pervateki;

import com.cj3636.lib.Ref;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import java.awt.*;

public class ForgeTESR extends TileEntitySpecialRenderer<ForgeBlockTileEntity> {
	private static long startLong = 0L;

	@Override
	public void render(ForgeBlockTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if (!te.isActive()) {
			return;
		}
		startLong += 1;
		EnumFacing facing = this.getWorld().getBlockState(te.getPos()).getValue(ForgeBlock.FACING);
		BlockPos pos = new BlockPos(0, 0, 0).offset(facing.getOpposite());
		BlockPos posr = pos.offset(facing.rotateYCCW());
		BlockPos posg = pos.offset(facing.rotateYCCW().rotateYCCW());
		BlockPos posb = pos.offset(facing.rotateYCCW().rotateYCCW().rotateYCCW());
		float offX = 0.5F;
		float offY = 0.5F;
		float offZ = 0.5F;

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + offX, y + offY, z + offZ);
		RenderingUtils.renderLightRayEffects(posr.getX(), posr.getY(), posr.getZ(), Color.RED, 0x12315L, startLong, 20, 2F, 21, 7);
		RenderingUtils.renderLightRayEffects(posg.getX(), posg.getY(), posg.getZ(), Color.GREEN, 0x12315L, startLong, 20, 2F, 21, 7);
		RenderingUtils.renderLightRayEffects(posb.getX(), posb.getY(), posb.getZ(), Color.BLUE, 0x12315L, startLong, 20, 2F, 21, 7);
		GlStateManager.popMatrix();
		int[] red = {1, 0, 0};
		int[] green = {0, 1, 0};
		int[] blue = {0, 0, 1};
		runSphereRender(x, y, z, te, posr.getX(), posr.getY(), posr.getZ(), red);
		runSphereRender(x, y, z, te, posg.getX(), posg.getY(), posg.getZ(), green);
		runSphereRender(x, y, z, te, posb.getX(), posb.getY(), posb.getZ(), blue);
	}

	private void runSphereRender(double x, double y, double z, ForgeBlockTileEntity te, double xT, double yT, double zT, int[] color) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + .5, y + .5, z + .5);
		GlStateManager.translate(xT, yT, zT);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableTexture2D();
		GlStateManager.disableAlpha();
		GlStateManager.depthMask(false);
		GL11.glEnable(GL11.GL_BLEND);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		this.bindTexture(new ResourceLocation(Ref.MODID, "textures/blocks/FlorusWarp.png"));
		GL11.glColor4f(color[0], color[1], color[2], 1.0F);
		Sphere sphere = new Sphere();
		sphere.setDrawStyle(GLU.GLU_FILL);
		sphere.setNormals(GLU.GLU_FLAT);
		sphere.setOrientation(GLU.GLU_OUTSIDE);
		int sphereID = GL11.glGenLists(1);
		GlStateManager.glNewList(sphereID, GL11.GL_COMPILE);
		sphere.draw(0.5F, 16, 16);
		GlStateManager.glEndList();
		GlStateManager.callList(sphereID);
		//0x12315L
		//GlStateManager.translate(0, 0.15, 0);
		//GlStateManager.scale(0.7, 0.7, 0.7);
		GlStateManager.depthMask(true);
		GlStateManager.enableTexture2D();
		GlStateManager.enableAlpha();
		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();
	}
}