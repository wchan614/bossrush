package statuses;

import units.BossRushUnit;

public class BossRushStatusMagicGuard extends BossRushStatuses{
    
    public BossRushStatusMagicGuard (int turns,int max) {
        setName("Magic Guard");
        setType("Buff");
        setDescription("MP Regen stops. Damage taken reduced. ");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }

    public BossRushStatusMagicGuard(BossRushStatusMagicGuard b) {
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
        return;
    }
    
    public void reapply(BossRushUnit self) {
        setTurnsLeft(getMaxTurnsAllowed());
    }
    public void onTimeOut(BossRushUnit self) {
        return;
    }
    public BossRushStatuses makeCopy() {
        return new BossRushStatusMagicGuard(this);
    }
}
