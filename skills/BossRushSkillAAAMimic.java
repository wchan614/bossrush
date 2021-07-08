package skills;

import java.util.ArrayList;

import statuses.*;
import units.BossRushUnit;

public class BossRushSkillAAAMimic extends BossRushSkills {
    
    public BossRushSkillAAAMimic() {
        super();
        this.setName("Mimic");
        this.setType("ATK");
        this.setDescription("Copies all damage received and reflects it back next turn for the caster.");
        this.setHpCost("0");
        this.setMpCost("0");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster,field);
        String log;
        //mimic
        BossRushStatuses mimic = caster.hasStatus("Mimic");
        if (mimic == null) { // no mimic
            log = String.format("%s uses mimic. %s is ready to reflect your attacks", caster.getName(), this.getName(), caster.getName());
            mimic = new BossRushStatusMimic(2,2);
            mimic.onApply(caster);
            caster.getBuffs().add(mimic);
        }
        else { // has mimic
            int damage = ((BossRushStatusMimic)mimic).getDamage() / targets.size();
            log = String.format("%s mimics back all the attacks.",caster.getName());
            for (BossRushUnit target : targets) {

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
        }
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