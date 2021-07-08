package game;


import java.util.Scanner;
import java.lang.System;

public class BossRushGame {
    private Scanner input = new Scanner(System.in);
    private BossRushPlayer player = new BossRushPlayer();
    private BossRushObjectives objectives = new BossRushObjectives();
    private BossRushParty party = new BossRushParty();
    private BossRushEnemyArchive enemyListing = new BossRushEnemyArchive();
    private BossRushMemoryManager memManager = new BossRushMemoryManager();

    private void pushText() {
        print("\n\n\n\n\n");
    }
    private void println(String s) {
        System.out.println(s);
    }

    private void print(String s) {
        System.out.print(s);
    }

    public void tutorial() {
        println("Take on Objective bosses with your team and see if you can defeat all the objective bosses on extreme mode.");
        println("Inputting b allows you to go back from menus.");
    }

    public void start() {
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        println("                  BOSS RUSH  v.1.42             ");
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
        println("             WELCOME TO BOSS RUSH             \n");
        while (true){
            println("    \nIS THIS YOUR FIRST TIME PLAYING? Y/N \n");
            String firstTime = input.nextLine();

            if (firstTime.equals("y") || firstTime.equals("Y")) {
                println("\nWOULD YOU LIKE AN EXPLAINATION OF BOSS RUSH? Y/N \n");
                String tutorial = input.nextLine();

                if (tutorial.equals("y") || tutorial.equals("Y")) {
                    tutorial();
                    break;
                }
                
                else if (tutorial.equals("n") || tutorial.equals("N")) {
                    break;
                }
            }
            else if (firstTime.equals("n") || firstTime.equals("N")) {
                break;
            }
        }
        menu();
    }

    private void showMenu() {
        for (int i = 0; i < 50; i++) {
            print("-");
        }
        print("\n");
        println(" 1) Objectives");
        println(" 2) Party");
        println(" 3) Enemy Archive");
        println(" 4) Save");
        println(" 5) Load");
        println(" q) Quit  -- b) go back");
        for (int i = 0; i < 50; i++) {
            print("-");
        }
        print("\n");
    }

    public void menu() {
        while (true) {
            pushText();
            showMenu();
            String cmd = input.nextLine();
            pushText();
            switch(cmd) {
                case "1": // Objectives
                    for (int i = 0; i < 50; i++) {
                        print("-");
                    }
                    print("\nObjectives\n");
                    objectives.showObjectives(player);
                    break;
                case "2": // Party
                    print("\nParty\n");
                    party.showParty(player);
                    break;
                case "3":
                    print("\nEnemy Archive\n");
                    enemyListing.showEnemies(objectives);
                    break;
                case "4":
                    print("\nSave\n");
                    memManager.save(player, objectives, input);
                    break;
                case "5":
                    print("\nLoad\n");
                    memManager.load(player,objectives);
                    break;
                case "q":
                    print("\nQuit\n");
                    quit();
            }
        }
    }

    public void quit() {
        input.close();
        System.exit(0);
    }
 }
