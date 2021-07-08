package skills;

import java.util.ArrayList;


import statuses.*;
import units.BossRushUnit;
public class BossRushSkillManawall extends BossRushSkills {
    public BossRushSkillManawall() {
        super();
        this.setName("Manawall");
        this.setType("SUPPORT");
        this.setDescription("Increases DEF and MDEF for all allies for 5 turns. ");
        this.setHpCost("0");
        this.setMpCost("300");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);

        for (BossRushUnit t : targets) {
            BossRushStatuses manawall = t.hasStatus("Manawall");
            if (manawall != null) {
                manawall.reapply(t);
            }
            else {
                BossRushStatusManawall buff = t.getName().equals(caster.getName()) ? 
                    new BossRushStatusManawall(6, 6):
                    new BossRushStatusManawall(5, 5);
                t.getBuffs().add(buff);
                buff.onApply(t);
            }
        }
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return String.format("%s used Manawall. A thin veil protects your allies.", caster.getName());
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> target = new ArrayList<BossRushUnit>();
        for (int i = 1; i < field.size(); i++){
            if (!field.get(i).isDefeated()){
                target.add(field.get(i));
            }
        }
        return target;
    }
}
