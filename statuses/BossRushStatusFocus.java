package statuses;

import units.*;
public class BossRushStatusFocus extends BossRushStatuses  {

    public BossRushStatusFocus (int turns,int max) {
        setName("Focus");
        setType("Buff");
        setDescription("Unit goes immediately again.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);
    }

    public BossRushStatusFocus(BossRushStatusFocus b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
	}

    public void onApply(BossRushUnit self) {
        self.setATB(1000);
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
    }

    public BossRushStatuses makeCopy() {
        return new BossRushStatusFocus(this);
    }
}
