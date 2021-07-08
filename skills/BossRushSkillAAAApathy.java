package skills;

import java.util.ArrayList;
import units.BossRushUnit;


public class BossRushSkillAAAApathy extends BossRushSkills {
    
    public BossRushSkillAAAApathy() {
        super();
        this.setName("Apathy");
        this.setType("ATK");
        this.setDescription("Reduces allies turn order and halves mp of allies.");
        this.setHpCost("0");
        this.setMpCost("10");
        this.setPotency("550");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);
        String log = String.format("%s used %s. Everyone lost motivation. ", caster.getName(), this.getName());
        for (BossRushUnit target : targets) {
           target.setATB(target.getATB()/2);
           target.setMp(target.getMp()/2);
    
        }
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
