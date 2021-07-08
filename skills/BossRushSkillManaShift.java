package skills;

import units.BossRushUnit;
import java.util.ArrayList;
import java.util.Scanner;
public class BossRushSkillManaShift extends BossRushSkills {
    public BossRushSkillManaShift() {
        super();
        this.setName("Manashift");
        this.setType("SUPPORT");
        this.setDescription("Shifts upto 33% of Max MP to an ally. ");
        this.setHpCost("0");
        this.setMpCost("33%");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> t = selectTargets(caster,field);
        if (t == null) return "b";
        BossRushUnit target = t.get(0);

        double mp = caster.getMp();
        int mpLost = (int)Math.min(mp, caster.getMaxMp()*0.333);
        caster.setMp(caster.getMp() - mpLost);
        target.setMp(Math.min(target.getMp()+mpLost, target.getMaxMp()));
  

        return String.format("%s used Manashift on %s. %s got %dMP. ",caster.getName(),target.getName(),target.getName(),mpLost);
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
