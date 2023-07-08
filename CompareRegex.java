import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.Stack;

public class CompareRegex {

    ArrayList<Character> L1 = new ArrayList<>();
    ArrayList<Character> L2 = new ArrayList<>();
    ArrayList<Character> Terminals = new ArrayList<>();
    NFA NFA_L1;
    NFA NFA_L2;
    DFA DFA_L1;
    DFA DFA_L2;
    int TerminalNumber;
    boolean isEquivalent = true;


    public CompareRegex(String reg1, String reg2) {
        Process(reg1, reg2);
        setTerminals();
    }


    private void Process(String s1, String s2) {
        int i;
        for (i = 0; i < s1.length(); i++) {
            if (L1.isEmpty())
                L1.add('$');
            char c = L1.get(L1.size() - 1);
            if ((96 < (int) s1.charAt(i) && (int) s1.charAt(i) < 123) || s1.charAt(i) == '$' || s1.charAt(i) == '(') {
                if (c == '(' || c == '+')
                    L1.add(s1.charAt(i));
                else {
                    L1.add('.');
                    L1.add(s1.charAt(i));
                }
            } else if (s1.charAt(i) == ')') {
                if (c == '+') {
                    L1.add('$');
                    L1.add(s1.charAt(i));
                } else
                    L1.add(s1.charAt(i));
            } else if (s1.charAt(i) == '*') {
                if (c == ')' || c == '$' || ((int) c < 123 && (int) c > 96))
                    L1.add(s1.charAt(i));
                else {
                    L1.add('$');
                    L1.add(s1.charAt(i));
                }
            } else if (s1.charAt(i) == '+') {
                if (c == ')' || c == '$' || c == '*' || ((int) c < 123 && (int) c > 96))
                    L1.add(s1.charAt(i));
                else {
                    L1.add('$');
                    L1.add(s1.charAt(i));
                }
            }
        }
        for (i = 0; i < s2.length(); i++) {
            if (L2.isEmpty())
                L2.add('$');
            char c = L2.get(L2.size() - 1);
            if ((96 < (int) s2.charAt(i) && (int) s2.charAt(i) < 123) || s2.charAt(i) == '$' || s2.charAt(i) == '(') {
                if (c == '(' || c == '+')
                    L2.add(s2.charAt(i));
                else {
                    L2.add('.');
                    L2.add(s2.charAt(i));
                }
            } else if (s2.charAt(i) == ')') {
                if (c == '+') {
                    L2.add('$');
                    L2.add(s2.charAt(i));
                } else
                    L2.add(s2.charAt(i));
            } else if (s2.charAt(i) == '*') {
                if (c == ')' || c == '$' || ((int) c < 123 && (int) c > 96))
                    L2.add(s2.charAt(i));
                else {
                    L2.add('$');
                    L2.add(s2.charAt(i));
                }
            } else if (s2.charAt(i) == '+') {
                if (c == ')' || c == '$' || c == '*' || ((int) c < 123 && (int) c > 96))
                    L2.add(s2.charAt(i));
                else {
                    L2.add('$');
                    L2.add(s2.charAt(i));
                }
            }
        }
    }

    private void setTerminals() {
        int i;
        Terminals.add('$');
        for (i = 0; i < L1.size(); i++) {
            if (L1.get(i) > 96 && L1.get(i) < 123 && !Terminals.contains(L1.get(i))) {
                Terminals.add(L1.get(i));
            }
        }
        for (i = 0; i < L2.size(); i++) {
            if (L2.get(i) > 96 && L2.get(i) < 123 && !Terminals.contains(L2.get(i))) {
                isEquivalent = false;
                break;
            }
        }
        TerminalNumber = Terminals.size();
    }

    private void EvaluateString() {
        Stack<NFA> NFAs = new Stack<>();
        Stack<Character> opr = new Stack<>();
        for (int i = 0; i < L1.size(); i++) {
            if (((int) L1.get(i) > 96 && (int) L1.get(i) < 123) || L1.get(i) == '$') {
                int TerminalNo = Terminals.indexOf(L1.get(i));
                NFA curNFA = NFA.NFA_Terminals(TerminalNumber, TerminalNo);
                NFAs.push(curNFA);
            } else if (L1.get(i) == '(')
                opr.push(L1.get(i));
            else if (L1.get(i) == '.' || L1.get(i) == '+' || L1.get(i) == '*') {
                while (!opr.isEmpty() && precedence(L1.get(i)) <= precedence(opr.peek())) {
                    NFAs.push(SingleEvaluate(NFAs, opr));
                }
                opr.push(L1.get(i));
            } else { //)
                while (opr.peek() != '(') {
                    NFAs.push(SingleEvaluate(NFAs, opr));
                }
                opr.pop();
            }
        }
        while (!opr.isEmpty()) {
            NFAs.push(SingleEvaluate(NFAs, opr));
        }
        NFA_L1 = NFAs.pop();


        NFAs.clear();
        opr.clear();


        for (int i = 0; i < L2.size(); i++) {
            if (((int) L2.get(i) > 96 && (int) L2.get(i) < 123) || L2.get(i) == '$') {
                int TerminalNo = Terminals.indexOf(L2.get(i));
                NFA curNFA = NFA.NFA_Terminals(TerminalNumber, TerminalNo);
                NFAs.push(curNFA);
            } else if (L2.get(i) == '(')
                opr.push(L2.get(i));
            else if (L2.get(i) == '.' || L2.get(i) == '+' || L2.get(i) == '*') {
                while (!opr.isEmpty() && precedence(L2.get(i)) <= precedence(opr.peek())) {
                    NFAs.push(SingleEvaluate(NFAs, opr));
                }
                opr.push(L2.get(i));
            } else { //)
                while (opr.peek() != '(') {
                    NFAs.push(SingleEvaluate(NFAs, opr));
                }
                opr.pop();
            }
        }
        while (!opr.isEmpty()) {
            NFAs.push(SingleEvaluate(NFAs, opr));
        }
        NFA_L2 = NFAs.pop();

    }

    private NFA SingleEvaluate(Stack<NFA> nfas, Stack<Character> operatprs) {
        char currOper = operatprs.pop();
        NFA newNFA;
        if (currOper == '*') {
            NFA lastNFA = nfas.pop();
            newNFA = NFA.NFA_star(lastNFA, TerminalNumber);
        } else { // . or +
            NFA lastNFA2 = nfas.pop();
            NFA lastNFA1 = nfas.pop();
            newNFA = NFA.NFA_Merge(lastNFA1, lastNFA2, currOper, TerminalNumber);
        }
        return newNFA;
    }

    private int precedence(char op) {
        return switch (op) {
            case '+' -> 1;
            case '.' -> 2;
            case '*' -> 3;
            default -> -1;
        };
    }

    public boolean isEquivalent() {
        if (!isEquivalent) {
            return false;
        } else {
            EvaluateString();
            DFA_L1 = Convert.ConvertNFAtoDFA(NFA_L1, TerminalNumber);
            DFA_L2 = Convert.ConvertNFAtoDFA(NFA_L2, TerminalNumber);
            return DFA.isEquivalent(DFA_L1, DFA_L2, TerminalNumber);
        }
    }


}
