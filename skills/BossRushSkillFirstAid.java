package skills;

import units.BossRushUnit;
import java.util.ArrayList;
import java.util.Scanner;
public class BossRushSkillFirstAid extends BossRushSkills {
    public BossRushSkillFirstAid() {
        super();
        this.setName("First Aid");
        this.setType("SUPPORT");
        this.setDescription("Restores 1500HP of an ally member.");
        this.setHpCost("0");
        this.setMpCost("45");
        this.setPotency("0");
    }

    public String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> t = selectTargets(caster,field);
        if (t == null) return "b";
        BossRushUnit target = t.get(0);

        caster.setMp(caster.getMp() - getMpCost());
        target.setHp(Math.min(target.getHp()+1500, target.getMaxHp()));
        String who = "themselves";
        if (!target.getName().equals(caster.getName()))
            who = target.getName();

        return String.format("%s used First Aid. %s heals %s for %d. ",caster.getName(),caster.getName(),who,1500);
    }

    public ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field) {
        ArrayList<BossRushUnit> retVal = new ArrayList<BossRushUnit>();
        Scanner input = new Scanner(System.in);
        String selectString = "";
        System.out.printf("Select a target: ");
        ArrayList<Integer> available = new ArrayList<Integer>();

        for (int i = 1 ; i < field.size(); i++ ) {
            BossRushUnit ally = field.get(i);
            if (!ally.isDefeated()) {
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
