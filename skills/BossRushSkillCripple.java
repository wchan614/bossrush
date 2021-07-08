package skills;

import java.util.ArrayList;

import statuses.BossRushStatusCripple;
import statuses.BossRushStatusSiphon;
import statuses.BossRushStatuses;
import units.BossRushUnit;

public class BossRushSkillCripple extends BossRushSkills {
            
    public BossRushSkillCripple() {
        super();
        this.setName("Cripple");
        this.setType("ATK");
        this.setDescription("Slash aimed at the legs. Applies SPD-");
        this.setHpCost("0");
        this.setMpCost("33");
        this.setPotency("350");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        BossRushUnit target = selectTargets(caster, field).get(0);
        double r = Math.pow(1.05 - (Math.random() * 0.15),1.2);
        double p = Double.valueOf(getPotency());
        double ma = Double.valueOf(caster.getMAtk());
        double md = Double.valueOf(target.getMDef());
        int damage = (int) (2*p/100*Math.round(Math.pow(ma,1.1) * (100/(100+md))) * r);
 
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
        BossRushStatuses cripple = target.hasStatus("Cripple");
        if (cripple == null) {
            cripple = new BossRushStatusCripple(1,1);
            target.getDebuffs().add(cripple);
            cripple.onApply(target);
        }
        if (caster.hasStatus("Siphon") != null) {
            BossRushStatusSiphon siphon = (BossRushStatusSiphon)caster.hasStatus("Siphon");
            int healing = (int)(Double.valueOf(damage)*0.05);
            siphon.heal(caster,healing);
            log += String.format("%s healed %s for %d. ",caster.getName(), siphon.getFrom(),healing);
        }
        caster.setMp(caster.getMp()-getMpCost());
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return log;
    }
    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> target = new ArrayList<BossRushUnit>();
        target.add(field.get(0));
        return target;
    }
}
