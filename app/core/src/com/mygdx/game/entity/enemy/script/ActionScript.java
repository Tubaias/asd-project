
package com.mygdx.game.entity.enemy.script;

import java.util.ArrayList;

import com.mygdx.game.entity.enemy.Enemy;

public class ActionScript {
    private ArrayList<ActionCommand> commands;
    private int commandIndex;

    public ActionScript() {
        this.commands = new ArrayList<>();
    }

    public ActionScript(ArrayList<ActionCommand> commands) {
        this.commands = commands;
    }

    public void addCommand(ActionCommand command) {
        this.commands.add(command);
    }

    public void step(Enemy enemy) {
        if (commandIndex >= commands.size()) {
            return;
        }

        commands.get(commandIndex).step(enemy);

        if (commands.get(commandIndex).isFinished()) {
            commandIndex++;
        }
    }

    public ActionScript cpy() {
        ArrayList<ActionCommand> commandsCopy = new ArrayList<>();

        for (ActionCommand c : commands) {
            commandsCopy.add(c.cpy());
        }

        return new ActionScript(commandsCopy);
    }
}