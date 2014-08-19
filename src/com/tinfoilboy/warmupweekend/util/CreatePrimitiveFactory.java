package com.tinfoilboy.warmupweekend.util;

import com.tinfoilboy.warmupweekend.graphics.sprites.Sprite;
import org.lwjgl.util.vector.Vector3f;

public class CreatePrimitiveFactory
{
	public static PrimitiveCoordinates makeCube(Vector3f position, Vector3f sizes, Sprite sprite)
	{
		Vertex[] vertices = new Vertex[] {
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
		Vertex[] normals = new Vertex[] {
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

		TextureCoordinate[] textureCoordinates = new TextureCoordinate[] {
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
		return new PrimitiveCoordinates(vertices, normals, textureCoordinates);
	}
}