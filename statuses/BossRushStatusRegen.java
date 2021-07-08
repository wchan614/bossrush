package statuses;

import units.BossRushUnit;
public class BossRushStatusRegen extends BossRushStatuses  {
        
    public BossRushStatusRegen(int turns,int max) {
        setName("Regen");
        setType("Buff");
        setDescription("Unit regenerates 8% max HP per turn.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);

    }
    public BossRushStatusRegen(BossRushStatusRegen b) {
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
        double newHp = Math.min(self.getMaxHp(),  self.getHp() + self.getMaxHp()*0.08);
        self.setHp((int) newHp);
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
        return new BossRushStatusRegen(this);
    }
}
