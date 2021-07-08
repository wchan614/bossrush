package skills;

import java.util.ArrayList;

import statuses.*;
import units.BossRushUnit;

public class BossRushSkillRefresh extends BossRushSkills{
    public BossRushSkillRefresh() {
        super();
        this.setName("Refresh");
        this.setType("SUPPORT");
        this.setDescription("Cleanses all allies and grants SPD+");
        this.setHpCost("0");
        this.setMpCost("100");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);

        for (BossRushUnit t : targets) {
            //cleanse
            ArrayList<BossRushStatuses> debuffs = t.getDebuffs();
            for (BossRushStatuses debuff : debuffs) {
                if (!debuff.getName().equals("Nightmare")) {
                    debuff.onTimeOut(t);
                }
            }
            t.getDebuffs().clear();

            // spd up
            BossRushStatuses quickened = t.hasStatus("Quickened");
            if (quickened != null) {
                quickened.reapply(t);
            }
            else {
                BossRushStatusQuickened buff = t.getName().equals(caster.getName()) ? 
                    new BossRushStatusQuickened(3, 3):
                    new BossRushStatusQuickened(2, 2);
                t.getBuffs().add(buff);
                buff.onApply(t);
            }

        }
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return String.format("%s used Refresh. All allies were cured. All allies were quickened. ", caster.getName());
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
