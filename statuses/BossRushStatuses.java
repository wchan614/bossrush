package statuses;

import units.BossRushUnit;
public abstract class BossRushStatuses {
    // TODO
    
    protected String name;
    protected String type; //Buff or Debuff
    protected String description; 
    protected int turnsLeft;
    protected int maxTurnsAllowed;
    
    //copy constructor

    public BossRushStatuses() {}
    public BossRushStatuses(BossRushStatuses status) {
        this.name = status.name;
        this.type = status.type;
        this.description = status.description;
        this.turnsLeft = status.turnsLeft;
        this.maxTurnsAllowed = status.maxTurnsAllowed;
    }

    public abstract void onApply(BossRushUnit self);
    public abstract void triggerEffect(BossRushUnit self);
    public abstract void updateStatus(BossRushUnit self);
    public abstract void reapply(BossRushUnit self);
    public abstract void onTimeOut(BossRushUnit self);
    public abstract BossRushStatuses makeCopy();
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public String getDescription() {
        return this.description;
    }
    public int getTurnsLeft() {
        return this.turnsLeft;
    }
    public int getMaxTurnsAllowed() {
        return this.maxTurnsAllowed;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDescription(String descript) {
        this.description = descript;
    }
    public void setTurnsLeft(int turns) {
        this.turnsLeft = turns;
    }
    public void setMaxTurnsAllowed(int max) {
        this.maxTurnsAllowed = max;
    }


}
