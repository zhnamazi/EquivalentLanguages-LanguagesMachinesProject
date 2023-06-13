import java.util.ArrayList;

public class NFA {
    ArrayList<State> States = new ArrayList<>();

    public static NFA NFA_Terminals(int TerminalNumber, int TerminalNo) {
        NFA nfa = new NFA();
        State Sstart = new State(TerminalNumber);
        State Sfinal = new State(TerminalNumber);
        Sstart.isStart = true;
        Sfinal.isFinal = true;
        Sstart.Transitions.add(new ArrayList<>());
        Sstart.Transitions.get(TerminalNo).add(Sfinal);
        nfa.States.add(Sstart);
        nfa.States.add(Sfinal);
        return nfa;
    }

    public static NFA NFA_Merge(NFA nfa1, NFA nfa2, char operation, int TerminalNumber) {
        NFA merged = new NFA();
        switch (operation) {
            case '+':
                State start = new State(TerminalNumber);
                start.isStart = true;
                State start1 = nfa1.States.get(0);
                State start2 = nfa2.States.get(0);
                start.Transitions.get(0).add(start1);
                start.Transitions.get(0).add(start2);
                nfa1.States.get(0).isStart = false;
                nfa2.States.get(0).isStart = false;

                State end = new State(TerminalNumber);
                end.isFinal = true;
                nfa1.States.get(nfa1.States.size() - 1).Transitions.get(0).add(end);
                nfa2.States.get(nfa2.States.size() - 1).Transitions.get(0).add(end);
                nfa1.States.get(nfa1.States.size() - 1).isFinal = false;
                nfa2.States.get(nfa2.States.size() - 1).isFinal = false;

                merged.States.add(start);
                for (int i = 0; i < nfa1.States.size(); i++)
                    merged.States.add(nfa1.States.get(i));
                for (int i = 0; i < nfa2.States.size(); i++)
                    merged.States.add(nfa2.States.get(i));
                merged.States.add(end);

                break;
            default: //'.'
                int n = nfa1.States.size();
                nfa1.States.get(n - 1).isFinal = false;
                for (int i = 0; i < n; i++)
                    merged.States.add(nfa1.States.get(i));
                nfa2.States.get(0).isStart = false;
                merged.States.get(n - 1).Transitions.get(0).add(nfa2.States.get(0));
                n = nfa2.States.size();
                for (int i = 0; i < n; i++)
                    merged.States.add(nfa2.States.get(i));
        }
        return merged;
    }

    public static NFA NFA_star(NFA nfa, int TerminalNumber) {
        NFA nfa_star = new NFA();
        int n = nfa.States.size();
        nfa.States.get(0).isStart = false;
        nfa.States.get(n - 1).isFinal = false;
        State start = new State(TerminalNumber);
        State end = new State(TerminalNumber);
        start.isStart = true;
        end.isFinal = true;
        start.Transitions.get(0).add(nfa.States.get(0));
        start.Transitions.get(0).add(end);
        nfa.States.get(n - 1).Transitions.get(0).add(nfa.States.get(0));
        nfa.States.get(n - 1).Transitions.get(0).add(end);
        nfa_star.States.add(start);
        for (int i = 0; i < n; i++)
            nfa_star.States.add(nfa.States.get(i));
        nfa_star.States.add(end);
        return nfa_star;
    }
}
