package skills;

import java.util.ArrayList;

import statuses.BossRushStatusCripple;
import statuses.BossRushStatusNightmare;
import statuses.BossRushStatuses;
import units.BossRushUnit;

public class BossRushSkillAAANightmare extends BossRushSkills {
    public BossRushSkillAAANightmare() {
        super();
        this.setName("Nightmare");
        this.setType("ATK");
        this.setDescription("Nightmares ensues.");
        this.setHpCost("0");
        this.setMpCost("0");
        this.setPotency("100");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);
        String log = String.format("%s casts %s. Everyone has been cursed. ", caster.getName(), this.getName());

        for (BossRushUnit target : targets ) {
            target.setATB(target.getATB()-200);

            // cripple  
            BossRushStatuses cripple = target.hasStatus("Cripple");
            if (cripple != null) {
                cripple.reapply(target);
            }
            else {
                cripple = new BossRushStatusCripple(3,3);
                cripple.onApply(target);
                target.getDebuffs().add(cripple);
            }
        
            // nightmare
            BossRushStatuses nightmare = target.hasStatus("Nightmare");
            if (nightmare != null) {
                nightmare.reapply(target);
            }
            else {
                nightmare = new BossRushStatusNightmare(7,7);
                nightmare.onApply(caster);
                target.getDebuffs().add(nightmare);
            }
        }
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return log;
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> target = new ArrayList<BossRushUnit>();
        for (int i = 1; i < field.size(); i++){
            if (!field.get(i).isDefeated())
                target.add(field.get(i));
        }
        return target;
    }
}

