package skills;

import java.util.ArrayList;

import statuses.*;
import units.BossRushUnit;

public class BossRushSkillRavagerHaste extends BossRushSkills {
    public BossRushSkillRavagerHaste() {
        super();
        this.setName("Refresh");
        this.setType("SUPPORT");
        this.setDescription("Cleanses all allies and grants SPD+");
        this.setHpCost("0");
        this.setMpCost("140");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {

        // spd up
        BossRushStatuses hastened = caster.hasStatus("Hastened");
        if (hastened != null) {
            hastened.reapply(caster);
        }
        else {
            hastened = new BossRushStatusHastened(2, 2);
            hastened.onApply(caster);
            caster.getBuffs().add(hastened);
        }


        return String.format("%s used Haste. %s begins moving much faster ", caster.getName(),caster.getName());
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }
}
