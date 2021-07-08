package statuses;

import units.BossRushUnit;

public class BossRushStatusLifeOfTheSea extends BossRushStatuses {
    
    public BossRushStatusLifeOfTheSea (int turns,int max) {
        setName("Life Of The Sea");
        setType("Buff");
        setDescription("Increases all stats.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);
    }

    public BossRushStatusLifeOfTheSea(BossRushStatusLifeOfTheSea b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
	}

    public void onApply(BossRushUnit self) {
        self.setAtk(self.getAtk()+30);
        self.setDef(self.getDef()+30);
        self.setMAtk(self.getMAtk()+30);
        self.setMDef(self.getMDef()+30);
        self.setSpd(self.getSpd()+30);
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
        self.setAtk(self.getAtk()-30);
        self.setDef(self.getDef()-30);
        self.setMAtk(self.getMAtk()-30);
        self.setMDef(self.getMDef()-30);
        self.setSpd(self.getSpd()-30);
    }

    public BossRushStatuses makeCopy() {
        return new BossRushStatusLifeOfTheSea(this);
    }
}
