package com.tinfoilboy.warmupweekend.model;

import com.tinfoilboy.warmupweekend.model.helpers.Face;
import com.tinfoilboy.warmupweekend.util.Vertex;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Model implements IModel
{
	public int vertexID = 0;

	public int normalID = 0;

	public FloatBuffer vertexBuffer;

	public FloatBuffer normalBuffer;

	public ArrayList<Vertex> vertices = new ArrayList<Vertex>();

	public ArrayList<Vertex> normals = new ArrayList<Vertex>();

	public ArrayList<Face> faces = new ArrayList<Face>();

	public Vector3f position = new Vector3f(0, 0, 0);

	/**
	 * Initialize this model.
	 */
	@Override
	public void init()
	{
		vertexID = glGenBuffers();
		normalID = glGenBuffers();
		vertexBuffer = BufferUtils.createFloatBuffer(this.faces.size() * 36);
		normalBuffer = BufferUtils.createFloatBuffer(this.faces.size() * 36);
		for (Face face : this.faces)
		{
			vertexBuffer.put(Vertex.convertToFloatArray(this.vertices.get((int) face.vertex.getX() - 1)));
			vertexBuffer.put(Vertex.convertToFloatArray(this.vertices.get((int) face.vertex.getY() - 1)));
			vertexBuffer.put(Vertex.convertToFloatArray(this.vertices.get((int) face.vertex.getZ() - 1)));
			normalBuffer.put(Vertex.convertToFloatArray(this.normals.get((int) face.normal.getX() - 1)));
			normalBuffer.put(Vertex.convertToFloatArray(this.normals.get((int) face.normal.getY() - 1)));
			normalBuffer.put(Vertex.convertToFloatArray(this.normals.get((int) face.normal.getZ() - 1)));
		}
		vertexBuffer.flip();
		normalBuffer.flip();
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, normalID);
		glBufferData(GL_ARRAY_BUFFER, normalBuffer, GL_STATIC_DRAW);
		vertexBuffer.clear();
		normalBuffer.clear();
	}

	/**
	 * Render this model.
	 */
	@Override
	public void render()
	{
		glPushMatrix();
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_NORMAL_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		glVertexPointer(3, GL_FLOAT, 0, 0L);
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		glNormalPointer(GL_FLOAT, 0, 0L);
		glDrawArrays(GL_TRIANGLES, 0, this.faces.size() * 3);
		glDisableClientState(GL_NORMAL_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glColor3f(1.0f, 1.0f, 1.0f);
		glPopMatrix();
	}

	/**
	 * Initialize this model.
	 */
	@Override
	public void update()
	{
	}

	/**
	 * Dispose of this model.
	 */
	@Override
	public void dispose()
	{
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteBuffers(vertexID);
		glDeleteBuffers(normalID);
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
	}
}