package statuses;

import units.BossRushUnit;

public class BossRushStatusMimic extends BossRushStatuses {
    private int damage = 0;
    private int initialHp = 0;
    public BossRushStatusMimic (int turns,int max) {
        setName("Mimic");
        setType("Buff");
        setDescription("Ready to copy your every move");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);
    }
    //copy constructor
    public BossRushStatusMimic (BossRushStatusMimic b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
        this.damage = b.damage;

    }

    public int getDamage() {
        return this.damage;
    }

    public void onApply(BossRushUnit self) {
        initialHp = self.getHp();
        return;
    }

    public void triggerEffect(BossRushUnit self) {
        return;
    }

    public void updateStatus(BossRushUnit self) {
        damage = initialHp - self.getHp();
        
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
        return new BossRushStatusMimic(this);
    }
}
