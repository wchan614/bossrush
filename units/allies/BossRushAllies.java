package units.allies;

import skills.*;
public class BossRushAllies { //Factory
    
    public BossRushAlly makeAlbert() {
        BossRushAlly ally = new BossRushAlly("Albert");
        ally.setRole("Warrior");
        ally.setDefeated(false);
        ally.setTurn(false);
        ally.setMaxHp(4000);
        ally.setMaxMp(170);
        ally.setHp(4000);
        ally.setMp(170);
        ally.setAtk(600);
        ally.setDef(450);
        ally.setMAtk(150);
        ally.setMDef(150);
        ally.setSpd(88);
        ally.setATB(88);

        ally.getSkills().add(new BossRushSkillFullCleave());
        ally.getSkills().add(new BossRushSkillGambit());
        ally.getSkills().add(new BossRushSkillSunderStrike());
        ally.getSkills().add(new BossRushSkillFirstAid());
        // empowered attack (ATK),SUPERBOLIDE (set hp to 1) cannot be killed for 3 turns , EXECUTE attack (less health him or boss more damage) (ATK), heal target based  on atk stat of albert

        return ally;
    }

    public BossRushAlly makeLifa() {
        BossRushAlly ally = new BossRushAlly("Lifa");
        ally.setRole("Bard");
        ally.setDefeated(false);
        ally.setTurn(false);
        ally.setMaxHp(3250);
        ally.setMaxMp(405);
        ally.setHp(3250);
        ally.setMp(405);
        ally.setAtk(450);
        ally.setDef(125);
        ally.setMAtk(260);
        ally.setMDef(115);
        ally.setSpd(100);
        ally.setATB(100);

        ally.getSkills().add(new BossRushSkillMysticShot());
        ally.getSkills().add(new BossRushSkillRefresh());
        ally.getSkills().add(new BossRushSkillNaturesHyme());
        ally.getSkills().add(new BossRushSkillManaShift());
        // empowered atk mixed damage (atk + matk),  AOE cleanse + spd up  , AOE Regen (20% max) (2) +  MP regen (10% max) (2)self, Mana shift (33% of  max);

        return ally;
    }

    public BossRushAlly makeStorm() {
        BossRushAlly ally = new BossRushAlly("Storm");
        ally.setRole("Mage");
        ally.setDefeated(false);
        ally.setTurn(false);
        ally.setMaxHp(2100);
        ally.setMaxMp(700);
        ally.setHp(2100);
        ally.setMp(700);
        ally.setAtk(100);
        ally.setDef(150);
        ally.setMAtk(630);
        ally.setMDef(500);
        ally.setSpd(85);
        ally.setATB(85);
        // mage attack (MATK), Magic guard (MP regen stops , portion of damage is taken to MP) (3), DEF+ MDEF UP for TEAM(2),  mage attack that checks targets DEF instead of mDEF.
        ally.getSkills().add(new BossRushSkillTeravolt());
        ally.getSkills().add(new BossRushSkillMagicGuard());
        ally.getSkills().add(new BossRushSkillManawall());
        ally.getSkills().add(new BossRushSkillGigavolt());
        return ally;
    }

    public BossRushAlly makeLeo() {
        BossRushAlly ally = new BossRushAlly("Leo");
        ally.setRole("Bard");
        ally.setDefeated(false);
        ally.setTurn(false);
        ally.setMaxHp(2900);
        ally.setMaxMp(300);
        ally.setHp(2900);
        ally.setMp(300);
        ally.setAtk(510);
        ally.setDef(200);
        ally.setMAtk(200);
        ally.setMDef(300);
        ally.setSpd(95);
        ally.setATB(95);

        // DoT  (2) CUR HP MAX%,  MORES DEAD = STRONGER BUFF SELF,  EXECUTE, play song -> people who have less % hp than you, they get def,mdef buff up, if they have equal or more, atk and spd up.
        ally.getSkills().add(new BossRushSkillGMinor());
        ally.getSkills().add(new BossRushSkillGMajor());
        ally.getSkills().add(new BossRushSkillAriaLibra());
        ally.getSkills().add(new BossRushSkillAriaMemoria());
        return ally;
    }

    public BossRushAlly makePetra() {
        BossRushAlly ally = new BossRushAlly("Petra");
        ally.setRole("Shade");
        ally.setDefeated(false);
        ally.setTurn(false);
        ally.setMaxHp(10000);
        ally.setMaxMp(0);
        ally.setHp(10000);
        ally.setMp(0);
        ally.setAtk(720);
        ally.setDef(100);
        ally.setMAtk(0);
        ally.setMDef(100);
        ally.setSpd(77);
        ally.setATB(77);
        
        // Drain restores 15% of damage dealt (checks mDEF), HP shift (33% cur hp.), give team members drain buff to heal him (1), Drain lvl 5  restores 5% of damage dealt (MATK), if hp was a multiple of 5, heal all damage delt. 

        ally.getSkills().add(new BossRushSkillDrain());
        ally.getSkills().add(new BossRushSkillLv5Drain());
        ally.getSkills().add(new BossRushSkillTransfusion());
        ally.getSkills().add(new BossRushSkillSiphon());

        return ally;


    }

    public BossRushAlly makeRhys() {
        BossRushAlly ally = new BossRushAlly("Rhys");
        ally.setRole("Time Mage");
        ally.setDefeated(false);
        ally.setTurn(false);
        ally.setMaxHp(2500);
        ally.setMaxMp(500);
        ally.setHp(2500);
        ally.setMp(500);
        ally.setAtk(200);
        ally.setDef(150);
        ally.setMAtk(605);
        ally.setMDef(450);
        ally.setSpd(118);
        ally.setATB(118);


        ally.getSkills().add(new BossRushSkillAccelerate());
        ally.getSkills().add(new BossRushSkillStop());
        ally.getSkills().add(new BossRushSkillSetRedo());
        ally.getSkills().add(new BossRushSkillTimeDilation());
        return ally;
    }
}
