package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Hitbox {
    private Vector2 position;
    private Vector2 size;
    private boolean hit = false;

    public Hitbox(float centerX, float centerY, float width, float height) {
        this.position = new Vector2(centerX - width / 2, centerY - height / 2);
        this.size = new Vector2(width, height);
    }

    public boolean collide(Hitbox other) {
        Vector2 otherPos = other.getPosition();
        Vector2 otherSize = other.getSize();

        return otherPos.x < this.position.x + this.size.x && otherPos.x + otherSize.x > this.position.x &&
               otherPos.y < this.position.y + this.size.y && otherPos.y + otherSize.y > this.position.y;
    }

    public boolean collide(Vector2 point) {
        if (point.x < this.position.x + this.size.x && point.x > this.position.x &&
               point.y < this.position.y + this.size.y && point.y > this.position.y) {
                   hit = true;
                   return true;
               }
        return false;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setPosition(float x, float y) {
        this.position.x = x - size.x / 2;
        this.position.y = y - size.y / 2;
    }

    public void drawHitbox(ShapeRenderer shape, Camera camera) {
        shape.setProjectionMatrix(camera.combined);
        shape.begin(ShapeType.Line);
		if (hit) {
            shape.setColor(Color.GREEN);
            hit = false;
        } else {
            shape.setColor(Color.RED);
        }
		shape.rect(position.x, position.y, size.x, size.y);
		shape.end();
    }
}