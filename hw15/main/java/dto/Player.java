package dto;

public class Player {
    private String name;
    private int win;
    private int lose;

    public Player(String name) {
        this.name = name;
        this.win = 0;
        this.lose = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }
}
