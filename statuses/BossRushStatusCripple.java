package statuses;

import units.BossRushUnit;
public class BossRushStatusCripple extends BossRushStatuses {
        
    public BossRushStatusCripple (int turns,int max) {
        setName("Crippled");
        setType("Debuff");
        setDescription("Unit is crippled. Hindering movement.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }

    public BossRushStatusCripple(BossRushStatusCripple b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
	}

    public void onApply(BossRushUnit self) {
        self.setSpd(self.getSpd() - 15);
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
        self.setSpd(self.getSpd() + 15);
    }
    public BossRushStatuses makeCopy() {
        return new BossRushStatusCripple(this);
    }
}
