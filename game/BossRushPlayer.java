package game;

import units.allies.*;
import java.util.ArrayList;
import essence.*;

public class BossRushPlayer {
    
    private ArrayList<BossRushAlly> alliesActive = new ArrayList<BossRushAlly>();
    private ArrayList<BossRushAlly> alliesAllUnlocked = new ArrayList<BossRushAlly>();
    private ArrayList<BossRushEssence> essences = new ArrayList<BossRushEssence>();
    private BossRushAllies allies = new BossRushAllies(); // factory
    private int alliesOwned = 3;


    public BossRushPlayer() {
        this.alliesAllUnlocked.add(allies.makeAlbert());
        this.alliesAllUnlocked.add(allies.makeLifa());
        this.alliesAllUnlocked.add(allies.makeStorm());
        this.alliesAllUnlocked.add(allies.makeLeo());
        this.alliesAllUnlocked.add(allies.makePetra());
        this.alliesAllUnlocked.add(allies.makeRhys());
        for (int i = 0 ; i < 3; i++ ) { //Populate default team.
            alliesActive.add(alliesAllUnlocked.get(i));
        }
    }

    public int getNumOfAllies() {
        return this.alliesOwned;
    }

    public void setNumOfAllies(int num) {
        this.alliesOwned = num;
    }
    
    public ArrayList<BossRushAlly> getAlliesActive() {
        return this.alliesActive;
    }

    public void setAlliesActive(ArrayList<BossRushAlly> newTeam) {
        this.alliesActive = newTeam;
    }

    public ArrayList<BossRushAlly> getAllAllies() {
        return alliesAllUnlocked;
    }

    public ArrayList<BossRushEssence> getEssences() {
        return this.essences;
    }
    public void addEssence(BossRushEssence essence) {
        this.essences.add(essence);
    }

    public void removeEssence(String name) {
        for (int i = 0; i < essences.size(); i++) {
            if (essences.get(i).getName().equals(name)) {// Found the essence
                essences.remove(i);
                return;
            }
        }
    }

    public void levelUp() { // advance the player to unlock another ally
        int oldLevel = alliesOwned;
        alliesOwned = Math.min(alliesOwned + 1, alliesAllUnlocked.size()); 
        if (alliesOwned == oldLevel++) {
            System.out.println("Level up! New Ally unlocked.");
        }
    }
}