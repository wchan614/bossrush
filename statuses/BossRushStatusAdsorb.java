package statuses;

import units.BossRushUnit;
public class BossRushStatusAdsorb extends BossRushStatuses  {
        
    public BossRushStatusAdsorb(int turns,int max) {
        setName("Adsorb");
        setType("Buff");
        setDescription("Unit regenerates 40MP per turn.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }

    public BossRushStatusAdsorb(BossRushStatusAdsorb b) {
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
        double newMp = Math.min(self.getMaxMp(),  self.getMp() + 40);
        self.setMp((int) newMp);
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
        return new BossRushStatusAdsorb(this);
    }
}
