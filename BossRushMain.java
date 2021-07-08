// William Chan 2021

import game.*;
import java.lang.Exception;

public class BossRushMain {
    public static void main(String[] args) throws Exception {

        BossRushGame game = new BossRushGame();
        game.start();

        // Unreachable
        throw new Exception("Unreachable in BossRushMain but was reached");
    }
}