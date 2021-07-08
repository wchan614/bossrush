package game;

import units.allies.BossRushAlly;
import essence.*;
import java.util.Scanner;
import java.util.ArrayList;

public class BossRushParty {
    
    private int pageNumber = 0;
    private final int DISPLAY_LIMIT = 2;

    public void nextPage(int maxPage) {
        if (pageNumber < maxPage-1) {
            pageNumber++;
        }
    }

    public void previousPage(int maxPage) {
        if (pageNumber > 0) {
            pageNumber--;
        }

    }
    public void showParty(BossRushPlayer player) {
        int mostPages = (int)Math.ceil(Double.valueOf(player.getNumOfAllies())/DISPLAY_LIMIT);

        System.out.println("\n\n\n\n\n\n");

        System.out.printf("Page %d/%d\n\n",pageNumber+1,mostPages);
        System.out.println("z) Previous Page   x) Next Page\n");
        showAllies(player);
        showEssences(player);

        Scanner input = new Scanner(System.in);
        String cmd = "";

        while (true) {
            System.out.println("1) Form Team.");
            System.out.println("2) Give Essence to Ally.");
            System.out.println("3) Take Essence from Ally.");


            cmd = input.nextLine();
            BossRushEssence selectedEssence = null;
            BossRushAlly selectedAlly = null;
            Boolean backed = false;
            switch (cmd) {
                case "1": //contruct party
                    formTeam(player);
                    break;
                case "2": // Giving

                    if ( player.getEssences().size() > 0 ) {
                        while (!backed) {  // Loop through Essence in player inventory as options

                            System.out.println("Which Essence would you like to give?");
                            for (int i = 0; i < player.getEssences().size(); i++ ) {
                                BossRushEssence essence = player.getEssences().get(i);
                                System.out.printf("%d) %s  ", i+1, essence.getName());
                            }
                            System.out.print("\n");

                            int whichEssence = 0;
                            String essenceCmd = input.nextLine();
                            if (essenceCmd.equals("b")) {
                                backed = true;
                                break;
                            }
                            try {
                                Integer.valueOf(essenceCmd); 
                            } catch (Exception e) {
                                continue; // Wasn't a number try again
                            }

                            whichEssence = Integer.valueOf(essenceCmd);
                            if (!(whichEssence <= player.getEssences().size() && whichEssence > 0)) { // out of bounds
                                continue;
                            }

                            selectedEssence = player.getEssences().get(whichEssence-1);
                            break;
                        }

                        while (!backed) { // User picks a player on their team to give a essence.

                            System.out.println("Who will receieve this essence?");
                            for (int i = 0; i < player.getNumOfAllies(); i++ ) {
                                BossRushAlly ally =  player.getAllAllies().get(i);
                                System.out.printf("%d) %s  ", i+1, ally.getName());
                            }

                            int whichPlayer = 0;
                            String playerCmd = input.nextLine();
                            if (playerCmd.equals("b")) {
                                backed = true;
                                break;
                            }
                            try {
                                Integer.valueOf(playerCmd); 
                            } catch (Exception e) {
                                continue; // Wasn't a number try again
                            }
                            whichPlayer = Integer.valueOf(playerCmd);
                            if (!(whichPlayer <= player.getNumOfAllies() && whichPlayer > 0)) { // out of bounds
                                continue;
                            }
                            if (player.getAllAllies().get(whichPlayer - 1).getEssence() != null ) { // has space in pocket
                                System.out.println("This person already is holding an essence.");
                                continue;
                            }

                            selectedAlly = player.getAllAllies().get(whichPlayer - 1);
                            break;
                        }
                        if (!backed) {
                            System.out.println("Essence gave.");
                            selectedAlly.applyEssence(selectedEssence); // give to them,
                            //remove essence from inventory.
                            player.removeEssence(selectedEssence.getName());

                        }
                    }
                    else {
                        System.out.println("You don't possess any Essences.");
                    }

                        // apply the essence to the ally
                
                    break;
                
                
                case "3":  // Taking
                    // Loop through the players on their team to take an essence.
                    while (true) {
                            System.out.println("Would do you want to remove an essence from?");
                            for (int i = 0; i < player.getNumOfAllies(); i++ ) {
                                BossRushAlly ally =  player.getAllAllies().get(i);
                                System.out.printf("%d) %s  ", i+1, ally.getName());
                            }

                            int whichPlayer = 0;
                            String playerCmd = input.nextLine();
                            if (playerCmd.equals("b")) {
                                backed = true;
                                break;
                            }
                            try {
                                Integer.valueOf(playerCmd); 
                            } catch (Exception e) {
                                continue; // Wasn't a number try again
                            }
                            whichPlayer = Integer.valueOf(playerCmd);
                            if (!(whichPlayer <= player.getNumOfAllies() && whichPlayer > 0)) { // out of bounds
                                continue;
                            }
                            if (player.getAllAllies().get(whichPlayer - 1).getEssence() == null ) { // has space in pocket
                                System.out.println("This person is not holding any essence");
                                continue;
                            }

                            selectedAlly = player.getAllAllies().get(whichPlayer - 1);
                            break;
                        }
                        if (!backed){
                            System.out.println("Essence taken.");
                            player.addEssence(selectedAlly.getEssence());
                            selectedAlly.removeEssence(); // give to them,
                        }

                    break;

                case "z":
                    previousPage(mostPages);
                    break;

                case "x":
                    nextPage(mostPages);
                    break;
            }
            if (cmd.equals("b")) {
                return;
            }
            System.out.println("\n\n\n\n\n\n");
            showAllies(player);
            showEssences(player);
            System.out.println("\n\n\n\n\n\n");
            System.out.printf("Page %d/%d\n\n",pageNumber+1,mostPages);
            System.out.println("z) Previous Page   x) Next Page\n");
        }
    }
    

