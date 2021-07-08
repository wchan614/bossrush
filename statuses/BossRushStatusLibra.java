package statuses;

import units.*;
public class BossRushStatusLibra extends BossRushStatuses {
    private String form;
    public BossRushStatusLibra (int turns,int max,String form) {
        setName("Libra");
        setType("Buff");
        setDescription("Libra changes depending on the caster.");
        setTurnsLeft(turns);
        setMaxTurnsAllowed(max);
        this.form = form;

    }
    //copy constructor
    public BossRushStatusLibra (BossRushStatusLibra b) {
        this.name = b.name;
        this.type = b.type;
        this.description = b.description;
        this.turnsLeft = b.turnsLeft;
        this.maxTurnsAllowed = b.maxTurnsAllowed;
        this.form = b.form;

    }

    public void onApply(BossRushUnit self) {
        if (this.form.equals(">")){
            self.setAtk(self.getAtk() + 30);
            self.setSpd(self.getSpd() + 5);
            setName("Libra >");
            setDescription("Unit ATK+, SPD+");
        }
        if (this.form.equals("<")){
            self.setDef(self.getDef() + 25);
            self.setMDef(self.getMDef() + 25);
            setName("Libra <");
            setDescription("Unit DEF+, MDEF+");
        }
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
        if (this.form.equals(">")){
            self.setAtk(self.getAtk() - 30);
            self.setSpd(self.getSpd() - 5);
        }
        if (this.form.equals("<")){
            self.setDef(self.getDef() - 25);
            self.setMDef(self.getMDef() - 25);
        }
    }
    public BossRushStatuses makeCopy() {
        return new BossRushStatusLibra(this);
    }
}
