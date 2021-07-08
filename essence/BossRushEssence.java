package essence;

import skills.*;
import units.allies.BossRushAlly;

public class BossRushEssence {
    private String name;
    private String from;
    private int hp;
    private int mp;
    private int atk;
    private int def;
    private int mAtk;
    private int mDef;
    private int spd;
    private BossRushSkills skill;

    public void printInfo() {
        System.out.printf("%s / %dHP / %dMP / %dATK / %dDEF / %dMAtk / %dMDef / %dSpd \n",
                            name,hp,mp,atk,def,mAtk,mDef,spd);
        System.out.printf("Essence Skill: %s / %sHP %sMP / TYPE: %s / POTENCY: %s / %s\n", 
                            skill.getName(),skill.getHpCost(), skill.getMpCost(), skill.getType(), skill.getPotency(), skill.getDescription());
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

    public BossRushSkills getSkill() {
        return this.skill;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setMAtk(int matk){
        this.mAtk = matk;
    }

    public void setMDef(int mdef) {
        this.mDef = mdef;
    }
    
    public void setSpd(int spd) {
        this.spd = spd;
    }

    public void setSkill(BossRushSkills skill) {
        this.skill = skill;
    }

    public void onApply(BossRushAlly ally) {
        ally.setHp(ally.getHp() + this.hp);
        ally.setMp(ally.getMp() + this.mp);
        ally.setMaxHp(ally.getMaxHp() + this.hp);
        ally.setMaxMp(ally.getMaxMp() + this.mp);
        ally.setAtk(ally.getAtk() + this.atk);
        ally.setDef(ally.getDef() + this.def);
        ally.setMAtk(ally.getMAtk() + this.mAtk);
        ally.setMDef(ally.getMDef() + this.mDef);
        ally.setSpd(ally.getSpd() + this.spd);
        ally.getSkills().add(this.skill);
    }

    public void onRemove(BossRushAlly ally) {
        ally.setHp(ally.getHp() - this.hp);
        ally.setMp(ally.getMp() - this.mp);
        ally.setMaxHp(ally.getMaxHp() - this.hp);
        ally.setMaxMp(ally.getMaxMp() - this.mp);
        ally.setAtk(ally.getAtk() - this.atk);
        ally.setDef(ally.getDef() - this.def);
        ally.setMAtk(ally.getMAtk() - this.mAtk);
        ally.setMDef(ally.getMDef() - this.mDef);
        ally.setSpd(ally.getSpd() - this.spd);
        ally.getSkills().remove(ally.getSkills().size() - 1);
    }
}