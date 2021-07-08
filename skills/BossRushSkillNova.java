package skills;

import units.*;
import java.util.ArrayList;
import statuses.*;
public class BossRushSkillNova extends BossRushSkills {
        
    public BossRushSkillNova() {
        super();
        this.setName("Nova");
        this.setType("MATK");
        this.setDescription("Explosive Blast based on MP. Uses all MP.");
        this.setHpCost("0");
        this.setMpCost("ALL");
        this.setPotency("0+");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        BossRushUnit target = selectTargets(caster, field).get(0);
        double r = Math.pow(1.05 - (Math.random() * 0.15),1.2);
        double p = Double.valueOf(getPotency());
        double mp = Double.valueOf(caster.getMp());
        double ma = Double.valueOf(caster.getMAtk());
        double md = Double.valueOf(target.getMDef());
        double bonusDamage = Math.min(1500, 1.8*mp);
        int damage = (int) (2*bonusDamage/100*Math.round(Math.pow(ma,1.1) * (100/(100+md))) * r);
 
        String log = String.format("%s used %s on %s for %d. ", caster.getName(), this.getName(), target.getName(), damage);
        if (target.getHp() - damage  <= 0) {
            target.setDefeated(true);
            target.setHp(0);
            log += String.format("%s has been defeated. ", target.getName());
        }
        else {
            target.setHp(target.getHp() - damage);
            log += target.onHit(caster); // resolve any effects
        }
        if (caster.hasStatus("Siphon") != null) {
            BossRushStatusSiphon siphon = (BossRushStatusSiphon)caster.hasStatus("Siphon");
            int healing = (int)(Double.valueOf(damage)*0.05);
            siphon.heal(caster,healing);
            log += String.format("%s healed %s for %d. ",caster.getName(), siphon.getFrom(),healing);
        }
        caster.setMp(0);
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return log;
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> target = new ArrayList<BossRushUnit>();
        target.add(field.get(0));
        return target;
    }
}
