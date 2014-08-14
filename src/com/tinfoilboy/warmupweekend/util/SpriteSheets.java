package com.tinfoilboy.warmupweekend.util;

import com.tinfoilboy.warmupweekend.graphics.sprites.SpriteSheet;

import java.util.HashMap;

public class SpriteSheets
{
	private static HashMap<String, SpriteSheet> spriteSheets = new HashMap<String, SpriteSheet>();

	public static boolean addSpriteSheet(String spriteSheetName, SpriteSheet sheet)
	{
		if (!spriteSheetName.isEmpty() && sheet != null && !spriteSheets.containsKey(spriteSheetName.toLowerCase()))
		{
			spriteSheets.put(spriteSheetName.toLowerCase(), sheet);
			return true;
		}
		System.err.println("Error! Could not add sprite sheet due to the name being empty, the sheet being null, or the name already being stored.");
		return false;
	}

	public static SpriteSheet getSpriteSheet(String spriteSheetName)
	{
		if (!spriteSheetName.isEmpty() && spriteSheets.containsKey(spriteSheetName.toLowerCase()))
		{
			return spriteSheets.get(spriteSheetName.toLowerCase());
		}
		else
		{
			System.err.println("Error! Could not find the sprite sheet.");
			return null;
		}
	}
}