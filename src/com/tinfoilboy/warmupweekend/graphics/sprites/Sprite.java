package com.tinfoilboy.warmupweekend.graphics.sprites;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class Sprite
{
	/**
	 * The position of this sprite in the sprite sheet.
	 */
	protected Vector2f spritePos = new Vector2f(0.0f, 0.0f);

	/**
	 * The width of this sprite on the sprite sheet.
	 */
	protected final float WIDTH;

	/**
	 * The height of this sprite on the sprite sheet.
	 */
	protected final float HEIGHT;

	/**
	 * The sprite sheet this sprite belongs to.
	 */
	protected final SpriteSheet SPRITE_SHEET;

	public Sprite(Vector2f spritePos, final float WIDTH, final float HEIGHT, final SpriteSheet SPRITE_SHEET)
	{
		this.spritePos = spritePos;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.SPRITE_SHEET = SPRITE_SHEET;
	}

	public Vector2f getSpritePos()
	{
		return spritePos;
	}

	public float getWidth()
	{
		return WIDTH;
	}

	public float getHeight()
	{
		return HEIGHT;
	}

	public SpriteSheet getSpriteSheet()
	{
		return SPRITE_SHEET;
	}

	public float getU()
	{
		return (this.spritePos.getX() * this.WIDTH + 1.0f) / this.getSpriteSheet().getWidth();
	}

	public float getU1()
	{
		return this.getU() + (this.WIDTH / this.getSpriteSheet().getWidth());
	}

	public float getV()
	{
		return (this.spritePos.getY() * this.HEIGHT) / this.getSpriteSheet().getHeight();
	}

	public float getV1()
	{
		return this.getV() + (this.WIDTH / this.getSpriteSheet().getWidth());
	}
}