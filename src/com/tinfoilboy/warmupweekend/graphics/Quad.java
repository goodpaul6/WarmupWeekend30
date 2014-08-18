package com.tinfoilboy.warmupweekend.graphics;

import com.tinfoilboy.warmupweekend.graphics.sprites.Sprite;
import com.tinfoilboy.warmupweekend.util.TextureCoordinate;
import com.tinfoilboy.warmupweekend.util.Vertex;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class Quad extends AbstractRenderable
{
	public Quad(Vector3f position, float size)
	{
		super(position, size);
	}

	public Quad(Vector3f position, Vector3f sizes)
	{
		super(position, sizes);
	}

	public Quad(Vector3f position, float size, Sprite sprite)
	{
		super(position, size, sprite);
	}

	public Quad(Vector3f position, Vector3f sizes, Sprite sprite)
	{
		super(position, sizes, sprite);
	}

	@Override
	public void beforeInit()
	{
		super.beforeInit();
		this.vertices = new Vertex[] {
				// Triangle 1
				new Vertex(-sizes.getX(), -sizes.getY(), 0.0f),
				new Vertex(sizes.getX(), -sizes.getY(), 0.0f),
				new Vertex(sizes.getX(), sizes.getY(), 0.0f),
				// Triangle 2
				new Vertex(-sizes.getX(), -sizes.getY(), 0.0f),
				new Vertex(sizes.getX(), sizes.getY(), 0.0f),
				new Vertex(-sizes.getX(), sizes.getY(), 0.0f)
		};
		if (this.hasTextures())
		{
			this.textureCoordinates = new TextureCoordinate[] {
				// Front Face 1
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV1()),
				new TextureCoordinate(sprite.getV(), sprite.getV()),
				// Front Face 2
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				new TextureCoordinate(sprite.getU1(), sprite.getV())
			};
		}
	}
}