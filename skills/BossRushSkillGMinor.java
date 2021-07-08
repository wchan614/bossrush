package skills;

import java.util.ArrayList;

import statuses.BossRushStatusVexed;
import units.BossRushUnit;

public class BossRushSkillGMinor extends BossRushSkills {
       
    public BossRushSkillGMinor() {
        this.setName("G Minor");
        this.setType("ATK");
        this.setDescription("Applies Vexation");
        this.setHpCost("0");
        this.setMpCost("55");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        BossRushUnit target = field.get(0);
        String log = String.format("%s plays in G Minor. %s was vexed. ", caster.getName(),target.getName());
        
        if (target.hasStatus("Vexed") != null) {
            target.hasStatus("Vexed").reapply(target);
        }
        else {
            BossRushStatusVexed vexed = new BossRushStatusVexed(2,2);
            vexed.onApply(target);
            target.getDebuffs().add(vexed);
        }
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return log;
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }
}
