package skills;

import java.util.ArrayList;

import statuses.BossRushStatusPerfectArmor;
import statuses.BossRushStatuses;
import units.BossRushUnit;

public class BossRushSkillLifeOfTheSea extends BossRushSkills {
    public BossRushSkillLifeOfTheSea() {
        super();
        this.setName("Life of the Sea");
        this.setType("SUPPORT");
        this.setDescription("Applies Perfect Armor. ");
        this.setHpCost("0");
        this.setMpCost("50");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {

        // perfectArmor
        BossRushStatuses perfectArmor = caster.hasStatus("Perfect Armor");
        if (perfectArmor != null) {
            perfectArmor.reapply(caster);
        }
        else {
            perfectArmor = new BossRushStatusPerfectArmor(2, 2);
            perfectArmor.onApply(caster);
            caster.getBuffs().add(perfectArmor);
        }

        // Life of the sea
        BossRushStatuses lifeOfTheSea = caster.hasStatus("Life Of The Sea");
        if (lifeOfTheSea != null) {
            lifeOfTheSea.reapply(caster);
        }
        else {
            lifeOfTheSea = new BossRushStatusPerfectArmor(3, 3);
            lifeOfTheSea.onApply(caster);
            caster.getBuffs().add(lifeOfTheSea);
        }



        return String.format("%s used Life of the Sea. %s has gained strength and defence. ", caster.getName(),caster.getName());
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }
}
