import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;

public class DFA {
    ArrayList<State>DStates=new ArrayList<>();
    ArrayList<State>nstates=new ArrayList<>();
    ArrayList<Character>T;
    String [][]NTT;
    String [][]DTT;
    public DFA(ArrayList<State> NS,ArrayList<Character> Terminal,String[][]NTT){
        DStates=NS;
        T=Terminal;
        this.NTT=NTT;
    }
    public void spliteM(String [][]NTT){
        ArrayList<String>start=new ArrayList<>();
        ArrayList<String>end=new ArrayList<>();
        for (int i=0; i<NTT.length;i++){
            String []v=new String[1];
            v[0]=NTT[i][1];
            v[1]=NTT[i][2];
            String []W=new String[1];
            W[0]=NTT[i][1];
            W[1]=NTT[i][0];
            start.add(Integer.parseInt(NTT[i][0]),toString(v));
            end.add(Integer.parseInt(NTT[i][3]),toString(W));
        }
    }
    public void nfaTTtodfaTT(String [][]ntt){
        spliteM(ntt);

    }

    private String toString(String[] s) {
        String S=new String();
        for(int i=0;i<s.length;i++){
            S+=Integer.toString(Integer.parseInt(s[i]));
        }
        return S;
    }

//    private String toString(String s) {
//        String S=new String();
//        S=Integer.toString(Integer.parseInt(s));
//        return s;
//    }
}

