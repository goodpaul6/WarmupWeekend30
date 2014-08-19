package com.tinfoilboy.warmupweekend.graphics;

import com.tinfoilboy.warmupweekend.util.TextureCoordinate;
import com.tinfoilboy.warmupweekend.util.Vertex;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class VBOBatcher
{
	/**
	 * The batch id for the VBO.
	 * */
	public int batchID = 0;

	public int batchLimit = 0;

	public int currentBatchNumbers = 0;

	private boolean hasMadeData = false;

 	/**
	 * An array list of vertices.
	 * */
	public ArrayList<Vertex> vertices = new ArrayList<Vertex>();

	/**
	 * An array list of normals.
	 * */
	public ArrayList<Vertex> normals = new ArrayList<Vertex>();

	/**
	 * An array list of texture coordinates.
	 * */
	public ArrayList<TextureCoordinate> textureCoordinates = new ArrayList<TextureCoordinate>();

 	/**
	 * The buffer for the vertices.
	 * */
	public FloatBuffer batchBuffer;

	public static VBOBatcher instance = null;

	public VBOBatcher()
	{
		this(1024);
	}

	public VBOBatcher(int batchLimit)
	{
		this.batchLimit = batchLimit;
		this.batchBuffer = BufferUtils.createFloatBuffer(batchLimit);
		instance = this;
	}

	/**
	 * Add a vertex with texture coordinates, and normals to the buffer.
	 * */
	public void addVertexWithTextureCoordinateAndNormal(Vertex vert, TextureCoordinate textureCoord, Vertex norm)
	{
		vertices.addAll(Arrays.asList(vert));
		textureCoordinates.addAll(Arrays.asList(textureCoord));
		normals.addAll(Arrays.asList(norm));
		float[] v = Vertex.convertToFloatArray(vert);
		float[] t = TextureCoordinate.convertToFloatArray(textureCoord);
		float[] n = Vertex.convertToFloatArray(norm);
		if (currentBatchNumbers + 8 > batchLimit)
		{
			flush();
		}
		currentBatchNumbers += v.length + n.length + t.length;
		batchBuffer.put(v).put(n).put(t);
	}

	/**
	 * Add an array of vertices, texture coordinates, and normals to their respective buffers.
	 * */
	public void addVerticesWithTextureCoordinatesAndNormals(Vertex[] verts, TextureCoordinate[] textureCoords, Vertex[] norms)
	{
		vertices.addAll(Arrays.asList(verts));
		textureCoordinates.addAll(Arrays.asList(textureCoords));
		normals.addAll(Arrays.asList(norms));
		float[] v = Vertex.convertToFloatArray(verts);
		float[] t = TextureCoordinate.convertToFloatArray(textureCoords);
		float[] n = Vertex.convertToFloatArray(norms);
		currentBatchNumbers += v.length + t.length + n.length;
		System.out.println(t.length);
		if (currentBatchNumbers + 8 > batchLimit)
		{
			flush();
		}
		batchBuffer.put(v).put(n).put(t);
	}

	public void begin()
	{
		this.currentBatchNumbers = 0;
		batchBuffer.clear();
	}

	/**
	 * Draw the batched VBO.
	 * */
	public void draw()
	{
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		glEnableClientState(GL_NORMAL_ARRAY);
		{
			glBindBuffer(GL_ARRAY_BUFFER, batchID);
			glVertexPointer(3, GL_FLOAT, 32, 0);
			glNormalPointer(GL_FLOAT, 32, 12);
			glTexCoordPointer(2, GL_FLOAT, 32, 24);
			glDrawArrays(GL_TRIANGLES, 0, currentBatchNumbers);
		}
		glDisableClientState(GL_NORMAL_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void flush()
	{
		batchBuffer.flip();
		if (batchID == 0)
			batchID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, batchID);
		glBufferData(GL_ARRAY_BUFFER, batchBuffer, GL_STATIC_DRAW);
		draw();
		vertices.clear();
		normals.clear();
		textureCoordinates.clear();
		batchBuffer.clear();
		currentBatchNumbers = 0;
	}

	public void end()
	{
		this.flush();
	}

	/**
	 * Dispose of everything in the batched VBO.
	 * */
	public void dispose()
	{
		glDeleteBuffers(batchID);
		batchBuffer.clear();
		batchBuffer = null;
		currentBatchNumbers = 0;
	}
}