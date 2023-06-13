import java.util.ArrayList;

public class CompareRegex {

    ArrayList<Character> L1 = new ArrayList<>();
    ArrayList<Character> L2 = new ArrayList<>();
    ArrayList<Character> Terminals = new ArrayList<>();
    boolean isEquivalent;


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
            } else if (s1.charAt(i) == '*' || s1.charAt(i) == '+') {
                if (c == ')' || c == '$' || ((int) c < 123 && (int) c > 96))
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
            } else if (s2.charAt(i) == '*' || s2.charAt(i) == '+') {
                if (c == ')' || c == '$' || ((int) c < 123 && (int) c > 96))
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
            if (L1.get(i) > 96 && L1.get(i) < 123 && !Terminals.contains(L1.get(i))) {
                isEquivalent = false;
                break;
            }
        }
    }

    public void print() {
        for (int i = 0; i < L1.size(); i++)
            System.out.print(L1.get(i));
    }
}
