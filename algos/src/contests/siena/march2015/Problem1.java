package contests.siena.march2015;

import java.util.*;

// Remember to make sure the name of the file & class match!
public class Problem1
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        sc.nextLine();
        for (int trialCount = 0; trialCount < K; trialCount++)
        {
            int n = sc.nextInt();
            long square = (((n) * (n + 1))/2) * (((n) * (n + 1))/2);
            System.out.println(square);
            
        }
        sc.close(  );
    }
    
    
    
}
