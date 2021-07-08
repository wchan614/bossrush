package skills;

import units.*;
import java.util.ArrayList;

public class BossRushSkillDrain extends BossRushSkills {
    public BossRushSkillDrain() {
        super();
        this.setName("Drain");
        this.setType("ATK");
        this.setDescription("Recovers 20% of damage dealt to HP.");
        this.setHpCost("1000");
        this.setMpCost("0");
        this.setPotency("250");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));

        BossRushUnit target = selectTargets(caster, field).get(0);
        double r = Math.pow(1.05 - (Math.random() * 0.15),1.2);
        double p = Double.valueOf(getPotency());
        double a  = Double.valueOf(caster.getAtk());
        double d = Double.valueOf(target.getDef());
        int damage = (int) (2*p/100*Math.round(Math.pow(a,1.1) * (100/(100+d))) * r);
        int recovery = (int) (Double.valueOf(damage) * 0.20);

        String log = String.format("%s used %s on %s for %d. ", caster.getName(), this.getName(), target.getName(), damage);
        log += String.format("%s heals for %d. ", caster.getName(), recovery);
        if (target.getHp() - damage  <= 0) {
            target.setDefeated(true);
            target.setHp(0);
            log += String.format("%s has been defeated. ", target.getName());
        }
        else {
            target.setHp(target.getHp() - damage);
            log += target.onHit(caster); // resolve any effects
        }
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        caster.setHp(Math.min(caster.getMaxHp(), caster.getHp()+recovery));
        return log;
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> target = new ArrayList<BossRushUnit>();
        target.add(field.get(0));
        return target;
    }
    
}
