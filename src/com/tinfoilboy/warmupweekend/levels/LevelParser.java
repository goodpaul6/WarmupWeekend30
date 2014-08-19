package com.tinfoilboy.warmupweekend.levels;

import com.tinfoilboy.warmupweekend.graphics.AbstractRenderable;
import com.tinfoilboy.warmupweekend.graphics.Cube;
import com.tinfoilboy.warmupweekend.graphics.VBOBatcher;
import com.tinfoilboy.warmupweekend.graphics.sprites.Sprite;
import com.tinfoilboy.warmupweekend.physics.AxisAlignedBoundingBox;
import com.tinfoilboy.warmupweekend.util.CreatePrimitiveFactory;
import com.tinfoilboy.warmupweekend.util.PrimitiveCoordinates;
import com.tinfoilboy.warmupweekend.util.SpriteSheets;
import com.tinfoilboy.warmupweekend.util.Vertex;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
					String[] splitVars = currentLine.split(" ");
					tempLevel.setAuthor(splitVars[1]);
				}
				else if (currentLine.startsWith("name"))
				{
					String[] splitVars = currentLine.split(" ");
					tempLevel.setLevelName(splitVars[1]);
				}
				else if (currentLine.startsWith("current_sheet"))
				{
					String[] splitVars = currentLine.split(" ");
					currentSpriteSheetName = splitVars[1];
				}
				else if (currentLine.startsWith("cube"))
				{
					String[] splitVars = currentLine.split(" ");
					float cubeX = Float.parseFloat(splitVars[1]);
					float cubeY = Float.parseFloat(splitVars[2]);
					float cubeZ = Float.parseFloat(splitVars[3]);
					float cubeSize = Float.parseFloat(splitVars[4]);
					if (splitVars.length <= 5)
					{
						tempLevel.addRenderable(new Cube(new Vector3f(cubeX, cubeY, cubeZ), cubeSize, true));
					}
					else
					{
						if (!currentSpriteSheetName.isEmpty() && SpriteSheets.getSpriteSheet(currentSpriteSheetName.toLowerCase()).getSprite(splitVars[5]) != null)
						{
							//tempLevel.addRenderable(new Cube(new Vector3f(cubeX * cubeSize * cubePadding, cubeY * cubeSize * cubePadding, cubeZ * cubeSize * cubePadding), cubeSize, SpriteSheets.getSpriteSheet(currentSpriteSheetName).getSprite(splitVars[5]), true));
							PrimitiveCoordinates coordinates = CreatePrimitiveFactory.makeCube(new Vector3f(cubeX, cubeY, cubeZ), new Vector3f(cubeSize, cubeSize, cubeSize), SpriteSheets.getSpriteSheet(currentSpriteSheetName).getSprite(splitVars[5]));
							tempLevel.levelGeoData.add(coordinates);
							//VBOBatcher.addVerticesWithTextureCoordinatesAndNormals(coordinates.vertices, coordinates.textureCoordinates, coordinates.normals);
						}
						else
						{
							System.err.println("Error! Sprite cannot be found!");
						}
					}
				}
				else if (currentLine.startsWith("wall"))
				{
					String[] splitVars = currentLine.split(" ");
					int wallX = Integer.parseInt(splitVars[1]);
					int wallY = Integer.parseInt(splitVars[2]);
					int wallZ = Integer.parseInt(splitVars[3]);
					int wallLength = Integer.parseInt(splitVars[4]);
					int wallWidth = Integer.parseInt(splitVars[5]);
					int wallHeight = Integer.parseInt(splitVars[6]);
					float wallCubeSize = Float.parseFloat(splitVars[7]);
					Sprite wallCubeSprite = null;
					if (splitVars.length > 8)
						 wallCubeSprite = SpriteSheets.getSpriteSheet(currentSpriteSheetName).getSprite(splitVars[8]);
					tempLevel = makeWall(tempLevel, new Vector3f(wallX, wallY, wallZ), wallLength, wallWidth, wallHeight, wallCubeSize, cubePadding, wallCubeSprite);
				}
				else if (currentLine.startsWith("set_level_bounds"))
				{
					String[] splitVars = currentLine.split(" ");
					int boundsWidth = Integer.parseInt(splitVars[1]);
					int boundsHeight = Integer.parseInt(splitVars[2]);
					int boundsLength = Integer.parseInt(splitVars[3]);
					float cubeSize = Float.parseFloat(splitVars[4]);
					Sprite cubeSprite = SpriteSheets.getSpriteSheet(currentSpriteSheetName).getSprite(splitVars[5]);
					tempLevel = addBounds(tempLevel, boundsWidth, boundsHeight, boundsLength, cubeSize, cubePadding, cubeSprite);
					tempLevel.setSpawnLocation(new Vector3f(boundsWidth / 2, -boundsHeight, boundsLength / 2));
				}
			}
		}
		levelReader.close();
		return tempLevel;
	}

	protected static Level makeWall(Level level, Vector3f wallStart, int wallLength, int wallWidth, int wallHeight, float wallCubeSize, float cubePadding, Sprite cubeSprite)
	{
		ArrayList<AbstractRenderable> wallPieces = new ArrayList<AbstractRenderable>();
		ArrayList<AxisAlignedBoundingBox> wallColliders = new ArrayList<AxisAlignedBoundingBox>();
		for (int i = (int) wallStart.getX(); i < (int) wallStart.getX() + wallWidth; i++)
		{
			for (int j = (int) wallStart.getY(); j < (int) wallStart.getY() + wallHeight; j++)
			{
				for (int k = (int) wallStart.getZ(); k < (int) wallStart.getZ() + wallLength; k++)
				{
					Vector3f cubePosition = new Vector3f(i * wallCubeSize * cubePadding, j * wallCubeSize * cubePadding, k * wallCubeSize * cubePadding);
					//Cube cube = new Cube(cubePosition, wallCubeSize, cubeSprite, true);
					PrimitiveCoordinates coordinates = CreatePrimitiveFactory.makeCube(cubePosition, new Vector3f(wallCubeSize, wallCubeSize, wallCubeSize), cubeSprite);
					level.levelGeoData.add(coordinates);
					//VBOBatcher.addVerticesWithTextureCoordinatesAndNormals(coordinates.vertices, coordinates.textureCoordinates, coordinates.normals);
					AxisAlignedBoundingBox boundingBox = new AxisAlignedBoundingBox(null, "collider", cubePosition, wallCubeSize, wallCubeSize, wallCubeSize + 4.0f);
					//wallPieces.add(cube);
					wallColliders.add(boundingBox);
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
		level.addRenderables(returnableArray);
		AxisAlignedBoundingBox[] returnableColliders = new AxisAlignedBoundingBox[wallColliders.size()];
		int j = 0;
		for (AxisAlignedBoundingBox collider : wallColliders)
		{
			returnableColliders[j] = collider;
			j++;
		}
		level.addColliders(returnableColliders);
		return level;
	}

	protected static Level addBounds(Level level, int boundsWidth, int boundsHeight, int boundsLength, float cubeSize, float cubePadding, Sprite cubeSprite)
	{
		ArrayList<AbstractRenderable> boundsPieces = new ArrayList<AbstractRenderable>();
		ArrayList<AxisAlignedBoundingBox> boundsColliders = new ArrayList<AxisAlignedBoundingBox>();
		for (int x = -boundsWidth; x < boundsWidth + 1; x++)
		{
			for (int y = -boundsHeight; y < boundsHeight + 1; y++)
			{
				for (int z = -boundsLength; z < boundsHeight + 1; z++)
				{
					if ((x == -boundsWidth || x == boundsWidth) ||
							(y == -boundsHeight || y == boundsHeight) ||
							(z == -boundsLength || z == boundsLength))
					{
						Vector3f cubePosition = new Vector3f(x * cubeSize * cubePadding, y * cubeSize * cubePadding, z * cubeSize * cubePadding);
						//Cube cube = new Cube(cubePosition, cubeSize, cubeSprite, true);
						PrimitiveCoordinates coordinates = CreatePrimitiveFactory.makeCube(cubePosition, new Vector3f(cubeSize, cubeSize, cubeSize), cubeSprite);
						level.levelGeoData.add(coordinates);
						//VBOBatcher.addVerticesWithTextureCoordinatesAndNormals(coordinates.vertices, coordinates.textureCoordinates, coordinates.normals);
						AxisAlignedBoundingBox collider = new AxisAlignedBoundingBox(null, "collider", cubePosition, cubeSize, cubeSize, cubeSize);
						boundsColliders.add(collider);
						//boundsPieces.add(cube);
					}
				}
			}
		}
		AbstractRenderable[] returnableRenderers = new AbstractRenderable[boundsPieces.size()];
		int i = 0;
		for (AbstractRenderable renderable : boundsPieces)
		{
			returnableRenderers[i] = renderable;
			i++;
		}
		AxisAlignedBoundingBox[] returnableColliders = new AxisAlignedBoundingBox[boundsColliders.size()];
		int j = 0;
		for (AxisAlignedBoundingBox collider : boundsColliders)
		{
			returnableColliders[j] = collider;
			j++;
		}
		level.addRenderables(returnableRenderers);
		level.addColliders(returnableColliders);
		return level;
	}
}