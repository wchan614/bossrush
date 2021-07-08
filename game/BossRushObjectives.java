package game;

import units.bosses.*;
import java.util.ArrayList;
import java.util.Scanner;

import essence.BossRushEssences;


public class BossRushObjectives {
    private Scanner input = new Scanner(System.in);
    private ArrayList<BossRushBoss> objectives = new ArrayList<BossRushBoss>();
    private BossRushBosses bosses = new BossRushBosses(); // factory
    private BossRushEssences essenceFactory = new BossRushEssences();
    private BossRushBattle battleField;
    private int objectiveCount = 1;

    private void println(String s) {
        System.out.println(s);
    }

    private void print(String s) {
        System.out.print(s);
    }

    private void pushText() {
        print("\n\n\n\n\n");
    }

    public BossRushObjectives() {
        objectives.add(bosses.makeBossOxo());
        objectives.add(bosses.makeBossRavager());
        objectives.add(bosses.makeBossCannoneer());
        objectives.add(bosses.makeBossAAA());
    }
    public void showObjectives(BossRushPlayer player) {
        for (int i = 0; i < objectiveCount; i++) { 
            objectives.get(i).show(i+1);
            objectives.get(i).showRanking();
        }
        print("Select a boss to fight\n");

        while (true) {
            String check = input.nextLine();
            if (check.equals("b")) {
                return;
            }
            try {
                Integer.parseInt(check);
            } catch  (NumberFormatException e) {
                continue;
            }
            int cmd = Integer.parseInt(check);
            if (0 < cmd && cmd <= objectiveCount) {
                BossRushBoss boss = objectives.get(cmd-1);

                if (boss.isExtremeMode() == true) { // Extreme mode unlocked
                    println("1) Normal    2) Extreme ");
                    String extreme = input.nextLine(); 
                    if (extreme.equals("1")) { // do normal
                        battleBossNormally(cmd,player);
                    }
                    else if (extreme.equals("2")) { // do extreme
                        boss.setExtremeMode(true);
                        int result = beginObjective(cmd, player);
                        if (result == 1 && boss.isExtremeDefeated() != true) {// we have defeated it first time in extreme.
                            boss.setExtremeDefeated(true); 
                            System.out.println("Boss Essence Gem Retrieved!");
                            player.addEssence(essenceFactory.makeEssence(cmd-1)); // add the essence
                        }
                    }
                    else { // reloop
                        for (int i = 0; i < objectiveCount; i++) { 
                            objectives.get(i).show(i+1);
                            objectives.get(i).showRanking();
                        }
                        print("Select a boss to fight\n");
                        continue;
                    }
                } 
                else {  // Extreme Mode is locked
                    battleBossNormally(cmd,player); // do normal
                }
                return;
            }
        }
    }

    public void battleBossNormally(int cmd, BossRushPlayer player) {
        BossRushBoss boss = objectives.get(cmd-1);
        int result = beginObjective(cmd, player);

        if (result == 1 && boss.isExtremeMode() == false) {
            boss.setExtremeMode(true);
            if (cmd == objectiveCount) { 
                objectiveCount = Math.min(objectiveCount+1,objectives.size());  
                    player.levelUp();
            }
        }
    }

    public int beginObjective(int cmd, BossRushPlayer player) {
        pushText();
        BossRushBoss boss = objectives.get(cmd-1);
        battleField = new BossRushBattle();
        int ret = battleField.initBattle(player,boss);
        print("Enter any key to continue...");
        battleField.battleOver();
        input.nextLine();
        if (ret == 1) { // Objective Successful

        }
        return ret;
    }

    public ArrayList<BossRushBoss> getObjectives() {
        return this.objectives;
    }
    public int getObjectiveCount() {
        return this.objectiveCount;
    }
    public void setObjectiveCount(int ob) {
        this.objectiveCount = ob;
    }
}
