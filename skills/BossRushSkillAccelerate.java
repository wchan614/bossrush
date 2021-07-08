package skills;


import java.util.ArrayList;
import units.BossRushUnit;
import statuses.*;


public class BossRushSkillAccelerate extends BossRushSkills {
    public BossRushSkillAccelerate() {
        super();
        this.setName("Accelerate");
        this.setType("MATK");
        this.setDescription("Chance of SPD+");
        this.setHpCost("0");
        this.setMpCost("70");
        this.setPotency("300");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        BossRushUnit target = field.get(0);
        double spdProc = Math.random();
        double r = Math.pow(1.05 - (Math.random() * 0.15),1.2);
        double p = Double.valueOf(getPotency());
        double ma = Double.valueOf(caster.getMAtk());
        double md = Double.valueOf(target.getMDef());
        int damage = (int) (2*p/100*Math.round(Math.pow(ma,1.1) * (100/(100+md))) * r);
        String log = "";
        log += String.format("%s used %s. ", caster.getName(), getName());

        if (spdProc < 0.33) {// proc successful
            if (caster.hasStatus("Quickened") != null) {
                caster.hasStatus("Quickened").reapply(caster);
            }
            else {
                BossRushStatusQuickened quickened = new BossRushStatusQuickened(2,2);
                caster.getBuffs().add(quickened);
                quickened.onApply(caster);
            }
        }
        
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

        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));


        return log;
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }
    
    
}