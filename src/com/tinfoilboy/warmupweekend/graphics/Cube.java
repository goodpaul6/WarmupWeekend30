package com.tinfoilboy.warmupweekend.graphics;

import com.tinfoilboy.warmupweekend.graphics.sprites.Sprite;
import com.tinfoilboy.warmupweekend.util.TextureCoordinate;
import com.tinfoilboy.warmupweekend.util.Vertex;
import org.lwjgl.util.vector.Vector3f;

public class Cube extends AbstractRenderable
{
	public Cube(Vector3f position, float size)
	{
		super(position, size);
	}

	public Cube(Vector3f position, Vector3f sizes)
	{
		super(position, sizes);
	}

	public Cube(Vector3f position, float size, Sprite sprite)
	{
		super(position, size, sprite);
	}

	public Cube(Vector3f position, Vector3f sizes, Sprite sprite)
	{
		super(position, sizes, sprite);
	}

	@Override
	public void beforeInit()
	{
		super.beforeInit();
		this.vertices = new Vertex[] {
			// Top Face
			new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()), new Vertex(-sizes.getX(), sizes.getY(), -sizes.getZ()), new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()), new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
			// Bottom Face
			new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ()), new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()), new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()), new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ()),
			// Front Face
			new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()), new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()), new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()), new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ()),
			// Back Face
			new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ()), new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()), new Vertex(-sizes.getX(), sizes.getY(), -sizes.getZ()), new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
			// Left Face
			new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()), new Vertex(-sizes.getX(), sizes.getY(), -sizes.getZ()), new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()), new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()),
			// Right Face
			new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()), new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()), new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ()), new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ())
		};
		if (this.hasTextures())
		{
			this.textureCoordinates = new TextureCoordinate[] {
					// Top
					new TextureCoordinate(sprite.getU(), sprite.getV1()), new TextureCoordinate(sprite.getU(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV1()),
					// Bottom
					new TextureCoordinate(sprite.getU1(), sprite.getV1()), new TextureCoordinate(sprite.getU(), sprite.getV1()), new TextureCoordinate(sprite.getU(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV()),
					// Front
					new TextureCoordinate(sprite.getU(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV1()), new TextureCoordinate(sprite.getU(), sprite.getV1()),
					// Back
					new TextureCoordinate(sprite.getU(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV1()), new TextureCoordinate(sprite.getU(), sprite.getV1()),
					// Left
					new TextureCoordinate(sprite.getU(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV1()), new TextureCoordinate(sprite.getU(), sprite.getV1()),
					// Right
					new TextureCoordinate(sprite.getU(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV()), new TextureCoordinate(sprite.getU1(), sprite.getV1()), new TextureCoordinate(sprite.getU(), sprite.getV1())
			};
		}
	}
}