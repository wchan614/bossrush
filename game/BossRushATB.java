package game;

import java.util.ArrayList;
import units.*;

public class BossRushATB {
    
    private final int SPEED_LIMIT = 1000;
    private final int PREDICTION_SIZE = 5;
    private ArrayList<Integer> unitsATB;

    public BossRushATB(ArrayList<BossRushUnit> units) {
        this.unitsATB = new ArrayList<Integer>();

        for (BossRushUnit unit : units) {
            if (!unit.isDefeated()) {
                this.unitsATB.add(unit.getATB());
            }
            else {
                this.unitsATB.add(-1);
            }
        }
    }

    public void loadIn(ArrayList<BossRushUnit> units) {
        for (int i = 0 ; i < units.size(); i++) {
            BossRushUnit unit = units.get(i);
            if (unit.isDefeated()) {
                unitsATB.set(i,-1);
            }
            else {
                unitsATB.set(i, unit.getATB());
            }
        }
    }


    public ArrayList<BossRushUnit> calculateSpeeds(ArrayList<BossRushUnit> units) {
        loadIn(units);

        ArrayList<BossRushUnit> order = new ArrayList<BossRushUnit>();

        for (int i = 0 ; i < PREDICTION_SIZE; i++) {
            Boolean maxFound = false;
            while (!maxFound) {
                // Check every alive persons ATB >= SPEED_LIMIT;
                for (int index = 0; index < unitsATB.size(); index++) {

                    if (unitsATB.get(index) >= SPEED_LIMIT) { // found
                        maxFound = true;

                        BossRushUnit victor = units.get(index);

                        order.add(victor); // add to order.
                        unitsATB.set(index, unitsATB.get(index) % SPEED_LIMIT); // reset the max speed person

                        // Save everyones ATB if first pass
                        if (i == 0) {
                            for (int j = 0; j < units.size(); j++) {
                                BossRushUnit u = units.get(j); // from units
                                if (!u.isDefeated()) {
                                    // first pass
                                        u.setATB(unitsATB.get(j));
                                }
                            }
                        }
                        break;
                    }
                }
        
                // Update every alive persons ATB
                for (int index = 0; index < unitsATB.size(); index++ ) {
                    BossRushUnit unit = units.get(index);
                    if (!unit.isDefeated()) {
                        unitsATB.set(index, unitsATB.get(index)+unit.getSpd());
                    }
                }
            }
        }
        return order;
    }
}
