package com.tinfoilboy.warmupweekend.gameplay;

import com.tinfoilboy.warmupweekend.WarmupWeekend;
import org.lwjgl.input.Keyboard;

import java.security.Key;

import static org.lwjgl.input.Keyboard.*;

public class InputHandler implements Runnable
{
	private Thread inputThread = null;

	public InputHandler()
	{
		this.start();
	}

	public synchronized void start()
	{
		this.inputThread = new Thread(this, "InputThread");
		this.inputThread.start();
	}

	@Override
	public void run()
	{
		while (WarmupWeekend.isRunning())
		{
			this.checkInput();
		}

		this.stop();
	}

	private void checkInput()
	{
		while (next())
		{
			switch (getEventKey())
			{
				case KEY_ESCAPE:
					WarmupWeekend.stop();
					break;
				case KEY_W:
					WarmupWeekend.getInstance().camera.movingForward = getEventKeyState();
					break;
				case KEY_S:
					WarmupWeekend.getInstance().camera.movingBackward = getEventKeyState();
					break;
				case KEY_A:
					WarmupWeekend.getInstance().camera.movingLeft = getEventKeyState();
					break;
				case KEY_D:
					WarmupWeekend.getInstance().camera.movingRight = getEventKeyState();
					break;
				case KEY_LSHIFT:
					WarmupWeekend.getInstance().camera.sprinting = getEventKeyState();
					break;
			}
		}
	}

	public synchronized void stop()
	{
		try
		{
			this.inputThread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}