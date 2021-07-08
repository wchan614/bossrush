package statuses;

import units.*;
public class BossRushStatusBurn extends BossRushStatuses {
    public BossRushStatusBurn(int turns,int max) {
        setName("Burn");
        setType("Debuff");
        setDescription("Unit loses 10% current HP per turn.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }

    public BossRushStatusBurn(BossRushStatusBurn b) {
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
        double newHp;
        if (self.hasStatus("Gambit") == null) {
            newHp = Math.max(0,  self.getHp()*0.9);

        }
        else {
            newHp = Math.max(1, self.getHp()*0.9);
        }
        self.setHp((int) Math.ceil(newHp));
        if (self.getHp() == 0) {
            self.setDefeated(true);
        }
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
        return new BossRushStatusBurn(this);
    }
}
