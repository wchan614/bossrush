package units.bosses;

import java.util.ArrayList;

import essence.BossRushEssence;
import statuses.BossRushStatuses;
import units.BossRushUnit;
import units.allies.BossRushAlly;

public class BossRushCannoneer extends BossRushBoss {
    private double skillChance = 0;
    private double turnNumber = 0;
    private String previous = "none";
    private String decision = "";
    public BossRushCannoneer() {
        super();
        this.setName("Cannoneer");
    }

    public void extremeForm() {
        if (this.isExtremeMode()) { // buff
            this.setMaxHp(46000); //41000
            this.setHp(46000); //41000
            this.setMaxMp(6000);
            this.setMp(6000);
            this.setAtk(400);
            this.setDef(200);
            this.setMAtk(400);
            this.setMDef(200);
            this.setSpd(145);
            this.setATB(145);
        }
    }

    @Override
    public void restore() {
        this.hp = this.maxHp;
        this.mp = this.maxMp;
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
        previous = "none";
        decision = "";
    }
    
    public BossRushEssence makeEssence(){
        return this.essenceFactory.makeEssence(2);
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
        else if (decision.equals("prime")){
            log = this.useSkill(field);
        }
        else if (decision.equals("load")) {
            log = this.useSkill(field);
        }
        else if (decision.equals("blast")) {
            log = this.useSkill(targets);
        }
        else if (decision.equals("life of the sea")) {
            log = this.useSkill(field);
        }
        else if (decision.equals("bazooka")) {
            log = this.useSkill(field);
        }
        else if (decision.equals("depleting shot")) {
            log = this.useSkill(targets);
        }
        
        previous = decision;
        return log;
    }

    public String useSkill(ArrayList<BossRushUnit> field) { // randomly pick one
        if (decision.equals("prime")) {
            return this.getSkills().get(0).doSkill(this, field);
        }
        else if (decision.equals("load")) {
            return this.getSkills().get(1).doSkill(this, field);
        }
        else if (decision.equals("blast")) {
            return this.getSkills().get(2).doSkill(this,field);
        }
        else if (decision.equals("life of the sea")) {
            return this.getSkills().get(3).doSkill(this,field);
        }
        else if (decision.equals("bazooka")) {
            return this.getSkills().get(4).doSkill(this,field);
        }
        else if (decision.equals("depleting shot")) {
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
            if (previous.matches("none|life of the sea|blast|bazooka")) {
                return "prime";
            }
            if (previous.matches("prime")) {
                return "load";
            }
            if (previous.matches("load")) {
                return r < 70 ? "blast" : "bazooka";
            }
            if (r < 20) {
                return "life of the sea";
            }
            else if ( r < 60) {
                return "blast";
            }
            else {
                return "bazooka";
            }
        }
        else { // Extreme Mode
            if (previous.matches("none|life of the sea|blast|bazooka")) {
                return "prime";
            }
            if (previous.matches("prime")) {
                return "load";
            }
            if (previous.matches("load")) {
                return r < 30 ? "blast" : "bazooka";
            }
            if (r < 10) {
                return "life of the sea";
            }
            else if ( r < 20) {
                return "blast";
            }
            else if (r < 60) {
                return "bazooka";
            }
            else {
                return "depleting shot";
            }
        }
    }
}
