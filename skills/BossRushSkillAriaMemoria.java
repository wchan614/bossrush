package skills;

import java.util.ArrayList;
import units.*;
import statuses.*;
public class BossRushSkillAriaMemoria extends BossRushSkills {
    public BossRushSkillAriaMemoria() {
        super();
        this.setName("Aria Memoria");
        this.setType("SUPPORT");
        this.setDescription("More fallen allies = Stronger self buffs.");
        this.setHpCost("0");
        this.setMpCost("130");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        int defeatedCount = countDefeated(field);
        String log = String.format("%s used %s. ", caster.getName(), this.getName());
        if (defeatedCount >= 0) {
            BossRushStatusRegen regen = new BossRushStatusRegen(4, 4);
            caster.getBuffs().add(regen);
            regen.onApply(caster);
            log += String.format("%s evoked Regen. ",caster.getName());
        }
        if (defeatedCount >= 1) {
            BossRushStatusRage rage = new BossRushStatusRage(3, 3);
            caster.getBuffs().add(rage);
            rage.onApply(caster);
            log += String.format("%s evoked Rage. ",caster.getName());
        }
        if (defeatedCount >= 2) {
            BossRushStatusFocus focus = new BossRushStatusFocus(2, 2);
            caster.getBuffs().add(focus);
            focus.onApply(caster);
            log += String.format("%s evoked Focus. ",caster.getName());
        }
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return log;
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }

    public int countDefeated(ArrayList<BossRushUnit> field) {
        int retVal = 0;
        for (int i = 1; i < field.size(); i++) {
            if (field.get(i).isDefeated()) {
                retVal++;
            }
        }
        return retVal;
    }
}