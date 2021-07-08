package units.bosses;

import units.BossRushUnit;
import units.allies.BossRushAlly;
import java.util.ArrayList;

import essence.BossRushEssence;
import statuses.BossRushStatuses;
public class BossRushRavager extends BossRushBoss {
    private double skillChance = 0;
    private double turnNumber = 0;
    private String decision;
    public BossRushRavager() {
        super();
        this.setName("Ravager");
    }

    public void extremeForm() {
        if (this.isExtremeMode()) { // buff
            this.setMaxHp(22000);
            this.setHp(22000);
            this.setMaxMp(6000);
            this.setMp(6000);
            this.setAtk(400);
            this.setDef(400);
            this.setMAtk(50);
            this.setMDef(400);
            this.setSpd(105);
            this.setATB(105);
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
    }

    public BossRushEssence makeEssence(){
        return this.essenceFactory.makeEssence(1);
    }
    
    public String startTurn(String action, ArrayList<BossRushUnit> field) { // AI
        BossRushAlly target = targetSelection(field);
        String log = "";
        decision = bossLogic(field);
        if (decision.equals("attack")) {
            log = this.attack(target);
        }
        else if (decision.equals("cripple")){
            ArrayList<BossRushUnit> targets = new ArrayList<BossRushUnit>();
            targets.add(this);
            targets.add(target);
            log = this.useSkill(targets);
        }
        else if (decision.equals("demolish")) {
            log = this.useSkill(field);
        }
        else if (decision.equals("haste")) {
            log = this.useSkill(field);
        }

        return log;
    }

    public String useSkill(ArrayList<BossRushUnit> field) { // randomly pick one
        if (decision.equals("cripple")) {
            return this.getSkills().get(0).doSkill(this, field);
        }
        else if (decision.equals("demolish")) {
            return this.getSkills().get(1).doSkill(this, field);
        }
        else if (decision.equals("haste")) {
            return this.getSkills().get(2).doSkill(this,field);
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
        if (!isExtremeMode()) {
            if (this.getHp() < 3000 && this.hasStatus("Hastened") == null) {
                return "haste";
            }
            if (r < 35.0 - skillChance) {
                this.skillChance += 5.0;
                return "attack";
            }
            else if (r < 60.0 - skillChance) {
                this.skillChance += 7.5;
                return "demolish";
            }
            else {
                this.skillChance = 0;
                return "cripple";
            }
        }
        else {
            if (this.getHp() < 6000 && this.hasStatus("Hastened") == null) {
                return "haste";
            }
            if (r < 25.0 - skillChance) {
                this.skillChance += 8.0;
                return "attack";
            }
            else if (r < 50.0 - skillChance) {
                this.skillChance += 10;
                return "demolish";
            }
            else {
                this.skillChance = 0;
                return "cripple";
            }
        }
    }
}