package me.rockybreslow.redordead;

import processing.core.*;
import java.util.logging.Logger;

/**
 * Main class for Red or Dead.
 *
 * @author Rocky Breslow
 */

public class Main extends PApplet {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        PApplet.main(Main.class.getName());
    }

    public void setup() {
        background(0);
    }

    public void settings() {
        size(200,200);
    }

    public void draw() {
        stroke(255);
        if (mousePressed) {
            line(mouseX,mouseY,pmouseX,pmouseY);
        }
    }
}
