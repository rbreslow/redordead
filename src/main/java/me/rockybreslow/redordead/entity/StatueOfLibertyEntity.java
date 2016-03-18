package me.rockybreslow.redordead.entity;

import me.rockybreslow.redordead.util.ImageLoader;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class StatueOfLibertyEntity extends Entity {
    private long time;
    private PImage image;
    private boolean drawStatue;

    public StatueOfLibertyEntity(float x) {
        super(x, 0);

        drawStatue = false;
    }

    public boolean collides(PhysicsEntity entity) {
        return drawStatue && entity.position.x < position.x + image.width && entity.position.x + entity.width > position.x && entity.position.y < position.y + image.height && entity.height + entity.position.y > position.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRegistration(PApplet applet) {
        super.onRegistration(applet);

        time = System.currentTimeMillis();
        image = ImageLoader.getInstance(applet).get("materials/liberty.png");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFrame() {
        PApplet applet = getPApplet();

        if(drawStatue) {
            applet.image(image, position.x, position.y);
        } else {
            // Draw red text
            applet.fill(255, 0, 0, 255);
            applet.textSize(80);
            applet.text("!", position.x, applet.height);

            // Draw stroke around text
            applet.fill(255, 0, 0, 255);
            applet.noFill();
            applet.rect(position.x, applet.height, applet.textWidth("!"), applet.textAscent());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpdate() {
        PApplet applet = getPApplet();

        // After two seconds draw the statue
        if(System.currentTimeMillis() - time > 2000 && !drawStatue) {
            drawStatue = true;
            position.y = applet.height;
            time = System.currentTimeMillis();
        }

        if(position.y >= applet.height - image.height && drawStatue) {
            position.add(new PVector(0, -20f).mult(.9f));
        }
    }
}
