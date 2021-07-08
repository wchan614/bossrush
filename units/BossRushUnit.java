package units;

import statuses.*;
import skills.*;

import java.util.ArrayList;

public abstract class BossRushUnit {
    protected String name;
    protected String desciption;
    protected String difficulty;
    protected int hp;
    protected int mp;
    protected int maxHp;
    protected int maxMp;
    protected int atk;
    protected int def;
    protected int mAtk;
    protected int mDef;
    protected int spd;
    protected int ATB;
    protected boolean defeated;
    protected boolean theirTurn;

    protected ArrayList<BossRushStatuses> debuffs = new ArrayList<BossRushStatuses>();
    protected ArrayList<BossRushStatuses> buffs = new ArrayList<BossRushStatuses>();;
    protected ArrayList<BossRushSkills> skills = new ArrayList<BossRushSkills>();


    public String waits() {
        // Waits, character does nothing.
        return String.format("%s waits.", this.getName());
    }
    
    public String attack(BossRushUnit target) {
        // Do calculations for unit first
        double r = Math.pow(1.05 - (Math.random() * 0.15),1.2);
        double a  = Double.valueOf(this.getAtk());
        double d = Double.valueOf(target.getDef());
        int damage = (int) (2*Math.round(Math.pow(a,1.1) * (100/(100+d))) * r);
        String log = String.format("%s attacked %s for %d. ", this.getName(), target.getName(), damage);

        if (target.hasStatus("Magic Guard") != null) {
            damage = (int)(Double.valueOf(damage)*0.2);
        }

        if (target.getHp() - damage  <= 0) {
            target.setDefeated(true);
            target.setHp(0);
    
            log += String.format("%s has been defeated. ", target.getName());
            log += target.onHit(this);
        }
        else {
            target.setHp(target.getHp() - damage);
            log += target.onHit(this); // resolve any effects
        }
        return log;
    }

    public void applyStatuses(BossRushUnit self){
        for (BossRushStatuses d : debuffs) {
            d.onApply(self);

        }
        for (BossRushStatuses b : buffs) {
            b.onApply(self);
        }
    }

    public void triggerStatuses() {
        for (BossRushStatuses d : debuffs) {
            d.triggerEffect(this);

        }
        for (BossRushStatuses b : buffs) {
            b.triggerEffect(this);
        }
    }

    public BossRushStatuses hasStatus(String statusName) {

        for (BossRushStatuses d : debuffs) {
            if (d.getName().equals(statusName)) {
                return d;
            }
        }
        for (BossRushStatuses b : buffs) {
            if (b.getName().equals(statusName)) {
                return b;
            }
        }

        return null;
    }

    public void updateStatuses() {
        for (BossRushStatuses d : debuffs) {
            d.updateStatus(this);
        }
        for (BossRushStatuses b : buffs) {
            b.updateStatus(this);
        }
        int debuffSize = debuffs.size();
        int buffSize = buffs.size();
        for (int i = debuffSize-1; i >= 0; i-- ) {
            if (debuffs.get(i).getTurnsLeft() == 0) 
                debuffs.remove(i);
        }
        for (int i = buffSize-1; i >= 0; i-- ) {
            if (buffs.get(i).getTurnsLeft() == 0)
                buffs.remove(i);
        }
    }

    public abstract String onHit(BossRushUnit from);
    public abstract String useSkill(ArrayList<BossRushUnit> field);
    public abstract String startTurn(String action,ArrayList<BossRushUnit> field);
    public abstract void show(int index);
    public abstract void showStats();
    public abstract void showStatuses();
    public abstract void showSkills();
    public abstract void showResourceBars();
    public abstract void endTurn();

    public void regenMpNaturally(){
        if (!this.isDefeated() && this.hasStatus("Magic Guard") == null)
            this.setMp(Math.min(this.getMaxMp(),this.getMp() + 10));
    }

    public String getName() {
        return this.name;
    }
    public int getHp() {
        return this.hp;
    }
    public int getMp() {
        return this.mp;
    }
    public int getMaxHp() {
        return this.maxHp;
    }
    public int getMaxMp() {
        return this.maxMp;
    }
    public int getAtk() {
        return this.atk;
    }
    public int getDef() {
        return this.def;
    }
    public int getMAtk() {
        return this.mAtk;
    }
    public int getMDef() {
        return this.mDef;
    }
    public int getSpd() {
        return this.spd;
    }
    public String getDescription() {
        return this.desciption;
    }
    public String getDifficulty() {
        return this.difficulty;
    }
    public Boolean isDefeated() {
        return this.defeated;
    }
    public Boolean isTheirTurn() {
        return this.theirTurn;
    }
    public ArrayList<BossRushStatuses> getDebuffs () {
        return this.debuffs;
    }
    public ArrayList<BossRushStatuses> getBuffs() {
        return this.buffs;
    }
    public ArrayList<BossRushSkills> getSkills() {
        return this.skills;
    }
    public int getATB() {
        return this.ATB;
    }
    public void setATB(int ATB) {
        this.ATB = ATB;
    }
    public void setDebuffs (ArrayList<BossRushStatuses> debuffs) {
        this.debuffs = debuffs;
    }
    public void setBuffs (ArrayList<BossRushStatuses> buffs) {
        this.buffs = buffs;
    }
    public void setSkills(ArrayList<BossRushSkills> skills) {
        this.skills = skills;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setMp(int mp) {
        this.mp = mp;
    }
    public void setMaxHp(int hp) {
        this.maxHp = hp;
    }
    public void setMaxMp(int mp) {
        this.maxMp = mp;
    }
    public void setDef(int def) {
        this.def = def;
    }
    public void setAtk(int atk) {
        this.atk = atk;
    }
    public void setMAtk(int mAtk) {
        this.mAtk =  mAtk;
    }
    public void setMDef(int mDef) {
        this.mDef = mDef;
    }
    public void setSpd(int spd) {
        this.spd = spd;
    }
    public void setDescription(String des) {
        this.desciption = des;
    }
    public void setDifficulty(String rank) {
        this.difficulty = rank;
    }
    public void setDefeated(Boolean d) {
        this.defeated = d;
        if (d == true && this.hasStatus("Gambit") == null) {
            for (BossRushStatuses s : this.buffs) {
                s.onTimeOut(this);
            }
            for (BossRushStatuses s : this.debuffs) {
                if (!s.getName().equals("Nightmare")) {
                    s.onTimeOut(this);
                }
            }
            this.buffs.clear();
            this.debuffs.clear();
            this.setATB(this.getSpd());
        }
        if (d == true && this.hasStatus("Gambit") != null) {
            this.defeated = false;
        }
    }
    public void setTurn(Boolean turn) {
        this.theirTurn = turn;
    }
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
    }
}