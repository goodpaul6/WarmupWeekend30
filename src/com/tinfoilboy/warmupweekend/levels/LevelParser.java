package com.tinfoilboy.warmupweekend.levels;

import com.tinfoilboy.warmupweekend.graphics.AbstractRenderable;
import com.tinfoilboy.warmupweekend.graphics.Cube;
import com.tinfoilboy.warmupweekend.graphics.sprites.Sprite;
import com.tinfoilboy.warmupweekend.util.SpriteSheets;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelParser
{
	public static Level parseLevel(File levelFile) throws IOException
	{
		Level tempLevel = new Level();
		BufferedReader levelReader = new BufferedReader(new FileReader(levelFile));
		String currentLine = "";
		String currentSpriteSheetName = "";
		float cubePadding = 2.0f;
		while ((currentLine = levelReader.readLine()) != null)
		{
			if (currentLine.startsWith("//") || currentLine.isEmpty())
			{
				continue;
			}
			else
			{
				if (currentLine.startsWith("author"))
				{
					// Set the level's author name.
					String[] splitVars = currentLine.split(" ");
					tempLevel.setAuthor(splitVars[1]);
				}
				else if (currentLine.startsWith("name"))
				{
					// Set the level name
					String[] splitVars = currentLine.split(" ");
					tempLevel.setLevelName(splitVars[1]);
				}
				else if (currentLine.startsWith("current_sheet"))
				{
					// Set the level name
					String[] splitVars = currentLine.split(" ");
					currentSpriteSheetName = splitVars[1];
				}
				else if (currentLine.startsWith("cube"))
				{
					// Set the level name
					String[] splitVars = currentLine.split(" ");
					float cubeX = Float.parseFloat(splitVars[1]);
					float cubeY = Float.parseFloat(splitVars[2]);
					float cubeZ = Float.parseFloat(splitVars[3]);
					float cubeSize = Float.parseFloat(splitVars[4]);
					if (splitVars.length <= 5)
						tempLevel.addRenderable(new Cube(new Vector3f(cubeX, cubeY, cubeZ), cubeSize));
					else
					{
						System.out.println(currentSpriteSheetName);
						if (!currentSpriteSheetName.isEmpty() && SpriteSheets.getSpriteSheet(currentSpriteSheetName.toLowerCase()).getSprite(splitVars[5]) != null)
						{
							tempLevel.addRenderable(new Cube(new Vector3f(cubeX * cubeSize * cubePadding, cubeY * cubeSize * cubePadding, cubeZ * cubeSize * cubePadding), cubeSize, SpriteSheets.getSpriteSheet(currentSpriteSheetName).getSprite(splitVars[5])));
						}
						else
						{
							System.err.println("Error! Sprite cannot be found!");
						}
					}
				}
				else if (currentLine.startsWith("wall"))
				{
					// Set the level name
					String[] splitVars = currentLine.split(" ");
					int wallX = Integer.parseInt(splitVars[1]);
					int wallY = Integer.parseInt(splitVars[2]);
					int wallZ = Integer.parseInt(splitVars[3]);
					int wallLength = Integer.parseInt(splitVars[4]);
					int wallWidth = Integer.parseInt(splitVars[5]);
					int wallHeight = Integer.parseInt(splitVars[6]);
					float wallCubeSize = Float.parseFloat(splitVars[7]);
					Sprite wallCubeSprite = SpriteSheets.getSpriteSheet(currentSpriteSheetName).getSprite(splitVars[8]);
					tempLevel.addRenderables(makeWall(new Vector3f(wallX, wallY, wallZ), wallLength, wallWidth, wallHeight, wallCubeSize, cubePadding, wallCubeSprite));
				}
			}
		}
		levelReader.close();
		return tempLevel;
	}

	protected static AbstractRenderable[] makeWall(Vector3f wallStart, int wallLength, int wallWidth, int wallHeight, float wallCubeSize, float cubePadding, Sprite cubeSprite)
	{
		ArrayList<AbstractRenderable> wallPieces = new ArrayList<AbstractRenderable>();
		for (int i = (int) wallStart.getX(); i < (int) wallStart.getX() + wallWidth; i++)
		{
			for (int j = (int) wallStart.getY(); j < (int) wallStart.getY() + wallHeight; j++)
			{
				for (int k = (int) wallStart.getZ(); k < (int) wallStart.getZ() + wallLength; k++)
				{
					wallPieces.add(new Cube(new Vector3f(i * wallCubeSize * cubePadding, j * wallCubeSize * cubePadding, k * wallCubeSize * cubePadding), wallCubeSize, cubeSprite));
				}
			}
		}
		AbstractRenderable[] returnableArray = new AbstractRenderable[wallPieces.size()];
		int i = 0;
		for (AbstractRenderable renderable : wallPieces)
		{
			returnableArray[i] = renderable;
			i++;
		}
		return returnableArray;
	}
}