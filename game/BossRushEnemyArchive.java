package game;

import units.bosses.BossRushBoss;

public class BossRushEnemyArchive {
    
    private int pageNumber = 0;
    //private final int DISPLAY_LIMIT = 3;

    public void nextPage(int maxPage) {
        if (pageNumber < maxPage-1) {
            pageNumber++;
        }
    }

    public void previousPage(int maxPage) {
        if (pageNumber > 0) {
            pageNumber--;
        }

    }

    public void showEnemies(BossRushObjectives objectives) {
        //int mostPages = (int)Math.ceil(Double.valueOf(objectives.getObjectives().size()/DISPLAY_LIMIT));
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Enemy Archive: ");
        //System.out.printf("Page %d/%d\n\n",pageNumber+1,mostPages);
        // System.out.println("z) Previous Page   x) Next Page\n"); not ended for now since 1/1 page.
        for (int i = 0; i < objectives.getObjectiveCount(); i++){
            BossRushBoss boss = objectives.getObjectives().get(i);
            boss.show(0);
            boss.showRanking();
            boss.showStats();
            boss.showSkills();
            System.out.println("\n");
        }
    }
}
