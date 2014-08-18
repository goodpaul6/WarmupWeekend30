package com.tinfoilboy.warmupweekend.gui;

import com.tinfoilboy.warmupweekend.Static;
import com.tinfoilboy.warmupweekend.WarmupWeekend;
import org.newdawn.slick.opengl.TextureImpl;

import static org.lwjgl.opengl.GL11.*;

public class HeadsUpDisplay
{
	public static void render()
	{
		TextureImpl.unbind();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		Static.font.drawString(2, 0, WarmupWeekend.getInstance().TITLE);
		Static.font.drawString(2, 26, "FPS: " + WarmupWeekend.getInstance().displayFPS);
		glDisable(GL_BLEND);
	}
}