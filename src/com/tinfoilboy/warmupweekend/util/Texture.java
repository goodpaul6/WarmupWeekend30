package com.tinfoilboy.warmupweekend.util;

import static org.lwjgl.opengl.GL11.*;

public class Texture
{
	public final int TEXTURE_ID;

	public float textureWidth = 0.0f, textureHeight = 0.0f;

	public Texture(final int TEXTURE_ID, float textureWidth, float textureHeight)
	{
		this.TEXTURE_ID = TEXTURE_ID;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}

	public void dispose()
	{
		glDeleteTextures(TEXTURE_ID);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
}