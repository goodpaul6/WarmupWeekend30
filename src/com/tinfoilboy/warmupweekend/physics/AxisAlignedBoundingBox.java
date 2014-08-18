package com.tinfoilboy.warmupweekend.physics;

import com.tinfoilboy.warmupweekend.gameplay.Camera;
import com.tinfoilboy.warmupweekend.graphics.AbstractRenderable;
import org.lwjgl.util.vector.Vector3f;

public class AxisAlignedBoundingBox
{
	/**
	 * The minimum x coordinate of the bounding box.
	 * */
	public float minX = 0.0f;

	/**
	 * The minimum y coordinate of the bounding box.
	 * */
	public float minY = 0.0f;

	/**
	 * The minimum z coordinate of the bounding box.
	 * */
	public float minZ = 0.0f;

	/**
	 * The maximum x coordinate of the bounding box.
	 * */
	public float maxX = 0.0f;

	/**
	 * The maximum y coordinate of the bounding box.
	 * */
	public float maxY = 0.0f;

	/**
	 * The maximum z coordinate of the bounding box.
	 * */
	public float maxZ = 0.0f;

	/**
	 * The width of this axis aligned bounding box.
	 * */
	public float width = 0.0f;

	/**
	 * The height of this axis aligned bounding box.
	 * */
	public float height = 0.0f;

	/**
	 * The depth of this axis aligned bounding box.
	 * */
	public float depth = 0.0f;

	/**
	 * The parent of this axis aligned bounding box.
	 * */
	public Object parent = null;

	public final String COLLIDER_TYPE;

	/**
	 * Creates a new Axis Aligned Bounding Box.
	 * <p>
	 * The parameters are as follows.
	 * <p>
	 * @param parent the parent of this bounding box (either a camera, or an Abstract Renderable)
	 * @param start the start of this bounding box
	 * @param width the width of this bounding box
	 * @param height the height of this bounding box
	 * @param depth the depth of this bounding box
	 * */
	public AxisAlignedBoundingBox(Object parent, final String COLLIDER_TYPE, Vector3f start, final float width, final float height, final float depth)
	{
		this.parent = parent;
		this.COLLIDER_TYPE = COLLIDER_TYPE;
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.minX = start.getX() - width;
		this.minY = start.getY() - height;
		this.minZ = start.getZ() - depth;
		this.maxX = start.getX() + width;
		this.maxY = start.getY() + height;
		this.maxZ = start.getZ() + depth;
	}

	public void update()
	{
		if (parent instanceof Camera)
		{
			Camera camParent = (Camera) parent;
			this.minX = camParent.getPosition().getX() - width;
			this.minY = camParent.getPosition().getY() - height;
			this.minZ = camParent.getPosition().getZ() - depth;
			this.maxX = camParent.getPosition().getX() + width;
			this.maxY = camParent.getPosition().getY() + height;
			this.maxZ = camParent.getPosition().getZ() + depth;
		}
		else if (parent instanceof AbstractRenderable)
		{
			AbstractRenderable cubeParent = (AbstractRenderable) parent;
			this.minX = cubeParent.getPosition().getX() - width;
			this.minY = cubeParent.getPosition().getY() - height;
			this.minZ = cubeParent.getPosition().getZ() - depth;
			this.maxX = cubeParent.getPosition().getX() + width;
			this.maxY = cubeParent.getPosition().getY() + height;
			this.maxZ = cubeParent.getPosition().getZ() + depth;
		}
	}

	public boolean colliding(AxisAlignedBoundingBox axisAlignedBoundingBox)
	{
		return (this.maxX >= axisAlignedBoundingBox.minX) && (this.maxY >= axisAlignedBoundingBox.minY) && (this.maxZ >= axisAlignedBoundingBox.minZ) &&
				(this.minX <= axisAlignedBoundingBox.maxX) && (this.minY <= axisAlignedBoundingBox.maxY) && (this.minZ <= axisAlignedBoundingBox.maxZ);
	}
}