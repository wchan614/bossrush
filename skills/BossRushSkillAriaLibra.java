package skills;

import java.util.ArrayList;
import units.*;
import statuses.*;

public class BossRushSkillAriaLibra extends BossRushSkills {
    public BossRushSkillAriaLibra() {
        super();
        this.setName("Aria Libra");
        this.setType("SUPPORT");
        this.setDescription("Allies with less %HP, DEF+ MDEF+. Allies with more %HP, ATK+ SPD+. ");
        this.setHpCost("0");
        this.setMpCost("45");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> targets = selectTargets(caster, field);
        String log = "";
        double casterHpRatio = Double.valueOf(caster.getHp())/Double.valueOf(caster.getMaxHp());
        for (BossRushUnit t : targets) {
            double targetHpRatio = Double.valueOf(t.getHp())/Double.valueOf(t.getMaxHp());
            BossRushStatusLibra libra;

            if (t.hasStatus("Libra >") != null || t.hasStatus("Libra <") != null) {
                if (t.hasStatus("Libra >") != null) {   
                    t.hasStatus("Libra >").reapply(t);
                }
                else {
                    t.hasStatus("Libra <").reapply(t);
                }
            }
            else {
                if (targetHpRatio >= casterHpRatio) { // give Atk and spd
                    libra = new BossRushStatusLibra(4,4,">");
                }
                else {
                    libra = new BossRushStatusLibra(4,4,"<");
                }
    
                t.getBuffs().add(libra);
                libra.onApply(t);
            }


        }
        log += String.format("%s used %s. Allies have been empowered. ", caster.getName(), getName() );
        caster.setMp(Math.max(0,caster.getMp()-this.getMpCost()));
        caster.setHp(Math.max(0,caster.getHp()-this.getHpCost()));
        return log;
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