package com.tinfoilboy.warmupweekend.levels;

import com.tinfoilboy.warmupweekend.Static;
import com.tinfoilboy.warmupweekend.graphics.AbstractRenderable;
import com.tinfoilboy.warmupweekend.graphics.VBOBatcher;
import com.tinfoilboy.warmupweekend.physics.AxisAlignedBoundingBox;
import com.tinfoilboy.warmupweekend.util.Culling;
import com.tinfoilboy.warmupweekend.util.PrimitiveCoordinates;
import org.lwjgl.util.vector.Vector3f;

import static com.tinfoilboy.warmupweekend.Static.RENDER_DISTANCE;
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

	public ArrayList<PrimitiveCoordinates> levelGeoData = new ArrayList<PrimitiveCoordinates>();

	/**
	 * An array list containing the renderable for the level.
	 * */
	protected ArrayList<AbstractRenderable> levelRenderables = new ArrayList<AbstractRenderable>();

	/**
	 * An array list containing the renderable for the level.
	 * */
	protected ArrayList<AxisAlignedBoundingBox> levelColliders = new ArrayList<AxisAlignedBoundingBox>();

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

	/**
	 * Adds a collider object to the level's renderable array list.
	 * */
	public void addCollider(AxisAlignedBoundingBox collider)
	{
		this.levelColliders.add(collider);
	}

	/**
	 * Adds a collider object to the level's renderable array list.
	 * */
	public void addColliders(AxisAlignedBoundingBox[] colliders)
	{
		for (AxisAlignedBoundingBox collider : colliders)
		{
			this.levelColliders.add(collider);
		}
	}

	public void drawLevel()
	{
		for (AbstractRenderable renderable : levelRenderables)
		{
			if (Culling.shouldShow(renderable.getPosition(), renderable.getSizes(), RENDER_DISTANCE))
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
	}

	public void updateLevel()
	{
		for (AxisAlignedBoundingBox axisAlignedBoundingBox : levelColliders)
		{
			if (axisAlignedBoundingBox != null)
			{
				// Collider Test Code
				/*glDisable(GL_DEPTH_TEST);
				glDisable(GL_TEXTURE_2D);
				glLineWidth(2.0f);
				glBegin(GL_LINES);
				{
					glColor3f(0.0f, 0.0f, 0.0f);
					// Left Bottom Line
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.minZ);
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.minZ);
					// Right Bottom Line
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.maxZ);
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.maxZ);
					// Left Top Line
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.minZ);
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.minZ);
					// Right Top Line
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.maxZ);
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.maxZ);
					// Left Forward Bottom Line
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.minZ);
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.maxZ);
					// Right Forward Bottom Line
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.minZ);
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.maxZ);
					// Left Forward Top Line
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.minZ);
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.maxZ);
					// Right Forward Top Line
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.minZ);
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.maxZ);
					// Left Down Forward Line
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.maxZ);
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.maxZ);
					// Left Down Backward Line
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.minZ);
					glVertex3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.minZ);
					// Right Down Forward Line
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.maxZ);
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.maxZ);
					// Right Down Backward Line
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.minZ);
					glVertex3f(axisAlignedBoundingBox.maxX, axisAlignedBoundingBox.maxY, axisAlignedBoundingBox.minZ);
					glColor3f(1.0f, 1.0f, 1.0f);
				}
				glEnd();
				glEnable(GL_TEXTURE_2D);
				glEnable(GL_DEPTH_TEST);
				glLineWidth(1.0f);*/
				if (Culling.shouldShow(new Vector3f(axisAlignedBoundingBox.minX, axisAlignedBoundingBox.minY, axisAlignedBoundingBox.minZ), new Vector3f(axisAlignedBoundingBox.width, axisAlignedBoundingBox.height, axisAlignedBoundingBox.depth), RENDER_DISTANCE))
				{
					axisAlignedBoundingBox.update();
				}
			}
		}
	}

	public AxisAlignedBoundingBox getAxisAlignedBoundingBoxAt(Vector3f position)
	{
		for (AxisAlignedBoundingBox boundingBox : levelColliders)
		{
			if (boundingBox != null)
			{
				if (boundingBox.minX <= position.getX() && boundingBox.minY <= position.getY() && boundingBox.minZ <= position.getZ() &&
						boundingBox.maxX >= position.getX() && boundingBox.maxY >= position.getY() && boundingBox.maxZ >= position.getZ())
				{
					return boundingBox;
				}
			}
		}
		return null;
	}

	/**
	 * Check if a renderable object in the level is at the position specified.
	 *
	 * @return a boolean representing if there is a renderable at the position.
	 * */
	public boolean isRenderableAtPosition(Vector3f position)
	{
		for (AbstractRenderable renderable : levelRenderables)
		{
			if (renderable.getPosition().getX() == position.getX() ||
					renderable.getPosition().getY() == position.getY() ||
					renderable.getPosition().getZ() == position.getZ())
				return true;
		}
		return false;
	}

	public ArrayList<AxisAlignedBoundingBox> getLevelColliders()
	{
		return levelColliders;
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