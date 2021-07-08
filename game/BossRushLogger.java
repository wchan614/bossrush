package game;

import java.util.ArrayList;

public class BossRushLogger {
    
    private int count = 1;
    private ArrayList<String> logs = new ArrayList<String>(); 
    public BossRushLogger(){}

    public void add(String log) {
        if (!log.equals("")){
            this.logs.add(count+": "+log);
            count++;
        }
    }

    public void printLog() {
        for (int i =  Math.max(0,logs.size() - 5); i < logs.size(); i++) {
            System.out.println(logs.get(i));
        }
    }

    public void printAllLog() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
