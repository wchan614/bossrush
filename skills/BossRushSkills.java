package skills;

import units.BossRushUnit;
import java.util.ArrayList;
public abstract class BossRushSkills {
    // TODO

    private String name;
    private String type;
    private String description;
    private String hpCost; //int
    private String mpCost; //int 
    private String potency; //int

    public abstract String doSkill(BossRushUnit caster, ArrayList<BossRushUnit> field);
    public abstract ArrayList<BossRushUnit> selectTargets(BossRushUnit caster, ArrayList<BossRushUnit> field);
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public String getDescription() {
        return this.description;
    }
    public int getHpCost() {
        if (hpCost.equals("ALL") || hpCost.contains("%")) {
            return 0;
        }
        if (hpCost.contains("+")) {
            return Integer.valueOf(hpCost.substring(0,hpCost.length() - 1));
        }
        
        return Integer.valueOf(hpCost);
    
    }

    public int getMpCost() {
        if (mpCost.equals("ALL") || mpCost.contains("%")) {
            return 0;
        }
        if (mpCost.contains("+")) {
            return Integer.valueOf(mpCost.substring(0,mpCost.length() - 1));
        }
        
        return Integer.valueOf(mpCost);
    }
    public int getPotency() {
        if (potency.contains("+")) {
            return Integer.valueOf(potency.substring(0,potency.length() - 1));
        }
        
        return Integer.valueOf(this.potency);
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setHpCost(String hpCost) {
        this.hpCost = hpCost;
    }
    public void setMpCost(String mpCost) {
        this.mpCost = mpCost;
    }
    public void setPotency(String potency) {
        this.potency = potency;
    }


}
