package com.tinfoilboy.warmupweekend.util;

public class PrimitiveCoordinates
{
	public Vertex[] vertices = null;

	public Vertex[] normals = null;

	public TextureCoordinate[] textureCoordinates = null;

	public PrimitiveCoordinates(Vertex[] vertices, Vertex[] normals, TextureCoordinate[] textureCoordinates)
	{
		this.vertices = vertices;
		this.normals = normals;
		this.textureCoordinates = textureCoordinates;
	}
}