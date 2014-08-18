package com.tinfoilboy.warmupweekend.model.helpers;

import com.tinfoilboy.warmupweekend.util.Vertex;
import org.lwjgl.util.vector.Vector3f;

public class Face
{
	public Vertex vertex = new Vertex(0.0f, 0.0f, 0.0f);

	public Vertex normal = new Vertex(0.0f, 0.0f, 0.0f);

	public Face(Vertex vertex, Vertex normal)
	{
		this.vertex = vertex;
		this.normal = normal;
	}
}