package units.allies;

import essence.BossRushEssence;
import skills.BossRushSkills;
import statuses.BossRushStatuses;
import units.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BossRushAlly extends BossRushUnit {
 
    private static Scanner input = new Scanner(System.in);
    private String role;
    private BossRushEssence essence = null;


    BossRushAlly(BossRushAlly ally) {
        this.setHp(ally.getHp());
        this.setMp(ally.getMp());
        this.setMaxHp(ally.getMaxHp());
        this.setMaxMp(ally.getMaxMp());
        this.setAtk(ally.getAtk());
        this.setDef(ally.getDef());
        this.setMAtk(ally.getMAtk());
        this.setMDef(ally.getMDef());
        this.setSpd(ally.getSpd());
        this.setName(ally.getName());
        this.setDefeated(ally.isDefeated());
        this.setRole(ally.getRole());

        this.setDebuffs(ally.getDebuffs());
        this.setBuffs(ally.getBuffs());
        this.setSkills(ally.getSkills());

    }

    private void println(String s) {
        System.out.println(s);
    }

    private void print(String s) {
        System.out.print(s);
    }

    public void endTurn() {
        this.updateStatuses();
        this.setTurn(false);
    }

    public String startTurn(String action, ArrayList<BossRushUnit> field) {
        // trigger effects of debuffs or buffs

        String retlog = "";
        this.setTurn(true);

        switch (action) {
            case "a" : //Begin Attack
                retlog += this.attack(field.get(0)); // attack boss as boss index 0 in field
                break;
            case "s" :
                retlog += this.useSkill(field);
                break;
            case "w":
                retlog += this.waits();
                break;
        }
        return retlog;
    }
    
    public String useSkill(ArrayList<BossRushUnit> field) {
        //print skills
        //await an input
        // if input is b go back
        // use skill - skill may ask for target
        this.showSkills();
        while (true){ 
            String cmd = input.nextLine();
            if (cmd.equals("b")) {
                return cmd;
            }
            try {
                Integer.valueOf(cmd);
            } catch (Exception e){
                continue;
            }
            int skillNum = Integer.valueOf(cmd);
            if (1 <= skillNum && skillNum <= this.getSkills().size()) {
                BossRushSkills skill = this.getSkills().get(skillNum-1);
                if (skill.getMpCost() > this.getMp()) {
                    System.out.println("Not enough MP.");
                    continue;
                }
                if (skill.getHpCost() > this.getHp()) {
                    System.out.println("Not enough HP.");
                    continue;
                }
                String retlog = skill.doSkill(this,field);
                if (retlog.equals("b")) {
                    return "b";
                }
                return retlog;
            }   
        }
    }


    public BossRushAlly (String name) {
        this.setName(name);
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public void applyEssence(BossRushEssence essence) {
        this.essence = essence;
        essence.onApply(this);
    }

    public BossRushEssence getEssence() {
        return this.essence;
    }

    public void removeEssence() {
        if (this.essence != null) {
            essence.onRemove(this);
            this.essence = null;
        }
    }

    public Boolean hasEssence() {
        if (this.essence == null) {
            return false;
        }
        return true;
    }


    public void show(int index){
        if (this.isTheirTurn()) {
            for (int i = 0 ; i < 50 ; i++ ){
                print(">");
            }
        }
        else {
            for (int i = 0 ; i < 50 ; i++ ){
                print("-");
            }
        }
        print("\n");
        if (index != 0) {
            print(index + ") ");
        }
        println(this.getName() + " - " + this.getRole());
        if (this.isTheirTurn()) {
            for (int i = 0 ; i < 50 ; i++ ){
                print("<");
            }
        }
        else {
            for (int i = 0 ; i < 50 ; i++ ){
                print("-");
            }
        }
        println("\n");
    }

    public void showStats() {
        println(String.format("HP:   %8d  MP:   %8d\nATK:  %8d  DEF:  %8d\nMATK: %8d  MDEF: %8d  SPD: %5d",
                getHp(), getMp(), getAtk(), getDef(), getMAtk(), getMDef(), getSpd()));
    }

    public void showResourceBars() {
        String hpBar = "";
        String mpBar = "";
        int size = 20;
        Double hpVal = Double.valueOf(this.getHp())/Double.valueOf(this.getMaxHp());
        Double mpVal = Double.valueOf(this.getMp())/Double.valueOf(this.getMaxMp());
        if (hpVal < 0.05 && hpVal > 0 ) {
            hpBar += "#";
        }
        else {
            for (int i = 0; i < (int)Math.round((hpVal*size)) ; i++) {
                hpBar += "#";
            }
        }
        if (mpVal < 0.05 && mpVal > 0) {
            mpBar += "#";
        }
        else {
            for (int i = 0; i < (int)Math.round((mpVal*size)) ; i++) {
                mpBar += "#";
            }
        }



        println(String.format("HP [%-"+size+"s] ", hpBar));
        println(String.format("MP [%-"+size+"s] ", mpBar));
    }

    public String onHit(BossRushUnit from) {
        if (this.hasStatus("Gambit") != null && this.getHp() == 0) {
            this.setHp(1);
            this.setDefeated(false);
            return String.format("But %s refused to be defeated. ", this.getName());
        }
        if (this.isDefeated()) {
            this.setMp(0);
        }
        return "";
    }

    public void showSkills() {
        println("Skills: ");
        for (int i = 0 ; i < this.getSkills().size(); i++) {
            BossRushSkills skill = this.getSkills().get(i);
            print(String.format("%d) %s / COST: %sMP %sHP / POTENCY:%s / TYPE:%s / %s\n"
            , i+1, skill.getName(),skill.getMpCost(),skill.getHpCost(), skill.getPotency(),skill.getType(), skill.getDescription()));
        }
    }

    public void showStatuses() {
        println("Statuses: ");
        for (BossRushStatuses buff : this.getBuffs()) {
            println(String.format("+ %s: [%d] / %s", buff.getName(), buff.getTurnsLeft(), buff.getDescription()));
        }
        for (BossRushStatuses debuff : this.getDebuffs()) {
            println(String.format("- %s: [%d] / %s ", debuff.getName(), debuff.getTurnsLeft(), debuff.getDescription()));
        }
    }
}
