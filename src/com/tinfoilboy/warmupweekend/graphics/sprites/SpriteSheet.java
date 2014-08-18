package com.tinfoilboy.warmupweekend.graphics.sprites;

import com.tinfoilboy.warmupweekend.util.Texture;
import com.tinfoilboy.warmupweekend.util.TextureLoader;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.vector.Vector2f;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import static org.lwjgl.opengl.GL11.*;

/**
 * Llorton told me to comment more, here you go.
 *
 * @author Tinfoilboy
 * @version 1.0.0
 * @since The dawn of time
 * */
public class SpriteSheet
{
	/**
	 * A hashmap containing all of the sprites in this sprite sheet.
	 * */
	protected HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

 	/**
	 * The width of this sprite sheet.
	 * */
	protected final float WIDTH;

	/**
	 * The height of this sprite sheet.
	 * */
	protected final float HEIGHT;

	/**
	 * The texture for this sprite sheet.
	 * */
	protected final Texture SPRITE_SHEET_TEXTURE;

	public SpriteSheet(final float WIDTH, final float HEIGHT, final File textureFile) throws IOException
	{
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.SPRITE_SHEET_TEXTURE = TextureLoader.loadImageData(textureFile, GL_CLAMP, GL_NEAREST, GL_NEAREST_MIPMAP_NEAREST);
	}

	/**
	 * Add a sprite to the sprites map.
	 * */
	public SpriteSheet addSprite(String spriteName, Vector2f spritePos, float spriteWidth, float spriteHeight)
	{
		if (!this.sprites.containsKey(spriteName.toLowerCase()))
		{
			this.sprites.put(spriteName.toLowerCase(), new Sprite(spritePos, spriteWidth, spriteHeight, this));
		}
		else
		{
			System.err.println("Error! A sprite with this name already exists!");
		}
		return this;
	}

	public float getWidth()
	{
		return WIDTH;
	}

	public float getHeight()
	{
		return HEIGHT;
	}

	public Texture getSpriteSheetTexture()
	{
		return SPRITE_SHEET_TEXTURE;
	}

	public Sprite getSprite(String name)
	{
		if (this.sprites.containsKey(name.toLowerCase()))
			return this.sprites.get(name.toLowerCase());
		return null;
	}
}