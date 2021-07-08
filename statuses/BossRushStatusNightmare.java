package statuses;

import units.*;
public class BossRushStatusNightmare extends BossRushStatuses {
    public BossRushStatusNightmare(int turns,int max) {
        setName("Nightmare");
        setType("Debuff");
        setDescription("User will lose 2000HP when debuff expires.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }

    public BossRushStatusNightmare(BossRushStatusNightmare b) {
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
    }
    
    public void onTimeOut(BossRushUnit self) {
        if (self.hasStatus("Gambit") != null) {
            self.setHp(Math.max(self.getHp() - 2000,1));
        }
        else if (self.hasStatus("Manawall") != null) {
            self.setHp(Math.max(self.getHp() - 400,0));
            if (self.getHp() == 0) {
                self.setDefeated(true);
            }
        }
        else {
            self.setHp(Math.max(self.getHp() - 2000,0));
            if (self.getHp() == 0) {
                self.setDefeated(true);
            }
        }

    }
    public BossRushStatuses makeCopy() {
        return new BossRushStatusNightmare(this);
    }
}
