package skills;

import java.util.ArrayList;

import statuses.BossRushStatusVulnerable;
import units.BossRushUnit;

public class BossRushSkillCannoneerDepletingShot extends BossRushSkills {
    public BossRushSkillCannoneerDepletingShot() {
        super();
        this.setName("Depleting Shot");
        this.setType("MATK");
        this.setDescription("Removes some mp from target.");
        this.setHpCost("0");
        this.setMpCost("5");
        this.setPotency("10");
    }
    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);
        String log = String.format("%s used %s. ", caster.getName(), this.getName());
        for (BossRushUnit target : targets) {
            double r = Math.pow(1.05 - (Math.random() * 0.15),1.2);
            double p = Double.valueOf(getPotency());
            double a  = Double.valueOf(caster.getAtk());
            double d = Double.valueOf(target.getDef());
            int damage = (int) (2*p/100*Math.round(Math.pow(a,1.1) * (100/(100+d))) * r);


            if (target.hasStatus("Magic Guard") != null) {
                damage = (int)(Double.valueOf(damage)*0.2);
            }

            log += String.format("%s took %d. ", target.getName(), damage);
            
            if (target.getHp() - damage  <= 0) {
                if (target.hasStatus("Gambit") == null) {
                    target.setDefeated(true);
                }
                target.setHp(0);
                log += String.format("%s has been defeated. ", target.getName());
                log += target.onHit(target);
            }
            else {
                int mpLost = Math.min(target.getMp()-40,0);
                target.setMp(target.getMp()-mpLost);
                log+= String.format("%s lost %dMP. ",target.getName(),mpLost);
                target.setHp(target.getHp() - damage);
                log += target.onHit(caster); // resolve any effects
            }
        }
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return log;
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> target = new ArrayList<BossRushUnit>();
        for (int i = 1; i < field.size(); i++){
            if (!field.get(i).isDefeated())
                target.add(field.get(i));
        }
        return target;
    }
}
