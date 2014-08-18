package com.tinfoilboy.warmupweekend.gameplay;

import com.tinfoilboy.warmupweekend.WarmupWeekend;
import com.tinfoilboy.warmupweekend.physics.AxisAlignedBoundingBox;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class Camera
{
	protected float fov = 0.0f;

	protected float nearZ = 0.0f;

	protected float farZ = 0.0f;

	protected Vector3f position = new Vector3f(0.0f, 0.0f, 0.0f);

	protected float boundingBoxWidth = 8.0f;

	protected float boundingBoxHeight = 9.0f;

	protected float boundingBoxDepth = 8.0f;

	public boolean movingForward = false, movingBackward = false, movingLeft = false, movingRight = false, grounded = false, sprinting = false, is3D = true;

	public AxisAlignedBoundingBox boundingBox = null;

	public Camera(float fov, float nearZ, float farZ)
	{
		this.fov = fov;
		this.nearZ = nearZ;
		this.farZ = farZ;
		this.boundingBox = new AxisAlignedBoundingBox(this, "Collider", position, boundingBoxWidth, boundingBoxHeight, boundingBoxDepth);
	}

	public void create()
	{
		setup3D();
		glEnable(GL_COLOR_MATERIAL);
		FloatBuffer ambientLight = BufferUtils.createFloatBuffer(4).put(new float[] {0.2f, 0.2f, 0.2f, 1.0f});
		ambientLight.flip();
		FloatBuffer diffuseLight = BufferUtils.createFloatBuffer(4).put(new float[] {0.1f, 0.1f, 0.1f, 1.0f});
		diffuseLight.flip();
		FloatBuffer positionLight = BufferUtils.createFloatBuffer(4).put(new float[] {0.0f, 1.0f, 0.0f, 1.0f});
		positionLight.flip();
		glLight(GL_LIGHT0, GL_AMBIENT, ambientLight);
		glLight(GL_LIGHT0, GL_DIFFUSE, diffuseLight);
		glLight(GL_LIGHT0, GL_POSITION, positionLight);
		glShadeModel(GL_SMOOTH);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		glClearColor(0.0f, 0.6f, 0.4f, 1.0f);
	}

	public void setup3D()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		gluPerspective(45.0f, (float) Display.getWidth() / (float) Display.getHeight(), 0.1f, 1000.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(-position.getX(), -position.getY(), -position.getZ());
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glDepthMask(true);
		this.is3D = true;
	}

	public void setup2D()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(0.0f, 0.0f, 0.0f);
		glDisable(GL_LIGHTING);
		glDisable(GL_LIGHT0);
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_CULL_FACE);
		this.is3D = false;
	}

	public void switchDimension()
	{
		if (is3D)
			setup2D();
		else
			setup3D();
	}

	public void update()
	{
		glLoadIdentity();

		glTranslatef(-position.getX(), -position.getY(), -position.getZ());

		FloatBuffer positionLight = BufferUtils.createFloatBuffer(4).put(new float[] {0.0f, 1.0f, 0.0f, 1.0f});

		positionLight.flip();

		glLight(GL_LIGHT0, GL_POSITION, positionLight);

		this.checkMoving();

		this.boundingBox.update();
	}

	private void checkMoving()
	{
		float moveSpeed;

		if (sprinting)
			moveSpeed = 200.0f;
		else
			moveSpeed = 100.0f;

		moveSpeed *= WarmupWeekend.getInstance().frameLength;

		if (movingForward)
		{
			moveForward(moveSpeed);
		}

		if (movingBackward)
		{
			moveBackward(moveSpeed);
		}

		if (movingLeft)
		{
			moveLeft(moveSpeed);
		}

		if (movingRight)
		{
			moveRight(moveSpeed);
		}

		// Use a gravity multiplier to do something.
		int gravityMultiplier = 5;
		// Apply Gravity
		int gravity = 90 * gravityMultiplier;
		gravity *= WarmupWeekend.getInstance().frameLength;
		doGravity(gravity);
	}

	public void resize()
	{
		if (is3D)
			this.resize3D();
		else
			this.resize2D();
	}

	public void resize3D()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		gluPerspective(45.0f, (float) Display.getWidth() / (float) Display.getHeight(), 0.1f, 1000.0f);
		glMatrixMode(GL_MODELVIEW);
	}

	public void resize2D()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
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

	protected void playFootstep()
	{
		if (!SoundManager.footstep_left.isPlaying() && !SoundManager.footstep_right.isPlaying())
		{
			int footstepSound = WarmupWeekend.random.nextInt(2);

			if (footstepSound == 0) SoundManager.footstep_left.playAsSoundEffect(1.0f, 4.0f, false);
			else if (footstepSound == 1) SoundManager.footstep_right.playAsSoundEffect(1.0f, 4.0f, false);
		}
	}

	protected void playFootstep(int whichFoot)
	{
		if (!SoundManager.footstep_left.isPlaying() && !SoundManager.footstep_right.isPlaying())
		{
			if (whichFoot == 0) SoundManager.footstep_left.playAsSoundEffect(1.0f, 4.0f, false);
			else if (whichFoot == 1) SoundManager.footstep_right.playAsSoundEffect(1.0f, 4.0f, false);
		}
	}

	protected void moveForward(float moveSpeed)
	{
		for (int z = (int) (this.position.getZ()); z > this.position.getZ() - moveSpeed - boundingBoxDepth; z--)
		{
			// The maximum Z you can get.
			int maxZ = (int) (this.position.getZ() - moveSpeed - boundingBoxDepth) + 1;
			AxisAlignedBoundingBox aabb = WarmupWeekend.getInstance().currentLevel.getAxisAlignedBoundingBoxAt(new Vector3f(this.position.getX(), this.position.getY(), z));
			AxisAlignedBoundingBox potentialCameraBB = new AxisAlignedBoundingBox(this, "collider", new Vector3f(this.position.getX(), this.position.getY(), z), boundingBoxWidth, boundingBoxHeight, boundingBoxDepth);
			if (aabb != null)
			{
				if (potentialCameraBB.colliding(aabb) && aabb.COLLIDER_TYPE.equalsIgnoreCase("collider"))
				{
					break;
				}
				else if (!potentialCameraBB.colliding(aabb) && z == maxZ)
				{
					if (this.grounded)
					{
						playFootstep();
					}
					this.position.z -= moveSpeed;
					break;
				}
			}
			else if (z == maxZ)
			{
				if (this.grounded)
				{
					playFootstep();
				}
				this.position.z -= moveSpeed;
				break;
			}
		}
	}

	protected void moveBackward(float moveSpeed)
	{
		for (int z = (int) (this.position.getZ()); z < this.position.getZ() + moveSpeed + boundingBoxDepth; z++)
		{
			// The maximum Z you can get.
			int maxZ = (int) (this.position.getZ() + moveSpeed + boundingBoxDepth) - 1;
			AxisAlignedBoundingBox aabb = WarmupWeekend.getInstance().currentLevel.getAxisAlignedBoundingBoxAt(new Vector3f(this.position.getX(), this.position.getY(), z));
			AxisAlignedBoundingBox potentialCameraBB = new AxisAlignedBoundingBox(this, "collider", new Vector3f(this.position.getX(), this.position.getY(), z), boundingBoxWidth, boundingBoxHeight, boundingBoxDepth);
			if (aabb != null)
			{
				if (potentialCameraBB.colliding(aabb) && aabb.COLLIDER_TYPE.equalsIgnoreCase("collider"))
				{
					break;
				}
				else if (!potentialCameraBB.colliding(aabb) && z == maxZ)
				{
					if (this.grounded)
					{
						playFootstep();
					}
					this.position.z += moveSpeed;
					break;
				}
			}
			else if (z == maxZ)
			{
				if (this.grounded)
				{
					playFootstep();
				}
				this.position.z += moveSpeed;
				break;
			}
		}
	}

	protected void moveLeft(float moveSpeed)
	{
		for (int x = (int) (this.position.getX()); x > this.position.getX() - moveSpeed - boundingBoxWidth; x--)
		{
			// The maximum Z you can get.
			int maxX = (int) (this.position.getX() - moveSpeed - boundingBoxWidth) + 1;
			AxisAlignedBoundingBox aabb = WarmupWeekend.getInstance().currentLevel.getAxisAlignedBoundingBoxAt(new Vector3f(x, this.position.getY(), this.position.getZ()));
			AxisAlignedBoundingBox potentialCameraBB = new AxisAlignedBoundingBox(this, "collider", new Vector3f(x, this.position.getY(), this.position.getZ()), boundingBoxWidth, boundingBoxHeight, boundingBoxDepth);
			if (aabb != null)
			{
				if (potentialCameraBB.colliding(aabb) && aabb.COLLIDER_TYPE.equalsIgnoreCase("collider"))
				{
					break;
				}
				else if (!potentialCameraBB.colliding(aabb) && x == maxX)
				{
					if (this.grounded)
					{
						playFootstep(0);
					}
					this.position.x -= moveSpeed;
					break;
				}
			}
			else if (x == maxX)
			{
				if (this.grounded)
				{
					playFootstep(0);
				}
				this.position.x -= moveSpeed;
				break;
			}
		}
	}

	protected void moveRight(float moveSpeed)
	{
		for (int x = (int) (this.position.getX()); x < this.position.getX() + moveSpeed + boundingBoxWidth; x++)
		{
			// The maximum Z you can get.
			int maxX = (int) (this.position.getX() + moveSpeed + boundingBoxWidth) - 1;
			AxisAlignedBoundingBox aabb = WarmupWeekend.getInstance().currentLevel.getAxisAlignedBoundingBoxAt(new Vector3f(x, this.position.getY(), this.position.getZ()));
			AxisAlignedBoundingBox potentialCameraBB = new AxisAlignedBoundingBox(this, "collider", new Vector3f(x, this.position.getY(), this.position.getZ()), boundingBoxWidth, boundingBoxHeight, boundingBoxDepth);
			if (aabb != null)
			{
				if (potentialCameraBB.colliding(aabb) && aabb.COLLIDER_TYPE.equalsIgnoreCase("collider"))
				{
					break;
				}
				else if (!potentialCameraBB.colliding(aabb) && x == maxX)
				{
					if (this.grounded)
					{
						playFootstep(1);
					}
					this.position.x += moveSpeed;
					break;
				}
			}
			else if (x == maxX)
			{
				if (this.grounded)
				{
					playFootstep(1);
				}
				this.position.x += moveSpeed;
				break;
			}
		}
	}

	protected void doGravity(int gravity)
	{
		for (int y = (int) (this.position.getY()); y > this.position.getY() - gravity - boundingBoxHeight; y--)
		{
			// The maximum Z you can get.
			int maxY = (int) (this.position.getY() - gravity - boundingBoxHeight) + 1;
			AxisAlignedBoundingBox aabb = WarmupWeekend.getInstance().currentLevel.getAxisAlignedBoundingBoxAt(new Vector3f(this.position.getX(), y, this.position.getZ()));
			AxisAlignedBoundingBox potentialCameraBB = new AxisAlignedBoundingBox(this, "collider", new Vector3f(this.position.getX(), y, this.position.getZ()), boundingBoxWidth, boundingBoxHeight, boundingBoxDepth);
			if (aabb != null)
			{
				if (potentialCameraBB.colliding(aabb) && aabb.COLLIDER_TYPE.equalsIgnoreCase("collider"))
				{
					if (!this.grounded)
						this.grounded = true;
					break;
				}
				else if (!potentialCameraBB.colliding(aabb) && y == maxY)
				{
					if (this.grounded)
						this.grounded = false;
					this.position.y -= gravity;
					break;
				}
			}
			else if (y == maxY)
			{
				if (this.grounded)
					this.grounded = false;
				this.position.y -= gravity;
				break;
			}
		}
	}
}