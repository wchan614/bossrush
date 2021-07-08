package statuses;

import units.*;
public class BossRushStatusSiphon extends BossRushStatuses {
    private BossRushUnit from;
    public BossRushStatusSiphon (int turns,int max,BossRushUnit from) {
        setName("Siphon");
        setType("Buff");
        setDescription("Damage done redirected as healing to caster.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);
        this.from = from;

    }
    public BossRushStatusSiphon(BossRushStatusSiphon b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
        this.from = b.from;
    }

    public String getFrom() {
        return from.getName();
    }
    public void heal(BossRushUnit self, int heal) {
        from.setHp(Math.max(from.getMaxHp(),heal+from.getHp()));
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
    }

    public BossRushStatuses makeCopy() {
        return new BossRushStatusSiphon(this);
    }
}
