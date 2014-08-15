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
			// Front Face 1
			new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
			new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ()),
			new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
			// Front Face 2
			new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
			new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
			new Vertex(-sizes.getX(), sizes.getY(), -sizes.getZ()),
			// Back Face 1
			new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()),
			new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ()),
			new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
			// Back Face 2
			new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()),
			new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
			new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()),
			// Left Face 1
			new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ()),
			new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
			new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
			// Left Face 2
			new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
			new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ()),
			new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ()),
			// Right Face 1
			new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
			new Vertex(-sizes.getX(), sizes.getY(), -sizes.getZ()),
			new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()),
			// Right Face 2
			new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()),
			new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
			new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()),
			// Top Face 1
			new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
			new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()),
			new Vertex(-sizes.getX(), sizes.getY(), -sizes.getZ()),
			// Top Face 2
			new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
			new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()),
			new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
			// Bottom Face 1
			new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ()),
			new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()),
			new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
			// Bottom Face 2
			new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ()),
			new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
			new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ())
		};
		if (this.hasTextures())
		{
			this.textureCoordinates = new TextureCoordinate[] {
				// Front Face 1
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				// Front Face 2
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				new TextureCoordinate(sprite.getU1(), sprite.getV()),
				// Back Face 1
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				// Back Face 2
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				new TextureCoordinate(sprite.getU1(), sprite.getV()),
				// Right Face 1
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU1(), sprite.getV()),
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				// Right Face 2
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV1()),
				// Right Face 1
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU1(), sprite.getV()),
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				// Right Face 2
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV1()),
				// Top Face 1
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU1(), sprite.getV()),
				// Top Face 2
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV1()),
				// Bottom Face 1
				new TextureCoordinate(sprite.getU(), sprite.getV()),
				new TextureCoordinate(sprite.getU1(), sprite.getV()),
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				// Bottom Face 2
				new TextureCoordinate(sprite.getU(), sprite.getV1()),
				new TextureCoordinate(sprite.getU1(), sprite.getV1()),
				new TextureCoordinate(sprite.getU(), sprite.getV())
			};
		}
	}
}