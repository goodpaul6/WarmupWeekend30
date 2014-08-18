package com.tinfoilboy.warmupweekend.model;

import com.tinfoilboy.warmupweekend.model.helpers.Face;
import com.tinfoilboy.warmupweekend.util.Vertex;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ModelOBJLoader
{
	public static Model loadModel(File objFile) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(objFile));
		Model tempModel = new Model();
		String currentLine;
		while ((currentLine = reader.readLine()) != null)
		{
			// This is a vertex!
			if (currentLine.startsWith("v "))
			{
				String[] vertexCoords = currentLine.split(" ");
				float x = Float.valueOf(vertexCoords[1]);
				float y = Float.valueOf(vertexCoords[2]);
				float z = Float.valueOf(vertexCoords[3]);
				tempModel.vertices.add(new Vertex(x, y, z));
			}

			// This is a vertex normal!
			if (currentLine.startsWith("vn "))
			{
				String[] normalCoords = currentLine.split(" ");
				float x = Float.valueOf(normalCoords[1]);
				float y = Float.valueOf(normalCoords[2]);
				float z = Float.valueOf(normalCoords[3]);
				tempModel.normals.add(new Vertex(x, y, z));
			}

			// This is a face!
			if (currentLine.startsWith("f "))
			{
				String[] faceCoords = currentLine.split(" ");
				float x = Float.valueOf(faceCoords[1].split("/")[0]);
				float y = Float.valueOf(faceCoords[2].split("/")[0]);
				float z = Float.valueOf(faceCoords[3].split("/")[0]);
				float xNorm = Float.valueOf(faceCoords[1].split("/")[2]);
				float yNorm = Float.valueOf(faceCoords[2].split("/")[2]);
				float zNorm = Float.valueOf(faceCoords[3].split("/")[2]);
				tempModel.faces.add(new Face(new Vertex(x, y, z), new Vertex(xNorm, yNorm, zNorm)));
			}
		}
		reader.close();
		return tempModel;
	}
}