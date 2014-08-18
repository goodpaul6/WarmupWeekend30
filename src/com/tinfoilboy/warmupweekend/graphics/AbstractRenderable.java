package com.tinfoilboy.warmupweekend.graphics;

import com.tinfoilboy.warmupweekend.graphics.sprites.Sprite;
import com.tinfoilboy.warmupweekend.util.TextureCoordinate;
import com.tinfoilboy.warmupweekend.util.Vertex;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

public class AbstractRenderable implements Renderable
{
	/**
	 * The vertices of this renderable object.
	 */
	protected Vertex[] vertices = null;

	/**
	 * The normals of this renderable object.
	 * This is optional.
	 */
	protected Vertex[] normals = null;

	/**
	 * The texture coordinates of this renderable object.
	 * Only set if we have a texture for this renderable object.
	 */
	protected TextureCoordinate[] textureCoordinates = null;

	/**
	 * An ID for the vertices in OpenGL.
	 */
	protected int vertexID = 0;

	/**
	 * An ID for the normals in OpenGL.
	 */
	protected int normalID = 0;

	/**
	 * An ID for the texture coordinates in OpenGL.
	 */
	protected int textureCoordinateID = 0;

	/**
	 * The position of this renderable object.
	 */
	protected Vector3f position = new Vector3f(0.0f, 0.0f, 0.0f);

	/**
	 * The sizes of this renderable object.
	 */
	protected Vector3f sizes = new Vector3f(0.0f, 0.0f, 0.0f);

	/**
	 * The optional sprite associated with this renderable object.
	 */
	protected Sprite sprite = null;

	/**
	 * A buffer containing the vertices for OpenGL.
	 */
	protected FloatBuffer vertexBuffer = null;

	/**
	 * A buffer containing the vertices for OpenGL.
	 */
	protected FloatBuffer normalBuffer = null;

	/**
	 * A buffer containing the texture coordinates for OpenGL.
	 */
	protected FloatBuffer textureCoordinateBuffer = null;

	public AbstractRenderable(Vector3f position, float size)
	{
		this.position = position;
		this.sizes = new Vector3f(size, size, size);
		this.init();
	}

	public AbstractRenderable(Vector3f position, Vector3f sizes)
	{
		this.position = position;
		this.sizes = sizes;
		this.init();
	}

	public AbstractRenderable(Vector3f position, float size, Sprite sprite)
	{
		this.position = position;
		this.sizes = new Vector3f(size, size, size);
		this.sprite = sprite;
		this.init();
	}

	public AbstractRenderable(Vector3f position, Vector3f sizes, Sprite sprite)
	{
		this.position = position;
		this.sizes = sizes;
		this.sprite = sprite;
		this.init();
	}

	@Override
	public void beforeInit()
	{
	}

	@Override
	public void init()
	{
		this.beforeInit();
		this.fillAndBindVertexBuffer();
		this.vertexBuffer.clear();
		if (this.hasTextures())
		{
			this.fillAndBindTextureCoordinateBuffer();
			this.textureCoordinateBuffer.clear();
		}
		if (this.usesNormals())
		{
			this.fillAndBindNormalBuffer();
			this.normalBuffer.clear();
		}
	}

	/**
	 * Note: for performance reasons the texture isn't bound automatically, before drawing a batch of objects that use the same sprite sheet bind the texture.
	 */
	@Override
	public void draw()
	{
		if (!this.hasTextures())
			glDisable(GL_TEXTURE_2D);
		glPushMatrix();
		glTranslatef(position.getX(), position.getY(), position.getZ());
		glEnableClientState(GL_VERTEX_ARRAY);
		if (this.hasTextures())
			glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		if (this.usesNormals())
			glEnableClientState(GL_NORMAL_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		glVertexPointer(3, GL_FLOAT, 3 << 2, 0L);
		if (this.hasTextures())
		{
			glBindBuffer(GL_ARRAY_BUFFER, textureCoordinateID);
			glTexCoordPointer(2, GL_FLOAT, 0, 0L);
		}
		if (this.usesNormals())
		{
			glBindBuffer(GL_ARRAY_BUFFER, normalID);
			glNormalPointer(GL_FLOAT, 0, 0L);
		}
		glDrawArrays(GL_TRIANGLES, 0, this.vertices.length * 3);
		if (this.usesNormals())
			glDisableClientState(GL_NORMAL_ARRAY);
		if (this.hasTextures())
			glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glPopMatrix();
		if (!this.hasTextures())
			glEnable(GL_TEXTURE_2D);
	}

	@Override
	public void dispose()
	{
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteBuffers(vertexID);
		if (this.hasTextures())
			glDeleteBuffers(textureCoordinateID);
		if (this.usesNormals())
			glDeleteBuffers(normalID);
	}

	protected void fillAndBindVertexBuffer()
	{
		this.vertexID = glGenBuffers();
		this.vertexBuffer = BufferUtils.createFloatBuffer(this.vertices.length * 3);
		float[] verts = Vertex.convertToFloatArray(vertices);
		this.vertexBuffer.put(verts);
		this.vertexBuffer.flip();
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
	}

	protected void fillAndBindTextureCoordinateBuffer()
	{
		this.textureCoordinateID = glGenBuffers();
		this.textureCoordinateBuffer = BufferUtils.createFloatBuffer(this.textureCoordinates.length * 2);
		float[] texCoords = TextureCoordinate.convertToFloatArray(textureCoordinates);
		this.textureCoordinateBuffer.put(texCoords);
		this.textureCoordinateBuffer.flip();
		glBindBuffer(GL_ARRAY_BUFFER, textureCoordinateID);
		glBufferData(GL_ARRAY_BUFFER, textureCoordinateBuffer, GL_STATIC_DRAW);
	}

	protected void fillAndBindNormalBuffer()
	{
		this.normalID = glGenBuffers();
		this.normalBuffer = BufferUtils.createFloatBuffer(this.normals.length * 3);
		float[] normals = Vertex.convertToFloatArray(this.normals);
		this.normalBuffer.put(normals);
		this.normalBuffer.flip();
		glBindBuffer(GL_ARRAY_BUFFER, normalID);
		glBufferData(GL_ARRAY_BUFFER, normalBuffer, GL_STATIC_DRAW);
	}

	protected boolean hasTextures()
	{
		return sprite != null;
	}

	public Sprite getSprite()
	{
		return this.sprite;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public Vector3f getSizes()
	{
		return sizes;
	}

	protected boolean usesNormals() { return this.normals != null; }
}