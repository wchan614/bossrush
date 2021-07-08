package skills;

import java.util.ArrayList;
import units.BossRushUnit;

public class BossRushSkillCannoneerPrime extends BossRushSkills {
    public BossRushSkillCannoneerPrime() {
        super();
        this.setName("Prime");
        this.setType("SUPPORT");
        this.setDescription("Readies the cannon");
        this.setHpCost("0");
        this.setMpCost("0");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return String.format("%s readies the cannon. ", caster.getName());
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }

}