    public void showAllies(BossRushPlayer player) {
        int size =  player.getNumOfAllies();
        for (int i = pageNumber*DISPLAY_LIMIT; i < pageNumber*DISPLAY_LIMIT + Math.min(DISPLAY_LIMIT, size - pageNumber*DISPLAY_LIMIT); i++) {
            BossRushAlly ally = player.getAllAllies().get(i);
           
            ally.show(0);
            ally.showStats();
            ally.showSkills();
            System.out.print("Essence: ");
            if (ally.getEssence() != null) {
                ally.getEssence().printInfo();
            }
            else {
                System.out.print("None\n");
            }
            System.out.print("\n\n");
        }
    }

    public void showEssences(BossRushPlayer player) {

        System.out.println("Essences in Inventory:\n");
        for (BossRushEssence e : player.getEssences()) {
            e.printInfo();
            System.out.print("\n");
        }
    }

    public void formTeam(BossRushPlayer player) {
        System.out.println("\n\n\n\n\n");
        System.out.println("Select allies you want on the team.");
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < player.getNumOfAllies(); i++) {
           BossRushAlly ally = player.getAllAllies().get(i);
           System.out.printf("%d) %s - %4dHP %4dMP %4dATK %4dDEF %4dMATK %4dMDEF %4dSPD\n", i+1,
           ally.getName(), ally.getHp(), ally.getMp(), ally.getAtk(), ally.getDef(),ally.getMAtk(), ally.getMDef(), ally.getSpd());            
        }
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0 ; i < 3; i++ ) {
            int option;
            String read = input.nextLine();
            if (read.equals("b")) {
                return;
            }
            // Input validation
            try {
                option = Integer.valueOf(read);
            } catch (Exception e) {
                i--;
                continue;
            }
            
            // Out of Range 
            if (option < 1 || option > player.getNumOfAllies() || indices.contains(option)) {
                i--;
                continue;
            }

            System.out.println("Added.");
            indices.add(option);
        }


        ArrayList<BossRushAlly> newTeam = new ArrayList<BossRushAlly>();
        for (int i : indices) {
            BossRushAlly ally = player.getAllAllies().get(i-1);
            newTeam.add(ally);
        }
        player.setAlliesActive(newTeam);
    }
}