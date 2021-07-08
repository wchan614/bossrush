package skills;

import java.util.ArrayList;

import statuses.*;
import units.BossRushUnit;

public class BossRushSkillNaturesHyme extends BossRushSkills{
    public BossRushSkillNaturesHyme() {
        super();
        this.setName("Nature's Hyme");
        this.setType("SUPPORT");
        this.setDescription("Grants HP regen to all allies. Grants MP regen to self. ");
        this.setHpCost("0");
        this.setMpCost("65");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);

        // REGEN
        for (BossRushUnit t : targets) {
            BossRushStatuses naturesHyme = t.hasStatus("Regen");
            if (naturesHyme != null) {
                naturesHyme.reapply(t);
            }
            else {
                BossRushStatusRegen buff = t.getName().equals(caster.getName()) ? 
                new BossRushStatusRegen(3, 3):
                new BossRushStatusRegen(2, 2);
                t.getBuffs().add(buff);
                buff.onApply(t);
            }

        }
        // ADSORB
        BossRushStatuses adsorb = caster.hasStatus("Adsorb");
        if (adsorb != null ) {
            adsorb.reapply(caster);
        }
        else {
            BossRushStatusAdsorb buff = new BossRushStatusAdsorb(4,4);
            caster.getBuffs().add(buff);
            buff.onApply(caster);
        }
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return String.format("%s used Nature's Hyme.  Allies are becoming healthier.", caster.getName());
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
