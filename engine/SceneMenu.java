/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author redblast71
 */
public class SceneMenu extends SceneBase {

    public static Image back;
    private WindowCommand command;
    private WindowItem inventory;
    private Window activeWindow;
    //private Input input;
    public int stateID = -1;

    public SceneMenu(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        input = gc.getInput();
        String[] coms = new String[6];
        coms[0] = "Item";
        coms[1] = "Skill";
        coms[2] = "Equip";
        coms[3] = "Status";
        coms[4] = "Save";
        coms[5] = "Options";
        command = new WindowCommand(120, coms, 1, 0);
        inventory = new WindowItem(command.x, command.y, SceneMap.B_WIDTH, SceneMap.B_HEIGHT, worldPlayer.getInv());
        activeWindow = command;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.drawImage(back, 0, 0);
        if (activeWindow != null) {
            activeWindow.render(grphcs, sbg);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        ((WindowSelectable) activeWindow).update(inputp);
        if (activeWindow.equals(command)) {
            switch (command.index) {
                case 0:
                    if (inputp.isCommandControlDown(action)) {
                        activeWindow = inventory;
                    }
                    break;
            }
        }
        if (inputp.isCommandControlPressed(cancel)) {
            if (activeWindow.equals(command)) {
                Sounds.cancel.play();
                input.clearKeyPressedRecord();
                input.clearControlPressedRecord();
                sbg.getState(1).update(gc, sbg, delta);
                sbg.enterState(1);
            } else {
                Sounds.cancel.play();
                activeWindow = command;
            }
        }
    }
}
