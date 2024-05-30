import java.util.ArrayList;
import java.util.List;

public class Board {
    private Timer timer = new Timer();
    private List<Square> squares = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<City> eventCity = new ArrayList<>();

    public Board() {
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public void setSquares(List<Square> squares) {
        this.squares = squares;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<City> getEventCity() {
        return eventCity;
    }

    public void setEventCity(List<City> eventCity) {
        this.eventCity = eventCity;
    }
}
