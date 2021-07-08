package skills;

import java.util.ArrayList;

import statuses.BossRushStatusBurn;
import statuses.BossRushStatusCripple;
import statuses.BossRushStatusVulnerable;
import units.BossRushUnit;
public class BossRushSkillCannoneerBazooka extends BossRushSkills {
       
    public BossRushSkillCannoneerBazooka() {
        super();
        this.setName("Bazooka");
        this.setType("ATK");
        this.setDescription("Large explosion that inflicts many debuffs.");
        this.setHpCost("0");
        this.setMpCost("5");
        this.setPotency("666");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);
        String log = String.format("%s explodes everyone with %s. ", caster.getName(), this.getName());
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
                target.setHp(target.getHp() - damage);
                if (target.hasStatus("Vulnerable") != null) {
                    target.hasStatus("Vulnerable").reapply(target);
                } 
                else {
                    BossRushStatusVulnerable vuln = new BossRushStatusVulnerable(2, 2);
                    target.getDebuffs().add(vuln);
                    vuln.onApply(target);
                }
                if (target.hasStatus("Cripple") != null) {
                    target.hasStatus("Cripple").reapply(target);
                } 
                else {
                    BossRushStatusCripple cripple = new BossRushStatusCripple(2, 2);
                    target.getDebuffs().add(cripple);
                    cripple.onApply(target);
                }
                if (target.hasStatus("Burn") != null) {
                    target.hasStatus("Burn").reapply(target);
                } 
                else {
                    BossRushStatusBurn burn = new BossRushStatusBurn(3,3);
                    target.getDebuffs().add(burn);
                    burn.onApply(target);
                }

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