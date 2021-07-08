package game;

import java.io.FileWriter; // Import the FileWriter class
import java.io.File; // Import the File class
import java.io.BufferedWriter;
import java.util.Scanner;
import units.allies.BossRushAlly;
import units.bosses.BossRushBoss;

public class BossRushMemoryManager {
    private final String name = "BR-SAVE";
    private final String ext = ".sav";
    // save the alliesOwned in BossRushPlayer.java
    // save the objectiveCount in BossRushObjectives.java
    // save whos currently in active team;
    // save which bosses have been defeated. if defaeted extreme unlocked (up to)
    // save which bosses have been defeated in extreme mode -> which essences unlocked
    // save which person is holding which essence.

    public BossRushMemoryManager(){};

    public void save(BossRushPlayer player, BossRushObjectives objectives, Scanner input) {
        try {

            File save = new File(name+ext);

            if (save.isFile()) {
                System.out.println("Would you like to overwrite your existing save? Y/N");
                if (!input.nextLine().matches("Y|y")) {
                    return;
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(save));
            writer.write(String.valueOf(player.getNumOfAllies())); //alliesOwned
            writer.newLine();
            writer.write(String.valueOf(objectives.getObjectiveCount())); // objectiveCount
            writer.newLine();
            // Record active team;
            for (int i = 0; i < player.getAlliesActive().size(); i++) {
                BossRushAlly ally = player.getAlliesActive().get(i);

                for (int j = 0; j < player.getNumOfAllies(); j++) {
                    BossRushAlly allyListing = player.getAllAllies().get(j);
                    if (allyListing.getName().equals(ally.getName())) {
                        writer.write(String.valueOf(j));
                        writer.newLine();
                        break;
                    }
                }
            }
            // Record number of bosses defeated.
            int defeated = 0;
            for (int i = 0 ; i < objectives.getObjectiveCount(); i++) { // 
                BossRushBoss b = objectives.getObjectives().get(i);
                if (b.isExtremeMode()) {
                    defeated++;
                }
                else {
                    break;
                }
            }
            writer.write(String.valueOf(defeated));
            writer.newLine();
            //  For each extreme defeated , figure out who is holding essence
            // record index of essence or none
            for (int i = 0; i < objectives.getObjectiveCount(); i++) {
                BossRushBoss b = objectives.getObjectives().get(i);
                if (b.isExtremeDefeated()) {
                    writer.write("1");

                    for (int j = 0; j < player.getNumOfAllies(); j++) {
                        BossRushAlly ally = player.getAllAllies().get(j);
                        if (ally.hasEssence() == true && ally.getEssence().getFrom().equals(b.getName())) {
                            writer.write(String.valueOf(j));
                            break;
                        }
                        if (j+1 == player.getNumOfAllies()) {
                            writer.write("P"); // P = on player.
                        }
                    }
                }
                else {
                    writer.write("0");
                }
                writer.newLine();
            }
            writer.close();
            System.out.println("Saving Successful.");
        } catch (Exception e) {
            System.out.println("Saving failed.");
        }
    }

    public void load(BossRushPlayer player, BossRushObjectives objectives) {
        try {
            File save = new File(name+ext);
            if (!save.isFile()) {
                System.out.println("There is no save file to load.");
                return;
            }

            Scanner reader = new Scanner(save);
            // reset all back to normal just incase
            for (BossRushBoss boss: objectives.getObjectives()) {
                boss.setDefeated(false);
                boss.setExtremeDefeated(false);
            }
            for (BossRushAlly ally: player.getAllAllies()) {
                ally.removeEssence();
            }

            // read and set alliesOwned
            int alliesOwned = Integer.valueOf(reader.nextLine());
            player.setNumOfAllies(alliesOwned);

            // read and set objectiveCount
            int objectiveCount = Integer.valueOf(reader.nextLine());
            objectives.setObjectiveCount(objectiveCount);

            System.out.println("Loaded player. Loaded objectives.");
            // read and set active members in party
            player.getAlliesActive().clear();
            for (int i = 0; i < 3; i++) {
                int indexOfAlly = Integer.valueOf(reader.nextLine());
                player.getAlliesActive().add(player.getAllAllies().get(indexOfAlly));
            }

            System.out.println("Loaded party.");
            // Read how many bosses were defeated normally.
            int bossesDefeated = Integer.valueOf(reader.nextLine());
            player.getEssences().clear();
            for (int i = 0; i < objectiveCount; i++) {

                BossRushBoss boss = objectives.getObjectives().get(i);
                String essenceIndex = reader.nextLine();
                if (i < bossesDefeated) {
                    boss.setExtremeMode(true);
                }
                if (essenceIndex.equals("0")) {
                    boss.setExtremeDefeated(false);
                }
                else {
                    boss.setExtremeDefeated(true);
                    String essenceLocation = essenceIndex.substring(1);
                    if (essenceLocation.matches("P")) { // make essence and put in player inventory
                        player.addEssence(boss.makeEssence());
                    }
                    else { // equip the essence
                        int allyIndex = Integer.valueOf(essenceLocation);
                        player.getAllAllies().get(allyIndex).applyEssence(boss.makeEssence());
                    }
                }
            }
            System.out.println("Loaded essences.");
            reader.close();
        } catch (Exception e) {
            System.out.println("Error while loading save file. Save may be corrupted.");
        }

    }

}
