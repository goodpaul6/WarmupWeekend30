package com.tinfoilboy.warmupweekend;

import com.tinfoilboy.warmupweekend.gameplay.Camera;
import com.tinfoilboy.warmupweekend.gameplay.InputHandler;
import com.tinfoilboy.warmupweekend.gameplay.SoundManager;
import com.tinfoilboy.warmupweekend.graphics.Quad;
import com.tinfoilboy.warmupweekend.graphics.sprites.SpriteSheet;
import com.tinfoilboy.warmupweekend.levels.Level;
import com.tinfoilboy.warmupweekend.levels.LevelParser;
import com.tinfoilboy.warmupweekend.model.Model;
import com.tinfoilboy.warmupweekend.model.ModelOBJLoader;
import com.tinfoilboy.warmupweekend.util.SpriteSheets;
import com.tinfoilboy.warmupweekend.util.Vertex;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static com.tinfoilboy.warmupweekend.Static.*;
import static org.lwjgl.opengl.GL11.*;

public class WarmupWeekend
{
	private final String TITLE = "Ludum Dare Warmup Weekend";

	private int width = 640, height = 480;

	public int fps = 0;

	public long lastFPSCalc = 0L, lastFrame = 0L;

	public float lastFrameTime = 0.0f, frameLength = 0.0f;

	private float angle = 0.0f;

	private static volatile boolean running = true;

	private static WarmupWeekend instance = null;

	public Level currentLevel = null;

	public Camera camera = new Camera(60.0f, 0.1f, 1000.0f);

	public static Random random = new Random();

	public InputHandler inputHandler;

	public Model model = null;

	public WarmupWeekend()
	{
		instance = this;
		this.initDisplay();
		this.camera.create();
		getDelta();
		lastFPSCalc = getTime();
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

		Mouse.setGrabbed(true);
	}

	private void init()
	{
		try
		{
			SoundManager.init();
			this.inputHandler = new InputHandler();
			SpriteSheets.addSpriteSheet("scenery", new SpriteSheet(512.0f, 512.0f, new File("assets/SpriteSheets/scenery.png")).addSprite("brick", new Vector2f(0.0f, 0.0f), 128.0f, 128.0f).addSprite("lava", new Vector2f(1.0f, 0.0f), 128.0f, 128.0f));
			this.currentLevel = LevelParser.parseLevel(new File("assets/levels/test.level"));
			this.camera.setPosition(this.currentLevel.spawnLocation);
			this.camera.update();
			this.model = ModelOBJLoader.loadModel(new File("assets/models/zombie.obj"));
			this.model.init();
			this.model.setPosition(new Vector3f(camera.getPosition().getX(), -380.0f, camera.getPosition().getZ() - 60.0f));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void gameLoop()
	{
		while (running)
		{
			this.update();

			this.render();

			Display.update();
		}

		model.dispose();

		currentLevel.disposeLevel();

		Display.destroy();

		AL.destroy();

		System.exit(0);
	}

	private void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glDisable(GL_TEXTURE_2D);
		model.render();
		glEnable(GL_TEXTURE_2D);

		currentLevel.drawLevel();
	}

	private void update()
	{
		this.camera.update();

		this.model.update();

		updateFPS();

		this.currentLevel.updateLevel();

		if (Display.isCloseRequested())
		{
			running = false;
		}

		if (Display.wasResized())
		{
			this.camera.resize();
		}
	}

	private int getDelta()
	{
		long time = this.getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	public long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public void updateFPS()
	{
		frameLength = (Sys.getTime() - lastFrameTime) / 1000.0f;
		lastFrameTime = Sys.getTime();
		if (this.getTime() - lastFPSCalc > 1000)
		{
			fps = 0;
			lastFPSCalc += 1000;
		}
		fps++;
	}

	public static void stop()
	{
		running = false;
	}

	public static boolean isRunning()
	{
		return running;
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