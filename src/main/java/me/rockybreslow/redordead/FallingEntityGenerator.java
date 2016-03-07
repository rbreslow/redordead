package me.rockybreslow.redordead;

import me.rockybreslow.redordead.entity.FallingEntity;
import me.rockybreslow.redordead.manager.EntityManager;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class FallingEntityGenerator {
    private PApplet parent;
    private EntityManager<FallingEntity> entityManager = new EntityManager<>();
    private List<FallingEntity> fallingEntityList = new ArrayList<>();
    public int frequency;

    public FallingEntityGenerator(PApplet parent, int frequency) {
        this.parent = parent;
        this.frequency = frequency;
    }

    public List<FallingEntity> generate() {
        List<FallingEntity> product = new ArrayList<>();

        for(int i = 0; i < frequency; i++) {
            FallingEntity fallingEntity = new FallingEntity(parent, "materials/cigar_128.png");
            product.add(fallingEntity);
            fallingEntityList.add(fallingEntity);
            entityManager.register(fallingEntity);
        }

        return product;
    }

    public void update() {
        for(FallingEntity entity : fallingEntityList) {
            if(entity.position.y > parent.height - entity.image.height) {
                entityManager.destroy(entity);
            }
        }

        entityManager.update();
    }
}
