package skills;


import java.util.ArrayList;
import units.BossRushUnit;
import statuses.*;
public class BossRushSkillMagicGuard extends BossRushSkills { 
    public BossRushSkillMagicGuard() {
        super();
        this.setName("Magic Guard");
        this.setType("SUPPORT");
        this.setDescription("Mana regen stops. Reduce damage taken by 80% for 2 turns.");
        this.setHpCost("0");
        this.setMpCost("40");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        BossRushStatusMagicGuard buff = new BossRushStatusMagicGuard(3,3);

        BossRushStatuses magicGuard = caster.hasStatus("Magic Guard");
        if (magicGuard != null) {
            magicGuard.reapply(caster);
        }
        else {
            caster.getBuffs().add(buff);
            buff.onApply(caster);
        }

        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return String.format("%s casts Magic Guard. ",caster.getName());
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        return null;
    }
}
