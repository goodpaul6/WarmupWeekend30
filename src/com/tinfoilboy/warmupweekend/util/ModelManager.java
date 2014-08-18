package com.tinfoilboy.warmupweekend.util;

import com.tinfoilboy.warmupweekend.WarmupWeekend;
import com.tinfoilboy.warmupweekend.model.Model;
import java.io.File;
import java.io.IOException;
import static com.tinfoilboy.warmupweekend.model.ModelOBJLoader.loadModel;

public class ModelManager
{
	public static Model ZOMBIE = null;

	static
	{
		try
		{
			ZOMBIE = loadModel(new File("assets/models/zombie.obj"));
			ZOMBIE.init();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			WarmupWeekend.getInstance().stop();
		}
	}

	public static void dispose()
	{
		ZOMBIE.dispose();
	}
}