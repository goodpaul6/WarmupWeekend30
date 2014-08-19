package com.tinfoilboy.warmupweekend.util;

import java.util.ArrayList;

public class TextureCoordinate
{
	protected float u = 0.0f, v = 0.0f;

	public TextureCoordinate(float u, float v)
	{
		this.u = u;
		this.v = v;
	}

	public float getU()
	{
		return u;
	}

	public float getV()
	{
		return v;
	}

	public static float[] convertToFloatArray(TextureCoordinate textureCoordinate)
	{
		return new float[] { textureCoordinate.getU(), textureCoordinate.getV() };
	}

	public static float[] convertToFloatArray(TextureCoordinate[] textureCoordinates)
	{
		// Using an ArrayList.
		ArrayList<Float> floats = new ArrayList<Float>();
		// For every vertex, add their coordinates to the arraylist.
		for (int i = 0; i < textureCoordinates.length; i++)
		{
			floats.add(textureCoordinates[i].u);
			floats.add(textureCoordinates[i].v);
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