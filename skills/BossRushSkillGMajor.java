package skills;


import java.util.ArrayList;
import units.BossRushUnit;
import statuses.*;
public class BossRushSkillGMajor extends BossRushSkills {
        
    public BossRushSkillGMajor() {
        super();
        this.setName("G Major");
        this.setType("ATK");
        this.setDescription("Lower HP of target, More damage.");
        this.setHpCost("0");
        this.setMpCost("20");
        this.setPotency("330+");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        BossRushUnit target = selectTargets(caster, field).get(0);
        double r = Math.pow(1.05 - (Math.random() * 0.15),1.2);
        double p = 330;
        double a  = Double.valueOf(caster.getAtk());
        double d = Double.valueOf(target.getDef());
        double targetBonus = (1 + 1.75*((1 - Double.valueOf(target.getHp())/Double.valueOf(target.getMaxHp())))); // 1.5
        int damage = (int) (targetBonus*2*p/100*Math.round(Math.pow(a,1.1) * (100/(100+d))) * r);

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
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return log;
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> target = new ArrayList<BossRushUnit>();
        target.add(field.get(0));
        return target;
    }
}
