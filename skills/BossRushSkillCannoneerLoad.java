package skills;

import java.util.ArrayList;
import units.BossRushUnit;

public class BossRushSkillCannoneerLoad extends BossRushSkills {
    public BossRushSkillCannoneerLoad() {
        super();
        this.setName("Load");
        this.setType("SUPPORT");
        this.setDescription("Loads the cannon");
        this.setHpCost("0");
        this.setMpCost("0");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return String.format("%s loads the cannon. ", caster.getName());
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }

}
