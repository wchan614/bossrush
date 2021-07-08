package statuses;


import units.BossRushUnit;

public class BossRushStatusQuickened extends BossRushStatuses {
    

    public BossRushStatusQuickened (int turns,int max) {
        setName("Quickened");
        setType("Buff");
        setDescription("Unit is performing actions faster.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }
    public BossRushStatusQuickened(BossRushStatusQuickened b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
	}
    public void onApply(BossRushUnit self) {
        self.setSpd(self.getSpd()+20);
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
        self.setSpd(self.getSpd() - 20);
    }
    public BossRushStatuses makeCopy() {
        return new BossRushStatusQuickened(this);
    }
}
