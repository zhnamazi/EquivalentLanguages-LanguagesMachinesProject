import java.util.ArrayList;

public class DFA {
    ArrayList<StateDFA> States = new ArrayList<>();


    public static DFA DFA_complementation(DFA dfa) {
        //DFA comp = new DFA(); copy
        DFA comp = dfa;
        int StateNumber = comp.States.size();
        for (int i = 0; i < StateNumber; i++) {
            comp.States.get(i).isFinal = !comp.States.get(i).isFinal;
        }
        return comp;
    }

    public static DFA DFA_intersection(DFA dfa1, DFA dfa2, int TerminalNumber) {
        DFA inters = new DFA();
        int interSize = dfa1.States.size() * dfa2.States.size();
        int n = 0;
        for (int i = 0; i < dfa1.States.size(); i++) {
            for (int j = 0; j < dfa2.States.size(); j++) {
                StateDFA newState = new StateDFA(n);
                newState.DFAStateSubset[0] = dfa1.States.get(i);
                newState.DFAStateSubset[1] = dfa2.States.get(j);
                newState.isStart = newState.DFAStateSubset[0].isStart && newState.DFAStateSubset[1].isStart;
                newState.isFinal = newState.DFAStateSubset[0].isFinal && newState.DFAStateSubset[1].isFinal;
                n++;
            }
        }
        for (int i = 0; i < interSize; i++) {
            for (int j = 0; j < TerminalNumber; j++) {
                int no1 = inters.States.get(i).DFAStateSubset[0].Transitions.get(j).no;
                int no2 = inters.States.get(i).DFAStateSubset[1].Transitions.get(j).no;
                for (int k = 0; k < interSize; k++) {
                    if (inters.States.get(k).DFAStateSubset[0].no == no1 && inters.States.get(k).DFAStateSubset[1].no == no2) {
                        inters.States.get(i).Transitions.add(inters.States.get(k));
                        break;
                    }
                }
            }
        }
        return inters;
    }

    public static boolean DFA_isEmpty(DFA dfa, int TerminalNumber) {
        boolean result = false;
        int[] mark = new int[TerminalNumber];
        StateDFA start = dfa.States.get(0);
        if (mark[0] == 0) {
            //visit();
        }
        return result;
    }

    //private
}
