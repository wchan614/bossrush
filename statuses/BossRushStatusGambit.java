package statuses;


import units.BossRushUnit;

public class BossRushStatusGambit extends BossRushStatuses {

    public BossRushStatusGambit (int turns,int max) {
        setName("Gambit");
        setType("Buff");
        setDescription("Hp will not drop below 1. ");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }

    public BossRushStatusGambit(BossRushStatusGambit b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
	}

    public void onApply(BossRushUnit self) {
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
        return;
    }

    public BossRushStatuses makeCopy() {
        return new BossRushStatusGambit(this);
    }
}
