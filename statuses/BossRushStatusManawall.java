package statuses;

import units.*;

public class BossRushStatusManawall extends BossRushStatuses {
    
    public BossRushStatusManawall (int turns,int max) {
        super();
        setName("Manawall");
        setType("Buff");
        setDescription("Def, MDef increased.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }
    public BossRushStatusManawall(BossRushStatusManawall b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
	}
    public void onApply(BossRushUnit self) {
        self.setDef(self.getDef() + 100);
        self.setMDef(self.getMDef() + 100);
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
        self.setDef(self.getDef() - 100);
        self.setMDef(self.getMDef() - 100);
    }
    public BossRushStatuses makeCopy() {
        return new BossRushStatusManawall(this);
    }
}
