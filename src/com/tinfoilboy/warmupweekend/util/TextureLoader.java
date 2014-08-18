package com.tinfoilboy.warmupweekend.util;

import com.google.common.io.Files;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.glu.MipMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader
{
	public static Texture loadImageData(File imageFile, int clamping, int filter)
	{
		BufferedImage image;

		int bitsPerPixel;

		if (Files.getFileExtension(imageFile.getPath()).equalsIgnoreCase("PNG") || Files.getFileExtension(imageFile.getPath()).equalsIgnoreCase("BMP") || Files.getFileExtension(imageFile.getPath()).equalsIgnoreCase("GIF"))
			bitsPerPixel = 4;
		else
			bitsPerPixel = 3;

		try
		{
			InputStream imageInputStream = new FileInputStream(imageFile);

			image = ImageIO.read(imageInputStream);

			int[] pixelData = new int[image.getWidth() * image.getHeight()];

			image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixelData, 0, image.getWidth());

			ByteBuffer imageData = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * bitsPerPixel);

			for(int y = 0; y < image.getHeight(); y++)
			{
				for(int x = 0; x < image.getWidth(); x++)
				{
					int currentPixel = pixelData[y * image.getWidth() + x];
					imageData.put((byte) ((currentPixel >> 16) & 0xFF));
					imageData.put((byte) ((currentPixel >> 8) & 0xFF));
					imageData.put((byte) (currentPixel & 0xFF));
					imageData.put((byte) ((currentPixel >> 24) & 0xFF));
				}
			}

			imageData.flip();

			int textureID = glGenTextures();

			glBindTexture(GL_TEXTURE_2D, textureID);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, clamping);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, clamping);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);

			glTexParameteri(GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, glGetInteger(EXTTextureFilterAnisotropic.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT));

			if(bitsPerPixel == 4)
			{
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getWidth(), 0, GL_RGBA, GL_UNSIGNED_BYTE, imageData);
			}
			else
			{
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB8, image.getWidth(), image.getWidth(), 0, GL_RGB, GL_UNSIGNED_BYTE, imageData);
			}

			imageData.clear();

			return new Texture(textureID, image.getWidth(), image.getHeight());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();

			return null;
		}
		catch (IOException e)
		{
			e.printStackTrace();

			return null;
		}
	}

	public static Texture loadImageData(File imageFile, int clamping, int filter, int mipmapFilter)
	{
		BufferedImage image;

		int bitsPerPixel = 0;

		if (Files.getFileExtension(imageFile.getPath()).equalsIgnoreCase("PNG") || Files.getFileExtension(imageFile.getPath()).equalsIgnoreCase("BMP") || Files.getFileExtension(imageFile.getPath()).equalsIgnoreCase("GIF"))
			bitsPerPixel = 4;
		else
			bitsPerPixel = 3;

		try
		{
			InputStream imageInputStream = new FileInputStream(imageFile);

			image = ImageIO.read(imageInputStream);

			int[] pixelData = new int[image.getWidth() * image.getHeight()];

			image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixelData, 0, image.getWidth());

			ByteBuffer imageData = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * bitsPerPixel);

			for(int y = 0; y < image.getHeight(); y++)
			{
				for(int x = 0; x < image.getWidth(); x++)
				{
					int currentPixel = pixelData[y * image.getWidth() + x];
					imageData.put((byte) ((currentPixel >> 16) & 0xFF));
					imageData.put((byte) ((currentPixel >> 8) & 0xFF));
					imageData.put((byte) (currentPixel & 0xFF));
					imageData.put((byte) ((currentPixel >> 24) & 0xFF));
				}
			}

			imageData.flip();

			int textureID = glGenTextures();

			glBindTexture(GL_TEXTURE_2D, textureID);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, clamping);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, clamping);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, mipmapFilter);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);

			glTexParameteri(GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, glGetInteger(EXTTextureFilterAnisotropic.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT));

			if(bitsPerPixel == 4)
			{
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getWidth(), 0, GL_RGBA, GL_UNSIGNED_BYTE, imageData);
				//GL30.glGenerateMipmap(GL_TEXTURE_2D);
				MipMap.gluBuild2DMipmaps(GL_TEXTURE_2D, GL_RGBA, image.getWidth(), image.getHeight(), GL_RGBA, GL_UNSIGNED_BYTE, imageData);
			}
			else
			{
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB8, image.getWidth(), image.getWidth(), 0, GL_RGB, GL_UNSIGNED_BYTE, imageData);
				//GL30.glGenerateMipmap(GL_TEXTURE_2D);
				MipMap.gluBuild2DMipmaps(GL_TEXTURE_2D, GL_RGB, image.getWidth(), image.getHeight(), GL_RGB, GL_UNSIGNED_BYTE, imageData);
			}

			imageData.clear();

			return new Texture(textureID, image.getWidth(), image.getHeight());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();

			return null;
		}
		catch (IOException e)
		{
			e.printStackTrace();

			return null;
		}
	}
}