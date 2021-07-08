package statuses;

import units.BossRushUnit;

public class BossRushStatusPerfectArmor extends BossRushStatuses {
    public BossRushStatusPerfectArmor (int turns,int max) {
        setName("Perfect Armor");
        setType("Buff");
        setDescription("Unit takes significantly reduced damage.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }
    public BossRushStatusPerfectArmor(BossRushStatusPerfectArmor b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
	}
    public void onApply(BossRushUnit self) {
        self.setDef(self.getDef()+400000);
        self.setMDef(self.getMDef()+400000);
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
        self.setDef(self.getDef()-400000);
        self.setMDef(self.getMDef()-400000);
    }
    public BossRushStatuses makeCopy() {
        return new BossRushStatusPerfectArmor(this);
    }
}
