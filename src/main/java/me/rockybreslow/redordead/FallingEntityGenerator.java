package me.rockybreslow.redordead;

import me.rockybreslow.redordead.entity.FallingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rocky Breslow on 3/17/2016.
 */
public class FallingEntityGenerator {
    private int frequency;
    private int generationTimer;

    public FallingEntityGenerator(int frequency) {
        this.frequency = frequency;
        this.generationTimer = 0;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public List<FallingEntity> generateEntities() {
        generationTimer--;

        if(generationTimer > 0) {
            return new ArrayList<>();
        }

        List<FallingEntity> ret = new ArrayList<>();

        generationTimer = frequency - (int) (Math.random() * 10);

        for(int i = 0; i < 4; i++) {
            FallingEntity fallingEntity;

            switch((int) (Math.random() * 10) + 1) {
                case 1:
                    fallingEntity = new FallingEntity(128, 21, "materials/cigar.png", 1000);
                    break;
                case 2:
                    fallingEntity = new FallingEntity(128, 21, "materials/rifle.png", 800);
                    break;
                case 3:
                    fallingEntity = new FallingEntity(104, 128, "materials/mao.png", 800);
                    break;
                case 4:
                    fallingEntity = new FallingEntity(128, 60, "materials/peso.png", 800);
                    break;
                case 5:
                    fallingEntity = new FallingEntity(108, 128, "materials/man.png", 1000);
                    break;
                // Western Ideals
                case 6:
                    fallingEntity = new FallingEntity(95, 128, "materials/uncle_sam.png", -1000);
                    break;
                case 7:
                    fallingEntity = new FallingEntity(128, 114, "materials/west/mcdonald.png", -1000);
                    break;
                case 8:
                    fallingEntity = new FallingEntity(128, 128, "materials/west/starbucks.png", -1000);
                    break;
                case 9:
                    fallingEntity = new FallingEntity(128, 128, "materials/west/target.png", -800);
                    break;
                case 10:
                    fallingEntity = new FallingEntity(128, 134, "materials/west/walmart.png", -800);
                    break;
                default:
                    fallingEntity = null;
                    break;
            }

            ret.add(fallingEntity);
        }

        if((int) (Math.random() * 10) == (int) (Math.random() * 10)) {
            FallingEntity fallingEntityBoss = new FallingEntity(345, 128, "materials/west/pentagon.png", -1500);
            ret.add(fallingEntityBoss);
        }

        return ret;
    }
}
