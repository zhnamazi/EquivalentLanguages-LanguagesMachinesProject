import java.util.ArrayList;

public class NFA {
    ArrayList<StateNFA> States = new ArrayList<>();

    public static NFA NFA_Terminals(int TerminalNumber, int TerminalNo) {
        NFA nfa = new NFA();
        StateNFA Sstart = new StateNFA(TerminalNumber, 0);
        StateNFA Sfinal = new StateNFA(TerminalNumber, 1);
        Sstart.isStart = true;
        Sfinal.isFinal = true;
        Sstart.Transitions.get(TerminalNo).add(Sfinal);
        nfa.States.add(Sstart);
        nfa.States.add(Sfinal);
        return nfa;
    }

    public static NFA NFA_Merge(NFA nfa1, NFA nfa2, char operation, int TerminalNumber) {
        NFA merged = new NFA();
        if (operation == '+') {
            StateNFA start = new StateNFA(TerminalNumber, 0);
            start.isStart = true;
            StateNFA start1 = nfa1.States.get(0);
            StateNFA start2 = nfa2.States.get(0);
            start.Transitions.get(0).add(start1);
            start.Transitions.get(0).add(start2);
            nfa1.States.get(0).isStart = false;
            nfa2.States.get(0).isStart = false;

            StateNFA end = new StateNFA(TerminalNumber, 0);
            end.isFinal = true;
            nfa1.States.get(nfa1.States.size() - 1).Transitions.get(0).add(end);
            nfa2.States.get(nfa2.States.size() - 1).Transitions.get(0).add(end);
            nfa1.States.get(nfa1.States.size() - 1).isFinal = false;
            nfa2.States.get(nfa2.States.size() - 1).isFinal = false;

            merged.States.add(start);
            int no = 1;
            for (int i = 0; i < nfa1.States.size(); i++, no++) {
                StateNFA newState = nfa1.States.get(i);
                newState.no = no;
                merged.States.add(newState);
            }
            for (int i = 0; i < nfa2.States.size(); i++, no++) {
                StateNFA newState = nfa2.States.get(i);
                newState.no = no;
                merged.States.add(newState);
            }
            end.no = no;
            merged.States.add(end);
        } else {
            int n = nfa1.States.size();
            nfa1.States.get(n - 1).isFinal = false;
            for (int i = 0; i < n; i++)
                merged.States.add(nfa1.States.get(i));
            nfa2.States.get(0).isStart = false;
            merged.States.get(n - 1).Transitions.get(0).add(nfa2.States.get(0));
            int no = n;
            n = nfa2.States.size();
            for (int i = 0; i < n; i++, no++) {
                nfa2.States.get(i).no = no;
                merged.States.add(nfa2.States.get(i));
            }
        }
        return merged;
    }

    public static NFA NFA_star(NFA nfa, int TerminalNumber) {
        NFA nfa_star = new NFA();
        int n = nfa.States.size();
        nfa.States.get(0).isStart = false;
        nfa.States.get(n - 1).isFinal = false;
        StateNFA start = new StateNFA(TerminalNumber, 0);
        StateNFA end = new StateNFA(TerminalNumber, n+1);
        start.isStart = true;
        end.isFinal = true;
        start.Transitions.get(0).add(nfa.States.get(0));
        start.Transitions.get(0).add(end);
        nfa.States.get(n - 1).Transitions.get(0).add(nfa.States.get(0));
        nfa.States.get(n - 1).Transitions.get(0).add(end);
        nfa_star.States.add(start);
        for (int i = 0; i < n; i++) {
            nfa.States.get(i).no++;
            nfa_star.States.add(nfa.States.get(i));
        }
        nfa_star.States.add(end);
        return nfa_star;
    }
}
