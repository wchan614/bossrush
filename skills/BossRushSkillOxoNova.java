package skills;

import java.util.ArrayList;
import units.BossRushUnit;
public class BossRushSkillOxoNova extends BossRushSkills{
    
    public BossRushSkillOxoNova() {
        super();
        this.setName("Nova");
        this.setType("MATK");
        this.setDescription("Bright burst of cosmic energy");
        this.setHpCost("0");
        this.setMpCost("15");
        this.setPotency("200");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);
        String log = String.format("%s used %s. ", caster.getName(), this.getName());
        for (BossRushUnit target : targets) {
            double r = Math.pow(1.05 - (Math.random() * 0.15),1.2);
            double p = Double.valueOf(getPotency());
            double m  = Double.valueOf(caster.getMAtk());
            double d = Double.valueOf(target.getMDef());
            int damage = (int) (2*p/100*Math.round(Math.pow(m,1.1) * (100/(100+d))) * r);


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
