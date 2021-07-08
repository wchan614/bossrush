package units.bosses;

import java.util.ArrayList;
import skills.*;
public class BossRushBosses { //factory
    
    public ArrayList<BossRushBoss> makeBosses(int n) {
        return null;
    }

    public BossRushBoss makeBossOxo() {
        BossRushBoss boss = new BossRushOxo();
        boss.setDescription("Dimensional Entity");
        boss.setDifficulty("D");
        boss.setDefeated(false);
        boss.setTurn(false);
        boss.setMaxHp(13500); //14000
        boss.setMaxMp(20000);
        boss.setHp(13500);
        boss.setMp(20000);
        boss.setAtk(300);
        boss.setDef(300);
        boss.setMAtk(300);
        boss.setMDef(450);
        boss.setSpd(85);
        boss.setATB(85);
        
        boss.getSkills().add(new BossRushSkillOxoNova());
        //  Add skills

        return boss;
    }

    public BossRushBoss makeBossRavager() {
        BossRushBoss boss = new BossRushRavager();
        boss.setDescription("Crimson Defender");
        boss.setDifficulty("C");
        boss.setDefeated(false);
        boss.setTurn(false);
        boss.setMaxHp(13000); //12666
        boss.setMaxMp(4000);
        boss.setHp(13000); //12666
        boss.setMp(4000);
        boss.setAtk(450);
        boss.setDef(250);
        boss.setMAtk(100);
        boss.setMDef(400);
        boss.setSpd(100);
        boss.setATB(100);

        boss.getSkills().add(new BossRushSkillRavagerCripple());
        boss.getSkills().add(new BossRushSkillRavagerDemolish());
        boss.getSkills().add(new BossRushSkillRavagerHaste());
        return boss;
    }

    
    public BossRushBoss makeBossCannoneer() {
        BossRushBoss boss = new BossRushCannoneer();
        boss.setDescription("Lock and loaded.");
        boss.setDifficulty("B");
        boss.setDefeated(false);
        boss.setTurn(false);
        boss.setMaxHp(45000); //32000
        boss.setMaxMp(4000);
        boss.setHp(45000); //32000
        boss.setMp(4000);
        boss.setAtk(340);
        boss.setDef(120);
        boss.setMAtk(340);
        boss.setMDef(120);
        boss.setSpd(130);
        boss.setATB(130);

        boss.getSkills().add(new BossRushSkillCannoneerPrime());
        boss.getSkills().add(new BossRushSkillCannoneerLoad());
        boss.getSkills().add(new BossRushSkillCannoneerBlast());
        boss.getSkills().add(new BossRushSkillCannoneerBazooka());
        boss.getSkills().add(new BossRushSkillCannoneerLifeOfTheSea());
        boss.getSkills().add(new BossRushSkillCannoneerDepletingShot());

        return boss;
    }

    public BossRushBoss makeBossAAA() {
        BossRushBoss boss = new BossRushAAA();
        boss.setDescription("Battle Voice");
        boss.setDifficulty("A");
        boss.setDefeated(false);
        boss.setTurn(false);
        boss.setMaxHp(55000); //14000
        boss.setMaxMp(60000);
        boss.setHp(55000);
        boss.setMp(60000);
        boss.setAtk(450);
        boss.setDef(100);
        boss.setMAtk(300);
        boss.setMDef(650);
        boss.setSpd(125);
        boss.setATB(500);
        
        boss.getSkills().add(new BossRushSkillAAANightmare());
        boss.getSkills().add(new BossRushSkillAAAMimic());
        boss.getSkills().add(new BossRushSkillAAAApathy());
        boss.getSkills().add(new BossRushSkillAAAOrdeal());
        boss.getSkills().add(new BossRushSkillAAAOneOne());
        //  Add skills

        return boss;
    }



}
