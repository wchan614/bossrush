package statuses;

import units.allies.*;
import units.*;
import java.util.ArrayList;
import statuses.*;

public class BossRushStatusSet extends BossRushStatuses {
    private int hp;
    private int mp;
    private int atk;
    private int def;
    private int mdef;
    private int matk;
    private int spd;
    private ArrayList<BossRushStatuses> debuffs = new ArrayList<BossRushStatuses>();
    private ArrayList<BossRushStatuses> buffs = new ArrayList<BossRushStatuses>();

    public BossRushStatusSet(BossRushUnit caster) {
        setName("Set");
        setType("Buff");
        setDescription("Time has been set.");
        setTurnsLeft(99);
        setMaxTurnsAllowed(99);

        hp = caster.getHp();
        mp = caster.getMp();
        atk = caster.getAtk();
        def = caster.getDef();
        mdef = caster.getMDef();
        matk = caster.getMAtk();
        spd = caster.getSpd();

        discriminate(caster);


    }

    public BossRushStatusSet(BossRushStatusSet s) {
        this.mp = s.mp;
        this.hp = s.hp;
        this.atk = s.atk;
        this.def = s.def;
        this.matk = s.matk;
        this.mdef = s.mdef;
        this.spd = s.spd;

        this.debuffs = s.debuffs;
        this.buffs = s.buffs;
    }

    public void onApply(BossRushUnit self) {
        return;
    }

    public void triggerEffect(BossRushUnit self) {
      return;
    }

    public void updateStatus(BossRushUnit self) {
        if (getTurnsLeft() == 0) {
            onTimeOut(self);
        }
    }
    
    public void reapply(BossRushUnit self) {
        this.turnsLeft = 0;
        updateStatus(self);
        self.setHp(this.hp);
        self.setMp(this.mp);
        self.setAtk(this.atk);
        self.setDef(this.def);
        self.setMAtk(this.matk);
        self.setMDef(this.mdef);
        self.setSpd(this.spd);
        self.setBuffs(this.buffs);
        self.setDebuffs(this.debuffs);

        return;
    }
    public void onTimeOut(BossRushUnit self) {
        return;
    }

    public void discriminate(BossRushUnit caster){
        for (BossRushStatuses b : caster.getBuffs()) {
            buffs.add(b.makeCopy());
        }
        for (BossRushStatuses d : caster.getDebuffs()) {
            debuffs.add(d.makeCopy());
        }
    }

    public BossRushStatuses makeCopy() {
        return new BossRushStatusSet(this);
    }
}
