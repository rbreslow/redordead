package me.rockybreslow.redordead.util;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageLoader {
    private static List<ImageLoader> imageLoaders = new ArrayList<>();

    private PApplet applet;
    private Map<String, PImage> images = new HashMap<>();

    public ImageLoader(PApplet applet) {
        this.applet = applet;
    }

    public boolean contains(String filename) {
        return images.containsKey(filename);
    }

    public PImage get(String filename, String extension, boolean shouldLoad) {
        if (!contains(filename)) {
            if (shouldLoad) {
                return load(filename, extension);
            }

            return null;
        }

        return images.get(filename);
    }

    public PImage get(String filename, boolean shouldLoad) {
        return get(filename, null, shouldLoad);
    }

    public PImage get(String filename) {
        return get(filename, true);
    }

    public PImage load(String filename, String extension) {
        PImage image = applet.loadImage(filename, extension);

        images.put(filename, image);

        return image;
    }

    public PImage load(String filename) {
        return load(filename, null);
    }

    public boolean unload(String filename) {
        if (!contains(filename)) {
            return false;
        }

        images.remove(filename);

        return true;
    }

    public static ImageLoader getInstance(PApplet applet) {
        for (ImageLoader loader : imageLoaders) {
            if (loader.applet == applet) {
                return loader;
            }
        }

        ImageLoader imageLoader = new ImageLoader(applet);

        imageLoaders.add(imageLoader);

        return imageLoader;
    }
}