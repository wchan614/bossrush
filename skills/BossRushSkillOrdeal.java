package skills;

import units.*;
import java.util.ArrayList;
import statuses.*;
public class BossRushSkillOrdeal extends BossRushSkills {
    public BossRushSkillOrdeal() {
        super();
        this.setName("Ordeal");
        this.setType("SUPPORT");
        this.setDescription("The wrath of AAA no longer lingers. Something good will happen.");
        this.setHpCost("0");
        this.setMpCost("20");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);

        for (BossRushUnit t : targets) {
            //cleanse
            ArrayList<BossRushStatuses> debuffs = t.getDebuffs();
            for (BossRushStatuses debuff : debuffs) {
                debuff.onTimeOut(t);
            }
            t.getDebuffs().clear();

            // heal
            t.setHp(Math.max(t.getHp()+4000,t.getMaxHp()));


        }
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return String.format("%s used Ordeal. All allies were cured and healthy. ", caster.getName());
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
