package skills;

import java.util.ArrayList;

import statuses.*;
import units.*;
public class BossRushSkillAAAOrdeal extends BossRushSkills {
    public BossRushSkillAAAOrdeal() {
        super();
        this.setName("Ordeal");
        this.setType("MATK");
        this.setDescription("Massive spell that inflicts large damage and debuffs.");
        this.setHpCost("0");
        this.setMpCost("10");
        this.setPotency("300");
    }
    
    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);
        String log = String.format("%s's wrath grows grower. %s's attack increases. Everyone is engulfed in darkness. ", caster.getName(), caster.getName());

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
                    BossRushStatusVulnerable vuln = new BossRushStatusVulnerable(5, 5);
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
                if (target.hasStatus("Vexed") != null) {
                    target.hasStatus("Vexed").reapply(target);
                } 
                else {
                    BossRushStatusVexed vexed = new BossRushStatusVexed(1,1);
                    target.getDebuffs().add(vexed);
                    vexed.onApply(target);
                }

                log += target.onHit(caster); // resolve any effects
            }
        }
        caster.setAtk(caster.getAtk()+30);
        caster.setMAtk(caster.getMAtk()+30);
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
