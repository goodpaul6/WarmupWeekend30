package com.tinfoilboy.warmupweekend.levels;

import com.tinfoilboy.warmupweekend.graphics.AbstractRenderable;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

public class Level
{
	/**
	 * The name of the author of this level.
	 * */
	public String author = "";

	/**
	 * The name of this level.
	 * */
	public String levelName = "";

	/**
	 * An array list containing the renderable for the level.
	 * */
	protected ArrayList<AbstractRenderable> levelRenderables = new ArrayList<AbstractRenderable>();

	/**
	 * The location the player will spawn at in this level.
	 * */
	public Vector3f spawnLocation = new Vector3f(0.0f, 0.0f, 0.0f);

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public void setLevelName(String levelName)
	{
		this.levelName = levelName;
	}

	/**
	 * Sets the location the player will spawn at in this level.
	 * */
	public void setSpawnLocation(Vector3f spawnLocation)
	{
		this.spawnLocation = spawnLocation;
	}

	/**
	 * Adds a renderable object to the level's renderable array list.
	 * */
	public void addRenderable(AbstractRenderable renderable)
	{
		this.levelRenderables.add(renderable);
	}

	/**
	 * Adds a renderable object to the level's renderable array list.
	 * */
	public void addRenderables(AbstractRenderable[] renderables)
	{
		for (AbstractRenderable renderable : renderables)
		{
			this.levelRenderables.add(renderable);
		}
	}

	public void drawLevel()
	{
		for (AbstractRenderable renderable : levelRenderables)
		{
			if (renderable.getSprite() != null)
			{
				if (glGetInteger(GL_TEXTURE_BINDING_2D) != renderable.getSprite().getSpriteSheet().getSpriteSheetTexture().TEXTURE_ID)
				{
					glBindTexture(GL_TEXTURE_2D, renderable.getSprite().getSpriteSheet().getSpriteSheetTexture().TEXTURE_ID);
				}
			}

			renderable.draw();
		}
	}

	/**
	 * Disposes of all renderables in this level.
	 * */
	public void disposeLevel()
	{
		glBindTexture(GL_TEXTURE_2D, 0);

		for (AbstractRenderable renderable : this.levelRenderables)
		{
			renderable.dispose();
		}

		this.levelRenderables.clear();
	}
}