import java.util.ArrayList;

public class StateDFA {
    int no;
    public boolean isStart = false;
    public boolean isFinal = false;
    public int MarkforDFS = 0;
    public ArrayList<StateDFA> Transitions = new ArrayList<>();
    public ArrayList<StateNFA> NFAStateSubset = new ArrayList<>();
    public StateDFA[] DFAStateSubset;

    public StateDFA(int TerminalNumber, int no) {
        for (int i=0;i<TerminalNumber;i++){
            Transitions.add(null);
        }
        this.no = no;
    }
}