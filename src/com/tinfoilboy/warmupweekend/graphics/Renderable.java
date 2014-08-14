package com.tinfoilboy.warmupweekend.graphics;

public interface Renderable
{
	public void beforeInit();

	public void init();

	/**
	 * Note: for performance reasons the texture isn't bound automatically, before drawing a batch of objects that use the same sprite sheet bind the texture.
	 */
	public void draw();

	public void dispose();
}