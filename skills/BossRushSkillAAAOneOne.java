package skills;

import java.util.ArrayList;

import units.BossRushUnit;

public class BossRushSkillAAAOneOne extends BossRushSkills {
    public BossRushSkillAAAOneOne() {
        super();
        this.setName("1 - 1");
        this.setType("ATK");
        this.setDescription("Targets hp and mp drops to 1.");
        this.setHpCost("0");
        this.setMpCost("0");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);
        String log = String.format("%s uses one-one.", caster.getName(), this.getName());

        for (BossRushUnit target : targets ) {
            target.setHp(1);
            target.setMp(1);
            log += String.format("%s HP and MP drops to 1.", target.getName());
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
