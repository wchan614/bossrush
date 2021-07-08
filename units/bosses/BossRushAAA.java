package units.bosses;

import java.util.ArrayList;

import essence.BossRushEssence;
import statuses.BossRushStatuses;
import units.BossRushUnit;
import units.allies.BossRushAlly;

public class BossRushAAA extends BossRushBoss {
    private double skillChance = 0;
    private double turnNumber = 0;
    private String decision = "";
    private String previous = "none";
    public BossRushAAA() {
        super();
        this.setName("A.A.A");
    }

    public void extremeForm() {
        if (this.isExtremeMode()) { // buff
            this.setMaxHp(66666);
            this.setHp(66666);
            this.setAtk(499);
            this.setDef(166);
            this.setMAtk(444);
            this.setMDef(666);
            this.setSpd(166);
            this.setATB(666);
        }
    }
    
    @Override
    public void restore() {
        this.hp = this.maxHp;
        this.mp = this.maxMp;
        this.spd = 125;
        this.atk = 450;
        this.mAtk = 300;
        this.defeated = false;
        this.theirTurn = false;
        for (BossRushStatuses s : this.buffs) {
            s.onTimeOut(this);
        }
        for (BossRushStatuses s : this.debuffs) {
            s.onTimeOut(this);
        }
        this.buffs.clear();
        this.debuffs.clear();
        this.setATB(this.getSpd());
        skillChance = 0;
        turnNumber = 0;
        decision = "";
        previous = "none";
    }
    
    public BossRushEssence makeEssence(){
        return this.essenceFactory.makeEssence(3);
    }

    public String startTurn(String action, ArrayList<BossRushUnit> field) { // AI
        BossRushAlly target = targetSelection(field);
        ArrayList<BossRushUnit> targets = new ArrayList<BossRushUnit>();
        targets.add(this);
        targets.add(target);
        String log = "";
        decision = bossLogic(field);
        if (decision.equals("attack")) {
            log = this.attack(target);
        }
        else if (decision.equals("nightmare")){
            log = this.useSkill(field);
        }
        else if (decision.equals("mimic")) {
            log = this.useSkill(field);
        }
        else if (decision.equals("apathy")) {
            log = this.useSkill(field);
        }
        else if (decision.equals("ordeal")) {
            log = this.useSkill(field);
        }
        else if (decision.equals("one-one")) {
            log = this.useSkill(targets);
        }


        previous = decision;
        if (decision.equals("mimic") && previous.equals("mimic")) {
            previous = "none";
        }

    
        return log;
    }

    public String useSkill(ArrayList<BossRushUnit> field) {
        if (decision.equals("nightmare")) {
            return this.getSkills().get(0).doSkill(this, field);
        }
        else if (decision.equals("mimic")) {
            return this.getSkills().get(1).doSkill(this, field);
        }
        else if (decision.equals("apathy")) {
            return this.getSkills().get(2).doSkill(this,field);
        }
        else if (decision.equals("ordeal")) {
            return this.getSkills().get(3).doSkill(this,field);
        }
        else if (decision.equals("one-one")) {
            return this.getSkills().get(4).doSkill(this,field);
        }
        return "";
    }

    public BossRushAlly targetSelection(ArrayList<BossRushUnit> field) {
        int targetIndex = (int) (Math.random()*(field.size()-1)) + 1 ;
        while (field.get(targetIndex).isDefeated()) { // swap to next target
            targetIndex++;
            if (targetIndex > field.size()-1){
                targetIndex = 1;
            }
        }
        return (BossRushAlly)field.get(targetIndex);
    }

    public String bossLogic(ArrayList<BossRushUnit> field) {
        double r = Math.random()*100;
        turnNumber++;
        if (!isExtremeMode()) { // Normal Mode
            // Recast mimic to use it
            if (previous.matches("mimic")) {
                return  "mimic";
            }
            if (previous.matches("one-one")) {
                if (r < 50) {
                    return "apathy";
                }
                else {
                    return "nightmare";
                }
            }
            if (previous.matches("nightmare|apathy|attack")) {
                if (r < 25) {
                    return "mimic";
                }
                if (r < 50) {
                    return "attack";
                }
                if (r < 75) {
                    return "one-one";
                }
                else {
                    return "ordeal";
                }
            }
            if (previous.matches("ordeal")) {
                if (r < 30) {
                    return "nightmare";
                }
                if (r < 70) {
                    return "apathy";
                }
                else {
                    return "ordeal";
                }
            }
            if (previous.matches("none")) {
                if (r < 40) {
                    return "nightmare";
                }
                if (r < 70) {
                    return "apathy";
                }
                else {
                    return "mimic";
                }
            }
        }
        else { // Extreme Mode
            if (turnNumber % 5 == 0) {
                return "ordeal";
            }
            if (previous.matches("mimic")) {
                return  "mimic";
            }
            if (previous.matches("one-one")) {
                return "apathy";
            }
            if (previous.matches("nightmare|apathy|attack")) {
                if (r < 25) {
                    return "mimic";
                }
                if (r < 40) {
                    return "attack";
                }
                if (r < 55) {
                    return "one-one";
                }
                else {
                    return "ordeal";
                }
            }
            if (previous.matches("ordeal")) {
                if (r < 50) {
                    return "apathy";
                }
                else {
                    return "ordeal";
                }
            }
            if (previous.matches("none")) {
                if (r < 40) {
                    return "nightmare";
                }
                if (r < 70) {
                    return "apathy";
                }
                else {
                    return "mimic";
                }
            }
        }
        return "";
    }
}
