package com.tinfoilboy.warmupweekend.gameplay;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class Camera
{
	protected float fov = 0.0f;

	protected float nearZ = 0.0f;

	protected float farZ = 0.0f;

	protected Vector3f position = new Vector3f(0.0f, 0.0f, 0.0f);

	public boolean movingForward = false, movingBackward = false, movingLeft = false, movingRight = false;

	private float moveSpeed = 1.0f;

	public Camera(float fov, float nearZ, float farZ)
	{
		this.fov = fov;
		this.nearZ = nearZ;
		this.farZ = farZ;
	}

	public void create()
	{
		glMatrixMode(GL_PROJECTION);
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		gluPerspective(45.0f, (float) Display.getWidth() / (float) Display.getHeight(), 0.1f, 1000.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(-position.getX(), -position.getY(), -position.getZ());
		glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		//glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glClearColor(0.0f, 0.6f, 0.7f, 1.0f);
	}

	public void update()
	{
		glLoadIdentity();

		glTranslatef(-position.getX(), -position.getY(), -position.getZ());

		this.checkMoving();
	}

	private void checkMoving()
	{
		if (movingForward)
			this.position.z -= moveSpeed;
		if (movingBackward)
			this.position.z += moveSpeed;
		if (movingLeft)
			this.position.x -= moveSpeed;
		if (movingRight)
			this.position.x += moveSpeed;
	}

	public void resize()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		gluPerspective(45.0f, (float) Display.getWidth() / (float) Display.getHeight(), 0.1f, 1000.0f);
		glMatrixMode(GL_MODELVIEW);
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
	}

	public Vector3f getPosition()
	{
		return position;
	}
}