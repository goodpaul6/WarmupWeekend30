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

	public Cube(Vector3f position, float size, boolean staticObject)
	{
		super(position, size, staticObject);
	}

	public Cube(Vector3f position, Vector3f sizes, boolean staticObject)
	{
		super(position, sizes, staticObject);
	}

	public Cube(Vector3f position, float size, Sprite sprite, boolean staticObject)
	{
		super(position, size, sprite, staticObject);
	}

	public Cube(Vector3f position, Vector3f sizes, Sprite sprite, boolean staticObject)
	{
		super(position, sizes, sprite, staticObject);
	}

	@Override
	public void beforeInit()
	{
		super.beforeInit();
		if (!this.staticObject)
		{
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
		}
		else
		{
			this.vertices = new Vertex[] {
					// Front Face 1
					new Vertex(-sizes.getX() + position.getX(), -sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), -sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					// Front Face 2
					new Vertex(-sizes.getX() + position.getX(), -sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					// Back Face 1
					new Vertex(-sizes.getX() + position.getX(), -sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), -sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					// Back Face 2
					new Vertex(-sizes.getX() + position.getX(), -sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					// Left Face 1
					new Vertex(sizes.getX() + position.getX(), -sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					// Left Face 2
					new Vertex(sizes.getX() + position.getX(), sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), -sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), -sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					// Right Face 1
					new Vertex(-sizes.getX() + position.getX(), -sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					// Right Face 2
					new Vertex(-sizes.getX() + position.getX(), sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), -sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), -sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					// Top Face 1
					new Vertex(sizes.getX() + position.getX(), sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					// Top Face 2
					new Vertex(sizes.getX() + position.getX(), sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					// Bottom Face 1
					new Vertex(sizes.getX() + position.getX(), -sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), -sizes.getY() + position.getY(), sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), -sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					// Bottom Face 2
					new Vertex(sizes.getX() + position.getX(), -sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(-sizes.getX() + position.getX(), -sizes.getY() + position.getY(), -sizes.getZ() + position.getZ()),
					new Vertex(sizes.getX() + position.getX(), -sizes.getY() + position.getY(), sizes.getZ() + position.getZ())
			};
		}
		this.normals = new Vertex[] {
			// Front Face 1 Normals
			new Vertex(-1.0f, -1.0f, -1.0f),
			new Vertex(1.0f, -1.0f, -1.0f),
			new Vertex(1.0f, 1.0f, -1.0f),
			// Front Face 2 Normals
			new Vertex(-1.0f, -1.0f, -1.0f),
			new Vertex(1.0f, 1.0f, -1.0f),
			new Vertex(-1.0f, 1.0f, -1.0f),
			// Back Face 1 Normals
			new Vertex(-1.0f, -1.0f, 1.0f),
			new Vertex(1.0f, -1.0f, 1.0f),
			new Vertex(1.0f, 1.0f, 1.0f),
			// Back Face 2 Normals
			new Vertex(-1.0f, -1.0f, 1.0f),
			new Vertex(1.0f, 1.0f, 1.0f),
			new Vertex(-1.0f, 1.0f, 1.0f),
			// Left Face 1 Normals
			new Vertex(1.0f, -1.0f, -1.0f),
			new Vertex(1.0f, 1.0f, -1.0f),
			new Vertex(1.0f, 1.0f, 1.0f),
			// Left Face 2 Normals
			new Vertex(1.0f, 1.0f, 1.0f),
			new Vertex(1.0f, -1.0f, -1.0f),
			new Vertex(1.0f, -1.0f, 1.0f),
			// Right Face 1 Normals
			new Vertex(-1.0f, 1.0f, -1.0f),
			new Vertex(-1.0f, -1.0f, -1.0f),
			new Vertex(-1.0f, 1.0f, 1.0f),
			// Right Face 2 Normals
			new Vertex(-1.0f, 1.0f, 1.0f),
			new Vertex(-1.0f, -1.0f, -1.0f),
			new Vertex(-1.0f, -1.0f, 1.0f),
			// Top Face 1 Normals
			new Vertex(1.0f, 1.0f, -1.0f),
			new Vertex(-1.0f, 1.0f, 1.0f),
			new Vertex(-1.0f, 1.0f, -1.0f),
			// Top Face 2 Normals
			new Vertex(1.0f, 1.0f, -1.0f),
			new Vertex(-1.0f, 1.0f, 1.0f),
			new Vertex(1.0f, 1.0f, 1.0f),
			// Bottom Face 1 Normals
			new Vertex(1.0f, -1.0f, -1.0f),
			new Vertex(-1.0f, -1.0f, 1.0f),
			new Vertex(-1.0f, -1.0f, -1.0f),
			// Bottom Face 2 Normals
			new Vertex(1.0f, -1.0f, -1.0f),
			new Vertex(-1.0f, -1.0f, 1.0f),
			new Vertex(1.0f, -1.0f, 1.0f),
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