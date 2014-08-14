package com.tinfoilboy.warmupweekend.graphics;

import com.tinfoilboy.warmupweekend.graphics.sprites.Sprite;
import com.tinfoilboy.warmupweekend.util.TextureCoordinate;
import com.tinfoilboy.warmupweekend.util.Vertex;
import org.lwjgl.util.vector.Vector3f;

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
		this.vertices = new Vertex[] {new Vertex(sizes.getX(), sizes.getY(), 0.0f), new Vertex(-sizes.getX(), sizes.getY(), 0.0f), new Vertex(-sizes.getX(), -sizes.getY(), 0.0f), new Vertex(sizes.getX(), -sizes.getY(), 0.0f)};
		if (this.hasTextures())
		{
			this.textureCoordinates = new TextureCoordinate[] {new TextureCoordinate(this.sprite.getU(), this.sprite.getV()), new TextureCoordinate(this.sprite.getU1(), this.sprite.getV()), new TextureCoordinate(this.sprite.getU1(), this.sprite.getV1()), new TextureCoordinate(this.sprite.getU(), this.sprite.getV1())};
		}
	}
}