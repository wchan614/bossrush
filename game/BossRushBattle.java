package game;

import units.bosses.BossRushBoss;
import units.allies.BossRushAlly;
import units.BossRushUnit;

import java.util.Scanner;
import java.util.ArrayList;

public class BossRushBattle {
    
    private BossRushPlayer player;
    private BossRushBoss boss;
    private BossRushUnit curTurn;
    private BossRushATB turnManager;
    private ArrayList<BossRushUnit> turnOrder = new ArrayList<BossRushUnit>(); 
    private ArrayList<BossRushUnit> field = new ArrayList<BossRushUnit>();
    private ArrayList<BossRushAlly> team;
    private BossRushLogger logger = new BossRushLogger();
    private Scanner input = new Scanner(System.in);
    private int turnNumber = 0;

    public int initBattle(BossRushPlayer p, BossRushBoss b){
        this.player = p;
        this.boss = b;
        this.team = player.getAlliesActive();
        field.add(boss);
        for (int i = 0 ; i < team.size(); i++)
            field.add(team.get(i));
        turnManager = new BossRushATB(field);
        turnOrder = turnManager.calculateSpeeds(field);
        //turnOrder = calculateSpeeds(field);
        return doBattle();
    }

    public int doBattle(){
        while (true) { // Turn loop
            // Decide The turn order
            System.out.println("\n\n\n\n\n\n\n");

            setTurn(turnOrder.get(0));
            turnNumber++;
            //natural mp regen
            curTurn.regenMpNaturally();
            // trigger statuses
            curTurn.triggerStatuses();
            // Show battle menu
            showBattleMenu();
             // Unit's start

            if  (!boss.isTheirTurn() && !curTurn.isDefeated() && !boss.isDefeated()) { //  Our turn
                System.out.print("\nCommand: ");

                while (true) { // Cmd loop
                    String cmd = input.nextLine();
                    String log = curTurn.startTurn(cmd,field);
                    if (log.equals("b")) { //user pressed back
                        System.out.println("\n\n\n\n\n\n\n");
                        showBattleMenu();
                        System.out.print("Command: ");
                        continue;
                    }
                    logger.add(log);

                    if (cmd.equals("q")) {
                        battleOver();
                        return -1;
                    }
                    if (cmd.matches("a|s|w")) {
                        break;
                    }
                }
            }

            else if (boss.isTheirTurn() && !boss.isDefeated() && !isPlayerDefeated()) {
                String log = boss.startTurn("boss",field);
                logger.add(log);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
                System.out.println("\n\n\n\n\n\n\n");
                showBattleMenu();
                try {
                    Thread.sleep(1500);
                } catch (Exception e) {}
            }
            // Check everyones hp.
            if (isBattleOver()) {
                System.out.println("\n\n\n\n\n\n\n");
                showBattleMenu();

                if (isBossDefeated()) {
                    boss.setDefeated(true);
                    System.out.println("Objective Successful.");
                    return 1; // 1 = succesful
                }
                else {
                    System.out.println("Objective Unsuccessful.");
                    return 0; // 0 = loss
                }
                // unreachable
            }
            endTurn();
            nextTurn();
        }
    }

    public void printUserMenu() {
        for (int i = 0; i < 50 ; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
        System.out.print("a) attack     s) skills     w) wait     q) abandon\n");
        for (int i = 0; i < 50 ; i++) {
            System.out.print("-");
        }
        System.out.print("\n");

    }

    public void printTurnNumber() {
        for (int i = 0; i < 50 ; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
        System.out.printf("Turn %d:\n",turnNumber);
    }
    public void setTurn(BossRushUnit unit) {
        curTurn = unit;
        curTurn.setTurn(true);
    }

    public void endTurn() {
        curTurn.endTurn();
        curTurn.setTurn(false);
    }
    public void startTurn(String action,ArrayList<BossRushUnit> field) {
        curTurn.setTurn(true);
        curTurn.startTurn(action, field);
    
    }

    public void showBattleMenu() {
        boss.show(0);
        boss.showResourceBars();
        boss.showStatuses();
        for (int i = 1; i <= team.size(); i++) {
            team.get(i-1).show(i);
            team.get(i-1).showResourceBars();
            team.get(i-1).showStatuses();
            team.get(i-1).showStats();

        }
        printTurnNumber(); 
        printTurnOrder();
        printUserMenu();
        logger.printLog();
    }
    
    public void printTurnOrder() {
        for (int i = 0; i < 50 ; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
        for (int i = 0 ; i < turnOrder.size()-1 ; i++) {
            if (i == 0) {
                System.out.printf("[%s] -> ",turnOrder.get(i).getName());
            }
            else { 
                System.out.printf("%s -> ",turnOrder.get(i).getName());
            }
        }
        System.out.print(turnOrder.get(turnOrder.size()-1).getName() + "\n");
        
    }

    public ArrayList<BossRushUnit> calculateSpeeds(ArrayList<BossRushUnit> source) {
        ArrayList<Integer> speeds = new ArrayList<Integer>();
        ArrayList<Integer> index = new ArrayList<Integer>();
        ArrayList<BossRushUnit> output = new ArrayList<BossRushUnit>();

        for (int i = 0 ; i < source.size(); i++) {
            BossRushUnit unit = source.get(i);
            if (!source.get(i).isDefeated()) {
                speeds.add(unit.getSpd());
                index.add(i);
            }
        }
        
        // find max one at a time
        while (speeds.size() != 0) {
            int max = speeds.get(0);
            int maxIndex = index.get(0);
            int localMax = 0;
            for (int i = 0 ; i < speeds.size(); i++) {
                if (speeds.get(i) > max) {
                    max = speeds.get(i);
                    maxIndex = index.get(i);
                    localMax = i;
                }
            }
            //after finding max index
            output.add(source.get(maxIndex));
            speeds.remove(localMax);
            index.remove(localMax);
        } 
        return output;
    }

    public void nextTurn() {
        turnOrder = turnManager.calculateSpeeds(field);
    }

    public int getNumAlive() {
        int alive = 0;
        for (int i = 0 ; i < field.size(); i++) {
            if (!field.get(i).isDefeated())
                alive++;
        }
        return alive;
    }

    public Boolean isPlayerDefeated() {
        for (BossRushAlly ally : team) {
            if (!ally.isDefeated()) {
                return false;
            }
        }
        return true;
    }

    public Boolean isBossDefeated() {
        return boss.isDefeated();
    }

    public Boolean isBattleOver() {
        return (isPlayerDefeated() || isBossDefeated());
    }

    public void battleOver() {
        for (BossRushUnit unit : field) {
            unit.restore();
        }
    }
}
