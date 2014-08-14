package com.tinfoilboy.warmupweekend.util;

import java.util.ArrayList;

public class Vertex
{
	protected float x = 0.0f, y = 0.0f, z = 0.0f;

	public Vertex(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getZ()
	{
		return z;
	}

	public static float[] convertToFloatArray(Vertex[] vertices)
	{
		// Using an ArrayList.
		ArrayList<Float> floats = new ArrayList<Float>();
		// For every vertex, add their coordinates to the arraylist.
		for (int i = 0; i < vertices.length; i++)
		{
			floats.add(vertices[i].x);
			floats.add(vertices[i].y);
			floats.add(vertices[i].z);
		}
		float[] newFloats = new float[floats.size()];
		int i = 0;
		for (Float flt : floats)
		{
			newFloats[i++] = flt;
		}
		return newFloats;
	}
}