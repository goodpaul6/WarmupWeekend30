package com.tinfoilboy.warmupweekend.graphics;

import com.tinfoilboy.warmupweekend.model.PositionableModel;
import com.tinfoilboy.warmupweekend.util.Culling;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;

import static com.tinfoilboy.warmupweekend.Static.RENDER_DISTANCE;
import static org.lwjgl.opengl.GL11.*;

public class ModelRenderer
{
	public static ArrayList<PositionableModel> models = new ArrayList<PositionableModel>();

	public static void addModel(PositionableModel model)
	{
		models.add(model);
	}

	public static void drawModels()
	{
		for (PositionableModel positionableModel : models)
		{
			if (Culling.shouldShow(positionableModel.position, new Vector3f(8.0f, 8.0f, 8.0f), RENDER_DISTANCE))
			{
				glPushMatrix();
				glTranslatef(positionableModel.position.getX(), positionableModel.position.getY(), positionableModel.position.getZ());
				glRotatef(positionableModel.rotation.getX(), 0.0f, 1.0f, 0.0f);
				glRotatef(positionableModel.rotation.getY(), 1.0f, 0.0f, 0.0f);
				glRotatef(positionableModel.rotation.getZ(), 0.0f, 0.0f, 1.0f);
				positionableModel.model.render();
				glPopMatrix();
			}
		}
	}

	public static void dispose()
	{
		models.clear();
	}
}