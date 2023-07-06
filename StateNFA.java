import java.util.ArrayList;

public class StateNFA {
    int no;
    public boolean isStart = false;
    public boolean isFinal = false;
    public ArrayList<ArrayList<StateNFA>> Transitions = new ArrayList<>();

    public StateNFA(int TerminalNumber, int no) {
        for (int i = 0; i < TerminalNumber; i++) {
            Transitions.add(new ArrayList<>());
        }
        this.no = no;
    }
}
