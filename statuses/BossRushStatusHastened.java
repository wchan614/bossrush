package statuses;

import units.BossRushUnit;
public class BossRushStatusHastened extends BossRushStatuses {
    public BossRushStatusHastened(int turns,int max) {
        setName("Quickened");
        setType("Buff");
        setDescription("Unit is performing actions faster.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }
    public BossRushStatusHastened(BossRushStatusHastened b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
	}

    public void onApply(BossRushUnit self) {
        self.setSpd(self.getSpd()+70);
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
        self.setSpd(self.getSpd() - 70);
    }

    public BossRushStatuses makeCopy() {
        return new BossRushStatusHastened(this);
    }
}
