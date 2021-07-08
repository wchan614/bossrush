package units.bosses;

import units.BossRushUnit;
import units.allies.BossRushAlly;
import java.util.ArrayList;

import essence.BossRushEssence;
import statuses.BossRushStatuses;

public class BossRushOxo extends BossRushBoss {
    private double skillChance = 0;
    private double turnNumber = 0;
    public BossRushOxo() {
        super();
        this.setName("Oxo");
    }

    public void extremeForm() {
        if (this.isExtremeMode()) { // buff
            this.setMaxHp(18000);
            this.setHp(18000);
            this.setAtk(400);
            this.setDef(450);
            this.setMAtk(400);
            this.setMDef(450);
            this.setSpd(102);
            this.setATB(102);
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
        return this.essenceFactory.makeEssence(0);
    }

    public String startTurn(String action, ArrayList<BossRushUnit> field) { // AI
        BossRushAlly target = targetSelection(field);
        String log = "";
        if (bossLogic(field).equals("attack")) {
            log = this.attack(target);
        }
        else {
            log = this.useSkill(field);
        }

        return log;
    }

    public String useSkill(ArrayList<BossRushUnit> field) {
        return this.getSkills().get(0).doSkill(this, field);
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

            if (r < 85.0 - skillChance) {
                this.skillChance += 5.0;
                return "attack";
            }
            else {
                this.skillChance = 0;
                return "skill";
            }
        }
        else {
            if (turnNumber == 1) {
                return "skill";
            }
            if (turnNumber % 3 == 0) {
                return "skill";
            }
            if (r < 40.0 - skillChance) {
                this.skillChance += 10.0;
                return "attack";
            }
            else {
                this.skillChance = 0;
                return "skill";
            }
        }
    }
}
