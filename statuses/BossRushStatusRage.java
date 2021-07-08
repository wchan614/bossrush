package statuses;

import units.*;
public class BossRushStatusRage extends BossRushStatuses {

    public BossRushStatusRage (int turns,int max) {
        setName("Rage");
        setType("Buff");
        setDescription("Unit's ATK+");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);
    }

    public BossRushStatusRage(BossRushStatusRage b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
	}

    public void onApply(BossRushUnit self) {
        self.setAtk(self.getAtk()+50);
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
        self.setAtk(self.getAtk() - 50);
    }

    public BossRushStatuses makeCopy() {
        return new BossRushStatusRage(this);
    }
}
