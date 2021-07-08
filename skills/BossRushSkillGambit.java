package skills;

import java.util.ArrayList;

import statuses.*;
import units.BossRushUnit;
public class BossRushSkillGambit extends BossRushSkills{
    
    public BossRushSkillGambit() {
        super();
        this.setName("Gambit");
        this.setType("SUPPORT");
        this.setDescription("Hp drops to 1. Hp will not drop below 1 for 3 turns.");
        this.setHpCost("0");
        this.setMpCost("80");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {

        BossRushStatuses gambit = caster.hasStatus("Gambit");
        if (gambit != null) {
            gambit.reapply(caster);
        }
        else {
            caster.getBuffs().add(new BossRushStatusGambit(4,4));
        }
        caster.setHp(1);
        caster.setMp(caster.getMp() - getMpCost());
        return String.format("%s used %s. %s readies themselves. ", caster.getName(), getName(), caster.getName());
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }
}
