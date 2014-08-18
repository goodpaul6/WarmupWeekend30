package com.tinfoilboy.warmupweekend.util;

import static com.tinfoilboy.warmupweekend.WarmupWeekend.*;
import org.lwjgl.util.vector.Vector3f;

public class Culling
{
	public static boolean shouldShow(Vector3f position, Vector3f sizes, float renderDistance)
	{
		return (getInstance().camera.getPosition().getX() >= position.getX() - sizes.getX() - renderDistance && getInstance().camera.getPosition().getX() <= position.getX() + sizes.getX() + renderDistance) &&
				(getInstance().camera.getPosition().getY() >= position.getY() - sizes.getY() - renderDistance && getInstance().camera.getPosition().getY() <= position.getY() + sizes.getY() + renderDistance) &&
				(getInstance().camera.getPosition().getZ() >= position.getZ() - sizes.getZ() - renderDistance && getInstance().camera.getPosition().getZ() <= position.getZ() + sizes.getZ() + renderDistance);
	}
}