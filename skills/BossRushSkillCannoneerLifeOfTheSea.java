package skills;

import java.util.ArrayList;

import statuses.BossRushStatusPerfectArmor;
import statuses.BossRushStatuses;
import units.BossRushUnit;
public class BossRushSkillCannoneerLifeOfTheSea extends BossRushSkills {
    public BossRushSkillCannoneerLifeOfTheSea() {
        super();
        this.setName("Life of the Sea");
        this.setType("SUPPORT");
        this.setDescription("Applies Perfect Armor. ");
        this.setHpCost("0");
        this.setMpCost("0");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {

        // spd up
        BossRushStatuses perfectArmor = caster.hasStatus("Perfect Armor");
        if (perfectArmor != null) {
            perfectArmor.reapply(caster);
        }
        else {
            perfectArmor = new BossRushStatusPerfectArmor(2, 2);
            perfectArmor.onApply(caster);
            caster.getBuffs().add(perfectArmor);
        }


        return String.format("%s used Life of the Sea. %s is nearly indestructable. ", caster.getName(),caster.getName());
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }
}
