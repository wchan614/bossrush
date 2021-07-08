package essence;

import skills.*;

public class BossRushEssences {
    
    public BossRushEssences() {}

    public BossRushEssence makeEssence(int boss) {
        BossRushEssence e = new BossRushEssence();
        
        switch (boss) {
            case 0: //oxo
                e.setName("Oxo Essence");
                e.setFrom("Oxo");
                e.setAtk(30);
                e.setDef(30);
                e.setMAtk(30);
                e.setMDef(30);
                e.setHp(300);
                e.setMp(300);
                e.setSpd(10);
                e.setSkill(new BossRushSkillNova());
                break;

            case 1:
                e.setName("Ravager Essence");
                e.setFrom("Ravager");
                e.setAtk(20);
                e.setDef(40);
                e.setMAtk(10);
                e.setMDef(50);
                e.setHp(0);
                e.setMp(0);
                e.setSpd(20);
                e.setSkill(new BossRushSkillCripple());
                break;

            case 2:
                e.setName("Canonneer Essence");
                e.setFrom("Canonneer");
                e.setAtk(45);
                e.setDef(45);
                e.setMAtk(45);
                e.setMDef(45);
                e.setHp(450);
                e.setMp(450);
                e.setSpd(15);
                e.setSkill(new BossRushSkillLifeOfTheSea());
                break;

            case 3:
            e.setName("AAA Essence");
            e.setFrom("AAA");
            e.setAtk(200);
            e.setDef(200);
            e.setMAtk(200);
            e.setMDef(200);
            e.setHp(500);
            e.setMp(500);
            e.setSpd(80);
            e.setSkill(new BossRushSkillOrdeal());
            break;
        }
        
        return e;
    }
}
