import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("enter regex1");
        Scanner sc = new Scanner(System.in);
        String reg1 = sc.nextLine();
        System.out.println("enter regex2");
        String reg2 = sc.nextLine();
        CompareRegex cr = new CompareRegex(reg1, reg2);
        if (cr.isEquivalent())
            cr.print();
        else
            System.out.print("false");
    }


}