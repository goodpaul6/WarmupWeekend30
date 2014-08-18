package com.tinfoilboy.warmupweekend.gameplay;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

public class SoundManager
{
	public static Audio footstep_left;

	public static Audio footstep_right;

	public static void init() throws IOException
	{
		footstep_left = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/sounds/player/footstep_1.wav"));

		footstep_right = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/sounds/player/footstep_2.wav"));
	}
}