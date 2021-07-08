package skills;

import units.*;
import java.util.ArrayList;
import statuses.*;
public class BossRushSkillSiphon extends BossRushSkills {
    public BossRushSkillSiphon() {
        super();
        this.setName("Siphon");
        this.setType("SUPPORT");
        this.setDescription("5% of damage dealt from allies will heal user.");
        this.setHpCost("20%");
        this.setMpCost("0");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);

        for (BossRushUnit t : targets) {
            BossRushStatusSiphon siphon;
            if (t.hasStatus("Siphon") != null) {
                t.hasStatus("Siphon").reapply(t);
            }
            else {
                siphon = new BossRushStatusSiphon(1,1,caster);
                t.getBuffs().add(siphon);
                siphon.onApply(t);
            }
        }

        caster.setHp(Math.max(1,(int) (Double.valueOf(caster.getHp())*0.8)));
        return String.format("%s used Siphon. All damage dealt can now heal %s. ", caster.getName(),caster.getName());
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> target = new ArrayList<BossRushUnit>();
        for (int i = 1; i < field.size(); i++){
            if (!field.get(i).isDefeated() && !field.get(i).getName().equals(caster.getName())){
                target.add(field.get(i));
            }
        }
        return target;
    }
}
