package com.tinfoilboy.warmupweekend;

import com.tinfoilboy.warmupweekend.gameplay.Camera;
import com.tinfoilboy.warmupweekend.gameplay.InputHandler;
import com.tinfoilboy.warmupweekend.graphics.Cube;
import com.tinfoilboy.warmupweekend.graphics.sprites.Sprite;
import com.tinfoilboy.warmupweekend.graphics.sprites.SpriteSheet;
import com.tinfoilboy.warmupweekend.levels.Level;
import com.tinfoilboy.warmupweekend.levels.LevelParser;
import com.tinfoilboy.warmupweekend.util.SpriteSheets;
import com.tinfoilboy.warmupweekend.util.TextureCoordinate;
import com.tinfoilboy.warmupweekend.util.Vertex;
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

	private float angle = 0.0f;

	private static volatile boolean running = true;

	private static WarmupWeekend instance = null;

	private Level currentLevel = null;

	public Camera camera = new Camera(60.0f, 0.1f, 1000.0f);

	public InputHandler inputHandler;

	public WarmupWeekend()
	{
		instance = this;
		this.initDisplay();
		this.camera.create();
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

	private void init()
	{
		try
		{
			this.inputHandler = new InputHandler();
			SpriteSheets.addSpriteSheet("scenery", new SpriteSheet(256.0f, 256.0f, new File("assets/SpriteSheets/scenery.png"))
					.addSprite("grass", new Vector2f(1.0f, 0.0f), 64.0f, 64.0f)
					.addSprite("brick", new Vector2f(0.0f, 0.0f), 64.0f, 64.0f));
			this.currentLevel = LevelParser.parseLevel(new File("assets/levels/test.level"));
			this.camera.setPosition(this.currentLevel.spawnLocation);
			this.camera.update();
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

		/*Vector3f sizes = new Vector3f(10.0f, 10.0f, 10.0f);

		Vertex[] vertices = new Vertex[] {
				// Front Face 1
				new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
				new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ()),
				new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
				// Front Face 2
				new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
				new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
				new Vertex(-sizes.getX(), sizes.getY(), -sizes.getZ()),
				// Back Face 1
				new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()),
				new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ()),
				new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
				// Back Face 2
				new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()),
				new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
				new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()),
				// Left Face 1
				new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ()),
				new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
				new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
				// Left Face 2
				new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
				new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ()),
				new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ()),
				// Right Face 1
				new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
				new Vertex(-sizes.getX(), sizes.getY(), -sizes.getZ()),
				new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()),
				// Right Face 2
				new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()),
				new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
				new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()),
				// Top Face 1
				new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
				new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()),
				new Vertex(-sizes.getX(), sizes.getY(), -sizes.getZ()),
				// Top Face 2
				new Vertex(sizes.getX(), sizes.getY(), -sizes.getZ()),
				new Vertex(-sizes.getX(), sizes.getY(), sizes.getZ()),
				new Vertex(sizes.getX(), sizes.getY(), sizes.getZ()),
				// Bottom Face 1
				new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ()),
				new Vertex(-sizes.getX(), -sizes.getY(), sizes.getZ()),
				new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
				// Bottom Face 2
				new Vertex(sizes.getX(), -sizes.getY(), -sizes.getZ()),
				new Vertex(-sizes.getX(), -sizes.getY(), -sizes.getZ()),
				new Vertex(sizes.getX(), -sizes.getY(), sizes.getZ())
		};

		Sprite tempSprite = SpriteSheets.getSpriteSheet("scenery").getSprite("brick");

		TextureCoordinate[] coordinates = new TextureCoordinate[] {
			// Front Face 1
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			// Front Face 2
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV()),
			// Back Face 1
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			// Back Face 2
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV()),
			// Right Face 1
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			// Right Face 2
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV1()),
			// Right Face 1
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			// Right Face 2
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV1()),
			// Top Face 1
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV()),
			// Top Face 2
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV1()),
			// Bottom Face 1
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			// Bottom Face 2
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU1(), tempSprite.getV1()),
			new TextureCoordinate(tempSprite.getU(), tempSprite.getV())
		};

		angle += 0.01f;

		glBindTexture(GL_TEXTURE_2D, SpriteSheets.getSpriteSheet("scenery").getSpriteSheetTexture().TEXTURE_ID);

		// Cube Test Code
		glTranslatef(-1.5f, 1.0f, -40.0f);
		glRotatef(45.0f, 0.0f, -1.0f, 0.0f);
		glBegin(GL_TRIANGLES);
		for (int i = 0; i < vertices.length; i++)
		{
			Vertex vertex = vertices[i];
			TextureCoordinate coordinate = coordinates[i];
			glTexCoord2f(coordinate.getU(), coordinate.getV());
			glVertex3f(vertex.getX(), vertex.getY(), vertex.getZ());
		}
		glEnd();*/

		currentLevel.drawLevel();
	}

	private void update()
	{
		this.camera.update();

		if (Display.isCloseRequested())
		{
			running = false;
		}

		if (Display.wasResized())
		{
			this.camera.resize();
		}
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