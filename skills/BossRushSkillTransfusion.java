package skills;

import java.util.ArrayList;
import units.*;
import java.util.Scanner;
public class BossRushSkillTransfusion extends BossRushSkills {
    public BossRushSkillTransfusion() {
        super();
        this.setName("Transfusion");
        this.setType("SUPPORT");
        this.setDescription("Shifts upto 33% of current HP to an ally. ");
        this.setHpCost("33%");
        this.setMpCost("0");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> t = selectTargets(caster,field);
        if (t == null) return "b";
        BossRushUnit target = t.get(0);

        int hpLost = (int)Math.max(0, caster.getHp()*0.333);
        caster.setHp(caster.getHp() - hpLost);
        target.setHp(Math.min(target.getHp() + hpLost, target.getMaxHp()));
  

        return String.format("%s used Transfusion on %s. %s got %dHP. ",caster.getName(),target.getName(),target.getName(),hpLost);
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> retVal = new ArrayList<BossRushUnit>();
        Scanner input = new Scanner(System.in);
        String selectString = "";
        System.out.printf("Select a target: ");
        ArrayList<Integer> available = new ArrayList<Integer>();

        for (int i = 1 ; i < field.size(); i++ ) {
            BossRushUnit ally = field.get(i);
            if (!ally.isDefeated() && !ally.getName().equals(caster.getName())) {
                available.add(i);
                selectString += i+") " + ally.getName() + "  ";
            }
        }
        System.out.println(selectString);

        String cmd = "";
        while (true) {
            cmd = input.nextLine();
            if (cmd.equals("b"))
                return null;

            try {Integer.valueOf(cmd);}
            catch (Exception e) { continue; }
            if (available.contains(Integer.valueOf(cmd))) {

                switch (cmd) {
                    case "1":
                        retVal.add(field.get(1));
                        return retVal;

                    case "2":
                        retVal.add(field.get(2));
                        return retVal;

                    case "3":
                        retVal.add(field.get(3));
                        return retVal;
                }
            }
        }
    }
}
