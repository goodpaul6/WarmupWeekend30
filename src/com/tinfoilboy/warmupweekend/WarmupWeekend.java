package com.tinfoilboy.warmupweekend;

import com.tinfoilboy.warmupweekend.graphics.Cube;
import com.tinfoilboy.warmupweekend.graphics.sprites.SpriteSheet;
import com.tinfoilboy.warmupweekend.levels.Level;
import com.tinfoilboy.warmupweekend.levels.LevelParser;
import com.tinfoilboy.warmupweekend.util.SpriteSheets;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.File;
import java.io.IOException;

import static com.tinfoilboy.warmupweekend.Static.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class WarmupWeekend
{
	private final String TITLE = "Ludum Dare Warmup Weekend";

	private int width = 640, height = 480;

	private volatile boolean running = true;

	private static WarmupWeekend instance = null;

	private Cube cube;

	private SpriteSheet spriteSheet;

	private Level currentLevel = null;

	public WarmupWeekend()
	{
		instance = this;
		this.initDisplay();
		this.initGL();
		this.init();
		this.gameLoop();
	}

	private void initDisplay()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(width, height));

			Display.setTitle(TITLE);

			Display.setResizable(true);

			Display.create(new PixelFormat(8, 8, 8, 0));
		} catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(DISPLAY_INIT_ERROR);
		}
	}

	private void initGL()
	{
		glMatrixMode(GL_PROJECTION);
		glViewport(0, 0, width, height);
		gluPerspective(45.0f, (float) width / (float) height, 0.1f, 1000.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(0.0f, 0.0f, -150.0f);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
	}

	private void init()
	{
		try
		{
			SpriteSheets.addSpriteSheet("scenery", new SpriteSheet(256.0f, 256.0f, new File("assets/SpriteSheets/scenery.png"))
					.addSprite("grass", new Vector2f(1.0f, 0.0f), 64.0f, 64.0f)
					.addSprite("brick", new Vector2f(0.0f, 0.0f), 64.0f, 64.0f));

			this.currentLevel = LevelParser.parseLevel(new File("assets/levels/test.level"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void gameLoop()
	{
		while (running)
		{
			this.render();

			this.update();

			Display.update();
		}

		currentLevel.disposeLevel();

		Display.destroy();

		System.exit(0);
	}

	private void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		currentLevel.drawLevel();
	}

	private void update()
	{
		if (Display.isCloseRequested())
		{
			running = false;
		}

		if (Display.wasResized())
		{
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glViewport(0, 0, Display.getWidth(), Display.getHeight());
			gluPerspective(45.0f, (float) Display.getWidth() / (float) Display.getHeight(), 0.1f, 1000.0f);
			glMatrixMode(GL_MODELVIEW);
		}
	}

	public static WarmupWeekend getInstance()
	{
		return instance;
	}

	public static void main(String[] args)
	{
		new WarmupWeekend();
	}
}