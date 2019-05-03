
package com.mygdx.game.entity.enemy.script;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.enemy.Enemy;

public class MoveCommand implements ActionCommand {
    private boolean finished;
    private Vector2 destination;
    private float speed;

    public MoveCommand(Vector2 destination, float speed) {
        this.finished = false;
        this.destination = destination;
        this.speed = speed;
    }

    public MoveCommand(float destX, float destY, float speed) {
        this.finished = false;
        this.destination = new Vector2(destX, destY);
        this.speed = speed;
    }

	@Override
	public void step(Enemy enemy) {
        float squareDistance = destination.cpy().sub(enemy.getPosition()).len2();

        if (squareDistance < Math.pow(speed, 2)) {
            enemy.setPosition(destination);
            finished = true;
        } else {
            int angle = (int) destination.cpy().sub(enemy.getPosition()).angle(new Vector2(0, 1));

            float x = (float) (enemy.getPosition().x + speed * Math.sin(Math.toRadians(angle)));
            float y = (float) (enemy.getPosition().y + speed * Math.cos(Math.toRadians(angle)));

            enemy.setPosition(new Vector2(x, y));
        }
	}

	@Override
	public boolean isFinished() {
		return finished;
    }

    @Override
    public MoveCommand cpy() {
        return new MoveCommand(destination.cpy(), speed);
    }
}