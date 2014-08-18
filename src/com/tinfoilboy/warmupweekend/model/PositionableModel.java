package com.tinfoilboy.warmupweekend.model;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

public class PositionableModel
{
	/**
	 * The model that will be rendered.
	 * */
	public Model model = null;

	/**
	 * The position of the model that will be rendered.
	 * */
	public Vector3f position = new Vector3f(0.0f, 0.0f, 0.0f);

	/**
	 * The rotation of this model.
	 * */
	public Quaternion rotation = new Quaternion(0.0f, 0.0f, 0.0f, 0.0f);

	public PositionableModel(Model model)
	{
		this.model = model;
	}

	public PositionableModel(Model model, Vector3f position)
	{
		this(model);
		this.position = position;
	}

	public PositionableModel(Model model, Vector3f position, Quaternion rotation)
	{
		this(model, position);
		this.rotation = rotation;
	}
}