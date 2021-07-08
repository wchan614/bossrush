package units.bosses;

import units.allies.*;
import units.*;
import java.util.ArrayList;

import essence.BossRushEssence;
import essence.BossRushEssences;
import skills.BossRushSkills;
import statuses.*;
public abstract class BossRushBoss extends BossRushUnit  {
    
    private Boolean extremeMode = false;
    private Boolean extremeDefeated = false;
    protected BossRushEssences essenceFactory = new BossRushEssences();
    
    public BossRushBoss() {}

    BossRushBoss(BossRushBoss boss) { //copy constructor
        this.setHp(boss.getHp());
        this.setMp(boss.getMp());
        this.setMaxHp(boss.getMaxHp());
        this.setMaxMp(boss.getMaxMp());
        this.setAtk(boss.getAtk());
        this.setDef(boss.getDef());
        this.setMAtk(boss.getMAtk());
        this.setMDef(boss.getMDef());
        this.setSpd(boss.getSpd());
        this.setName(boss.getName());
        this.setDefeated(boss.isDefeated());
        this.setDifficulty(boss.getDifficulty());
        this.setDescription(boss.getDescription());

        this.setDebuffs(boss.getDebuffs());
        this.setBuffs(boss.getBuffs());
        this.setSkills(boss.getSkills());

    }

    public BossRushBoss(String name) {
        this.setName(name);
    }

    public abstract BossRushEssence makeEssence();
    public abstract String bossLogic(ArrayList<BossRushUnit> field); // what to do
    public abstract BossRushAlly targetSelection(ArrayList<BossRushUnit> field); // who to target
    public abstract String startTurn(String action,ArrayList<BossRushUnit> field);
    public abstract void extremeForm();
    
    public void endTurn() {
        this.updateStatuses();
        this.setTurn(false);
    }
    public void showSkillInfo() {
        //pass;
    }

    public Boolean isExtremeMode() {
        return this.extremeMode;
    }

    public Boolean isExtremeDefeated() {
        return this.extremeDefeated;
    }

    public void setExtremeDefeated(Boolean t) {
        this.extremeDefeated = t;
    }

    public void setExtremeMode(Boolean t) {
        this.extremeMode = t;
        extremeForm();
    }

    public String onHit(BossRushUnit from) {
        return "";
    }
    private void println(String s) {
        System.out.println(s);
    }

    private void print(String s) {
        System.out.print(s);
    }

    
    public void show(int index) {
        if (this.isTheirTurn()) {
            for (int i = 0 ; i < 50 ; i++ ){
                print("V");
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
        println( this.getName() + " - " + this.getDescription() );
        if (this.isTheirTurn()) {
            for (int i = 0 ; i < 50 ; i++ ){
                print("^");
            }
        }
        else {
            for (int i = 0 ; i < 50 ; i++ ){
                print("-");
            }
        }
    }


    public void showResourceBars() {
        String hpBar = "";
        String mpBar = "";
        int size = 40;
        Double hpVal = Double.valueOf(this.getHp())/Double.valueOf(this.getMaxHp());
        Double mpVal = Double.valueOf(this.getMp())/Double.valueOf(this.getMaxMp());
        if (hpVal < 0.025 && hpVal > 0 ) {
            hpBar += "#";
        }
        else {
            for (int i = 0; i < (int)Math.round((hpVal*size)) ; i++) {
                hpBar += "#";
            }
        }
        if (mpVal < 0.025 && mpVal > 0) {
            mpBar += "#";
        }
        else {
            for (int i = 0; i < (int)Math.round((mpVal*size)) ; i++) {
                mpBar += "#";
            }
        }
        println(String.format("\nHP [%-"+size+"s]", hpBar));
        println(String.format("MP [%-"+size+"s]", mpBar));
    }

    public void showRanking() {
        print("\n / Rank - " + this.getDifficulty());
        if (this.isExtremeMode() == true) {
            print(" / ! Extreme Mode Available");
        }
        print("\n");
        for (int i = 0 ; i < 50 ; i++ ) {
            print("-");
        }
        println("\n");
    }
    public void showStats() {
        println(String.format("HP:   %8d  MP:   %8d  ATK: %8d  DEF: %8d\nMATK: %8d  MDEF: %8d  SPD: %8d",
                getHp(), getMp(), getAtk(), getDef(), getMAtk(), getMDef(), getSpd()));
    }
    public void showStatuses() {
        println("Statuses: ");
        for (BossRushStatuses buff : this.getBuffs()) {
            println(String.format("+ %s: [%d] / %s", buff.getName(), buff.getTurnsLeft(), buff.getDescription()));
        }
        for (BossRushStatuses debuff : this.getDebuffs()) {
            println(String.format("- %s: [%d] / %s", debuff.getName(), debuff.getTurnsLeft(), debuff.getDescription()));
        }
    }
    public void showSkills() {
        println("Skills: ");
        for (int i = 0 ; i < this.getSkills().size(); i++) {
            BossRushSkills skill = this.getSkills().get(i);
            print(String.format("%d) %s / COST: %sMP %sHP / POTENCY:%s / TYPE:%s / %s\n"
            , i+1, skill.getName(),skill.getMpCost(),skill.getHpCost(), skill.getPotency(),skill.getType(), skill.getDescription()));
        }
    }

}