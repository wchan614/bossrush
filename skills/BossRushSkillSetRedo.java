package skills;


import java.util.ArrayList;

import statuses.BossRushStatusSet;
import units.BossRushUnit;

public class BossRushSkillSetRedo extends BossRushSkills {

    public BossRushSkillSetRedo() {
        super();
        this.setName("Set/Redo");
        this.setType("SUPPORT");
        this.setDescription("Set state in time. Using again returns unit back to set time.");
        this.setHpCost("0");
        this.setMpCost("25");
        this.setPotency("0");
    }

    
    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        BossRushStatusSet set = (BossRushStatusSet)caster.hasStatus("Set");
        String log = "";
        log += "Time and space has been set. ";


        if (set == null) {
            set = new BossRushStatusSet(caster);
            set.onApply(caster);
            caster.getBuffs().add(set);
            caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
            caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        } 
        else { // reload everything , going back in time
            set.reapply(caster);
        }

        return log;
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }
}
