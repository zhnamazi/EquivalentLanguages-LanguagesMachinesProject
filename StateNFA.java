import java.util.ArrayList;

public class StateNFA implements Comparable<StateNFA> {
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

    @Override
    public int compareTo(StateNFA nfa) {
        if (this.no > nfa.no)
            return 1;
        else if (this.no < nfa.no)
            return -1;
        else
            return 0;
    }
}
