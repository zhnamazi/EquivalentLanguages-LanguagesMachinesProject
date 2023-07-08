import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the first regular expression");
        Scanner sc = new Scanner(System.in);
        String reg1 = sc.nextLine();
        System.out.println("Enter the second regular expression");
        String reg2 = sc.nextLine();
        CompareRegex cr = new CompareRegex(reg1, reg2);
        boolean result = cr.isEquivalent();
        if (result)
            System.out.println("These languages are equivalent");
        else System.out.println("These languages are not equivalent");
    }
    
}