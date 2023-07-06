import java.util.ArrayList;

public class StateDFA {
    int no;
    public boolean isStart = false;
    public boolean isFinal = false;
    public ArrayList<StateDFA> Transitions = new ArrayList<>();
    public ArrayList<StateNFA> NFAStateSubset = new ArrayList<>();
    public StateDFA[] DFAStateSubset = new StateDFA[2];

    public StateDFA(int no) {
        //Transitions.add(null);
        /*for (int i = 0; i < TerminalNumber; i++) {
            Transitions.add(new ArrayList<>());
        }*/
        this.no = no;
    }
}