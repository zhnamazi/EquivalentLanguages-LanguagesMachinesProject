import java.util.ArrayList;

public class State {
    public boolean isStart = false;
    public boolean isFinal = false;
    public ArrayList<ArrayList<State>> Transitions = new ArrayList<>();

    public State(int TerminalNumber) {
        for (int i = 0; i < TerminalNumber; i++) {
            Transitions.add(new ArrayList<>());
        }
    }
}
