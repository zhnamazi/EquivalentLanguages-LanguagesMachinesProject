import java.util.ArrayList;
import java.util.Collections;

public class Convert {
    public static DFA ConvertNFAtoDFA(NFA nfa, int TerminalNumber) { //number of terminals without lambda
        DFA converted = new DFA();
        int no = 1;
        StateDFA start = new StateDFA(TerminalNumber - 1, 0);
        start.isStart = true;
        start.NFAStateSubset.add(nfa.States.get(0));
        start.isFinal = StatesFromLambda(start.NFAStateSubset);
        converted.States.add(start);
        boolean TrapisUsed = false;
        StateDFA Trap = null;
        StateDFA currState;
        ArrayList<StateNFA> newSubset;
        for (int i = 0; i < converted.States.size(); i++) {
            currState = converted.States.get(i);
            boolean newisFinal = false;
            for (int j = 1; j < TerminalNumber; j++) {
                newSubset = new ArrayList<>();
                for (int k = 0; k < currState.NFAStateSubset.size(); k++) {
                    for (int l = 0; l < currState.NFAStateSubset.get(k).Transitions.get(j).size(); l++) {
                        StateNFA CandState = currState.NFAStateSubset.get(k).Transitions.get(j).get(l);
                        if (!newSubset.contains(CandState)) {
                            newSubset.add(CandState);
                            if (CandState.isFinal)
                                newisFinal = true;
                        }
                    }
                }
                if (StatesFromLambda(newSubset))
                    newisFinal = true;
                int k;
                for (k = 0; k < converted.States.size(); k++) {
                    if (newSubset.isEmpty()) {
                        if (!TrapisUsed) {
                            Trap = new StateDFA(TerminalNumber - 1, no);
                            no++;
                            TrapisUsed = true;
                            for (int l = 0; l < TerminalNumber - 1; l++) {
                                Trap.Transitions.set(l, Trap);
                            }
                            converted.States.add(Trap);
                        }
                        currState.Transitions.set(j - 1, Trap);
                        break;
                    } else if (SetsAreEqual(newSubset, converted.States.get(k).NFAStateSubset)) {
                        currState.Transitions.set(j - 1, converted.States.get(k));
                        break;
                    }
                }
                if (k == converted.States.size()) {
                    StateDFA newState = new StateDFA(TerminalNumber - 1, no);
                    newState.NFAStateSubset = newSubset;
                    newState.isFinal = newisFinal;
                    converted.States.add(newState);
                    currState.Transitions.set(j - 1, newState);
                    no++;
                }
            }
        }

        return converted;
    }

    private static boolean StatesFromLambda(ArrayList<StateNFA> subsetNFA) {
        boolean isFinal = false;
        for (int i = 0; i < subsetNFA.size(); i++) {
            for (int j = 0; j < subsetNFA.get(i).Transitions.get(0).size(); j++) {
                StateNFA CandState = subsetNFA.get(i).Transitions.get(0).get(j);
                if (!subsetNFA.contains(CandState)) {
                    subsetNFA.add(CandState);
                    if (CandState.isFinal)
                        isFinal = true;
                }
            }
        }
        return isFinal;
    }

    private static boolean SetsAreEqual(ArrayList<StateNFA> sub1, ArrayList<StateNFA> sub2) {
        if (sub1.size() != sub2.size())
            return false;
        Collections.sort(sub1);
        Collections.sort(sub2);
        int size = sub1.size();
        for (int i = 0; i < size; i++) {
            if (sub1.get(i).no != sub2.get(i).no)
                return false;
        }
        return true;
    }
}
