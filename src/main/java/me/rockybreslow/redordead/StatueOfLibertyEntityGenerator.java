package me.rockybreslow.redordead;

import me.rockybreslow.redordead.entity.Entity;
import me.rockybreslow.redordead.entity.StatueOfLibertyEntity;
import processing.core.PApplet;

public class StatueOfLibertyEntityGenerator {
    private PApplet applet;
    private boolean generateStatueOfLibertyEntities;

    public StatueOfLibertyEntityGenerator(PApplet applet) {
        this.applet = applet;
        generateStatueOfLibertyEntities = false;
    }

    public void generateStatueOfLibertyEntities() {
        generateStatueOfLibertyEntities = true;
    }

    public StatueOfLibertyEntity[] getStatueOfLibertyEntities() {
        if(!generateStatueOfLibertyEntities) {
            return new StatueOfLibertyEntity[0];
        }

        StatueOfLibertyEntity[] statueOfLibertyEntities = new StatueOfLibertyEntity[(int) (Math.random() * 8) + 2];

        for(int i = 0; i < statueOfLibertyEntities.length; i++) {
            statueOfLibertyEntities[i] = new StatueOfLibertyEntity((float) Math.random() * applet.width);
        }

        generateStatueOfLibertyEntities = false;

        return statueOfLibertyEntities;
    }


}
