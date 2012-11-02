/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import org.newdawn.slick.Image;

/**
 *
 * @author redblast71
 */
public class GameBattler {
    
    private final int HP_LIMIT = 999999;
    public Image battleSprite;
    public int currentHP;
    public int currentMP;
    public int baseHP;
    public int HPplus;
    public int baseMP;
    public int MPplus;
    public int baseATK;
    public int ATKplus;
    public int baseMATK;
    public int MATKplus;
    public int baseDEF;
    public int DEFplus;
    public int baseMDEF;
    public int MDEFplus;
    public int baseSPD;
    public int SPDplus;
    
    public GameBattler(){
        battleSprite = null;
        currentHP = 0;
        currentMP = 0;
        //@action = Game_BattleAction.new(self)
        //@states = []                    # States (ID array)
        //@state_turns = {}               # Remaining turns for states (Hash)
        //@hidden = false   
        //@immortal = false
        clearExtraValues();
        //clear_sprite_effects
        //clear_action_results 
    }
    
    public void clearExtraValues(){
        HPplus = 0;
        MPplus = 0;
        ATKplus = 0;
        MATKplus = 0;
        DEFplus = 0;
        MDEFplus = 0;
        SPDplus = 0;
    }
    
    public int getMaxHP(){
        return Math.min(Math.max(baseHP + HPplus, 1), HP_LIMIT);
    }
    
    public int getMaxMP(){
        return Math.min(Math.max(baseMP + MPplus, 1), 9999);
    }
    
    public int getMaxATK(){
        return Math.min(Math.max(baseATK + ATKplus, 1), 999);
    }
    
    public int getMaxMATK(){
        return Math.min(Math.max(baseMATK + MATKplus, 1), 999);
    }
    
    public int getMaxDEF(){
        return Math.min(Math.max(baseDEF + DEFplus, 1), 999);
    }
    
    public int getMaxMDEF(){
        return Math.min(Math.max(baseMDEF + MDEFplus, 1), 999);
    }
    
    public int getMaxSPD(){
        return Math.min(Math.max(baseSPD + SPDplus, 1), 999);
    }
}