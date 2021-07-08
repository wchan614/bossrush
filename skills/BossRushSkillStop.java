package skills;

import units.BossRushUnit;
import java.util.ArrayList;

public class BossRushSkillStop extends BossRushSkills {
    public BossRushSkillStop() {
        super();
        this.setName("Stop");
        this.setType("ATK");
        this.setDescription("Moderate chance to skip target's next turn.");
        this.setHpCost("0");
        this.setMpCost("330");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        BossRushUnit target = field.get(0);
        double r = Math.random();
        String log = "";
        log += String.format("%s used %s. ", caster.getName(), getName());
        if (r < 0.75) {
            target.setATB(1);
        }
        else {
            log += " But it failed.";
        }


        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return log;
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }
    
    
}
