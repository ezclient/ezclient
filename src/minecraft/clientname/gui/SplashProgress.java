package clientname.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

public class SplashProgress {
	
	private static final int MAX = 10;
	private static int PROGRESS = 0;
	private static String CURRENT = "";
	private static ResourceLocation splash;
	private static UnicodeFontRenderer ufr;
	
	public static void update() {
		if(Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager() == null) {
			return;
		}
		drawSplash(Minecraft.getMinecraft().getTextureManager());
	}
	
	public static void setProgress(int givenProgress, String givenText) {
		PROGRESS = givenProgress;
		CURRENT = givenText;
		update();
	}
	
	public static void drawSplash(TextureManager tm) {
		
		ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
		int scaleFactor = scaledResolution.getScaleFactor();
		
		Framebuffer framebuffer = new Framebuffer(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor, true);
		framebuffer.bindFramebuffer(false);
		
		GlStateManager.matrixMode(GL11.GL_PROJECTION);
		GlStateManager.loadIdentity();
		GlStateManager.ortho(0.0D, (double)scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
		GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		GlStateManager.loadIdentity();
		GlStateManager.translate(0.0F,  0.0F, -2000.0F);
		GlStateManager.disableLighting();
		GlStateManager.disableFog();
		GlStateManager.disableDepth();
		GlStateManager.enableTexture2D();
		
		if(splash == null) {
			splash = new ResourceLocation("clientname/splash.png");
		}
		
		tm.bindTexture(splash);
		
		GlStateManager.resetColor();
		GlStateManager.color(1.0F,  1.0F, 1.0F);
		
		Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 1920, 1080);
		framebuffer.unbindFramebuffer();
		framebuffer.framebufferRender(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor);
		
		GlStateManager.enableAlpha();
		GlStateManager.alphaFunc(516, 0.1F);
		
		Minecraft.getMinecraft().updateDisplay();
	}
	
}