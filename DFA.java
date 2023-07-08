import java.util.ArrayList;

public class DFA {
    ArrayList<StateDFA> States = new ArrayList<>();


    public static DFA DFA_complementation(DFA dfa) {
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
                StateDFA newState = new StateDFA(TerminalNumber, n);
                newState.DFAStateSubset = new StateDFA[2];
                newState.DFAStateSubset[0] = dfa1.States.get(i);
                newState.DFAStateSubset[1] = dfa2.States.get(j);
                newState.isStart = newState.DFAStateSubset[0].isStart && newState.DFAStateSubset[1].isStart;
                newState.isFinal = newState.DFAStateSubset[0].isFinal && newState.DFAStateSubset[1].isFinal;
                n++;
                inters.States.add(newState);
            }
        }
        for (int i = 0; i < interSize; i++) {
            for (int j = 0; j < TerminalNumber; j++) {
                int no1 = inters.States.get(i).DFAStateSubset[0].Transitions.get(j).no;
                int no2 = inters.States.get(i).DFAStateSubset[1].Transitions.get(j).no;
                for (int k = 0; k < interSize; k++) {
                    if (inters.States.get(k).DFAStateSubset[0].no == no1 && inters.States.get(k).DFAStateSubset[1].no == no2) {
                        inters.States.get(i).Transitions.set(j, inters.States.get(k));
                        break;
                    }
                }
            }
        }
        return inters;
    }

    public static boolean DFA_isEmpty(DFA dfa) {
        int[] mark = new int[dfa.States.size()];
        StateDFA start = dfa.States.get(0);
        if (start.MarkforDFS == 0) {
            VisitState(start);
        }
        for (int i = 0; i < dfa.States.size(); i++) {
            if(dfa.States.get(i).isFinal && dfa.States.get(i).MarkforDFS==2){
                return false;
            }
        }
        return true;
    }

    private static void VisitState(StateDFA state) {
        state.MarkforDFS = 1;
        for (int i = 0; i < state.Transitions.size(); i++) {
            if (state.Transitions.get(i).MarkforDFS == 0)
                VisitState(state.Transitions.get(i));
        }
        state.MarkforDFS = 2;
    }

    public static boolean isEquivalent(DFA dfa1, DFA dfa2, int TerminalNumber) {
        DFA dfa1_comp = DFA_complementation(dfa1);
        DFA inter1 = DFA_intersection(dfa1_comp, dfa2, TerminalNumber - 1);
        dfa1 = DFA_complementation(dfa1_comp);
        DFA dfa2_comp = DFA_complementation(dfa2);
        DFA inter2 = DFA_intersection(dfa1, dfa2_comp, TerminalNumber - 1);
        if (DFA_isEmpty(inter1) && DFA_isEmpty(inter2))
            return true;
        return false;
    }
}
