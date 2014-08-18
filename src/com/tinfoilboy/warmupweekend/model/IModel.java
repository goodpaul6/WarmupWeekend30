package com.tinfoilboy.warmupweekend.model;

/**
 * A model interface, use to create more complex objects, i.e. not cubes or quads.
 * */
public interface IModel
{
	/**
	 * Initialize this model.
	 * */
	public void init();

	/**
	 * Initialize this model.
	 * */
	public void update();

 	/**
	 * Render this model.
	 * */
	public void render();

	/**
	 * Dispose of this model.
	 * */
	public void dispose();
}