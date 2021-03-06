/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import effectutil.CameraFadeInTransition;
import java.util.ArrayList;
import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.ControllerButtonControl;
import org.newdawn.slick.command.ControllerDirectionControl;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Kieran
 */
public class SceneTitle extends SceneBase{
    
    private CameraFadeInTransition fin;
    private KeyListener klistener;
    private ControllerListener clistener;
    private Image back;
    private WindowCommand wind;
    private WindowMessage test;
    private int keyPressed;
    private char repChar;
    private int controller;
    private int buttonPressed;
    private boolean inSubMenu;
    private ArrayList<Window> uielements;
    private Window lastAdded;
    public int stateID = -1;
    
    public SceneTitle(int stateID){
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        inSubMenu = false;
        klistener = new KeyListener(){ 
            
            @Override
            public void keyPressed(int index, char c){
            keyPressed = index; repChar = c;
            }
            
            @Override
            public void keyReleased(int index, char c){
            
            }
            
            @Override
            public void inputStarted(){
            
            }
            
            @Override
            public void inputEnded(){
            
            }
            
            @Override
            public boolean isAcceptingInput(){
                return true;
            }
            
            @Override
            public void setInput(Input input){
                
            }
        
        };
        clistener = new ControllerListener(){

            @Override
            public void controllerLeftPressed(int i) {
                
            }

            @Override
            public void controllerLeftReleased(int i) {
                
            }

            @Override
            public void controllerRightPressed(int i) {
                
            }

            @Override
            public void controllerRightReleased(int i) {
                
            }

            @Override
            public void controllerUpPressed(int i) {
                
            }

            @Override
            public void controllerUpReleased(int i) {
                
            }

            @Override
            public void controllerDownPressed(int i) {
                
            }

            @Override
            public void controllerDownReleased(int i) {
                
            }

            @Override
            public void controllerButtonPressed(int i, int i1) {
                controller = i; buttonPressed = i1;
            }

            @Override
            public void controllerButtonReleased(int i, int i1) {
                
            }

            @Override
            public void setInput(Input input) {
                
            }

            @Override
            public boolean isAcceptingInput() {
                return true;
            }

            @Override
            public void inputEnded() {
                
            }

            @Override
            public void inputStarted() {
                
            }
        };
        input = gc.getInput();
        input.addKeyListener(klistener);
        input.addControllerListener(clistener);
        inputp = new InputProvider(input);
        inputp.bindCommand(new KeyControl(Input.KEY_W), up);
        inputp.bindCommand(new KeyControl(Input.KEY_S), down);
        inputp.bindCommand(new KeyControl(Input.KEY_A), left);
        inputp.bindCommand(new KeyControl(Input.KEY_D), right);
        inputp.bindCommand(new KeyControl(Input.KEY_E), menu);
        inputp.bindCommand(new KeyControl(Input.KEY_K), cancel);
        inputp.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.DOWN), down);
        inputp.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.UP), up);
        inputp.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.LEFT), left);
        inputp.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.RIGHT), right);
        inputp.bindCommand(new ControllerButtonControl(0, 1), sprint);
        inputp.bindCommand(new ControllerButtonControl(0, 3), action);
        inputp.bindCommand(new ControllerButtonControl(0, 4), menu);
        inputp.bindCommand(new ControllerButtonControl(0, 2), cancel);
        inputp.bindCommand(new KeyControl(Input.KEY_LSHIFT), sprint);
        inputp.bindCommand(new KeyControl(Input.KEY_J), action);
        uielements = new ArrayList<>();
        fin = new CameraFadeInTransition();
        back = Cache.getRes("TitleBack.png");
        String[] coms = new String[]{"New Game","Continue","Options","Exit"};
        wind = new WindowCommand(160, coms, 1, 0);
        gameMessage.texts.push("Hello, this is a test of the message system");
        gameMessage.texts.push("This is a new line. This is still the same line");
        gameMessage.texts.push("Habla espanol por favor, senor. No hablo ingles.");
        gameMessage.texts.push("Goody gumdrop trincket tucker pie jammer.");
        test = new WindowMessage();
        wind.initX = (1280/2) - 80;
        wind.initY = 500;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        back.draw(0, 0);
        Cache.getFont().drawString(300, 100, "Debug Message: KeyListener "+repChar);
        Cache.getFont().drawString(300, 150, "Debug Message: ContollerListener Button: "+buttonPressed+" on Controller: "+controller);
        if(!inSubMenu){
            wind.render(g, sbg);
        }
        else{
            Cache.getFont().drawString(300, 50, "Debug Message: In Submenu");
            for(Window w: uielements){
            w.render(g, sbg);
            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if(!inSubMenu){
        ((WindowSelectable)wind).update(inputp);
            if(inputp.isCommandControlDown(action)){
                switch(wind.index){
                    case 0:
                        input.removeKeyListener(klistener);
                        input.removeControllerListener(clistener);
                        input.clearKeyPressedRecord();
                        input.clearControlPressedRecord();
                        sbg.enterState(1, new FadeOutTransition(), fin); break;
                    case 1: lastAdded = test;
                        uielements.add(lastAdded); break;
                    case 2:
                        lastAdded = new WindowSystem(2, 1);
                        uielements.add(lastAdded); break;
                    case 3: gc.exit(); break;
                }
                inSubMenu = true;
            }
        }
        else{
            for(Window w: uielements){
                if(w instanceof WindowSelectable){
                    ((WindowSelectable)w).update(inputp);
                }
            }
            if(inputp.isCommandControlDown(cancel)){
                uielements.remove(lastAdded);
                inSubMenu = false;
            }
        }
    }
    
}
