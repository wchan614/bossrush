package statuses;

import units.BossRushUnit;

public class BossRushStatusVulnerable extends BossRushStatuses {
    
    public BossRushStatusVulnerable(int turns,int max) {
        setName("Vulnerable");
        setType("Buff");
        setDescription("Unit's DEF-, MDEF-");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }
    public BossRushStatusVulnerable(BossRushStatusVulnerable  b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
	}
    public void onApply(BossRushUnit self) {
        self.setDef(self.getDef()-33);
        self.setMDef(self.getMDef()-33);
        return;
    }

    public void triggerEffect(BossRushUnit self) {
        return;
    }

    public void updateStatus(BossRushUnit self) {
        setTurnsLeft(getTurnsLeft()-1);
        if (getTurnsLeft() == 0) {
            onTimeOut(self);
        }
    }
    
    public void reapply(BossRushUnit self) {
        setTurnsLeft(getMaxTurnsAllowed());
    }
    public void onTimeOut(BossRushUnit self) {
        self.setDef(self.getDef()+33);
        self.setMDef(self.getMDef()+33);
    }
    public BossRushStatuses makeCopy() {
        return new BossRushStatusVulnerable(this);
    }
}
