
package com.mygdx.game.level.event;

public interface LevelEvent {
    void execute();
    boolean isTime(float currentTime);
}