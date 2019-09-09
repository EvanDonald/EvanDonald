/**************************************************************************
*                                                                         *
*     Program Filename:  Palindromes2.java                                *
*     Author          :  John B. Student                                  *
*     Date Written    :  March 29, 2019                                   *
*     Purpose         :  Find Minimal Cuts in Palindrome Partition        *
*     Input from      :  None                                             *
*     Output to       :  Screen                                           *
*                                                                         *
**************************************************************************/

public class Palindromes2
{
   static int[][] ResultsMatrix = new int[100][100];

   public static void main(String[] args)
   {
      String W = "Hello";
      String X = "ABACDCEFEGHG";
      String Y = "AAAAAAAAAAJJZZZZZZZZ";
      String Z = "ABCDEFGHIJJJKLMNOPQR";

      InitializeMatrix(ResultsMatrix);
      System.out.println("String W: " + MinimalCuts(W, 0, W.length()-1));
      InitializeMatrix(ResultsMatrix);
      System.out.println("String X: " + MinimalCuts(X, 0, X.length()-1));
      InitializeMatrix(ResultsMatrix);
      System.out.println("String Y: " + MinimalCuts(Y, 0, Y.length()-1));
      InitializeMatrix(ResultsMatrix);
      System.out.println("String Z: " + MinimalCuts(Z, 0, Z.length()-1));
   }
   // end main method

   public static void InitializeMatrix(int[][] A)
   {
      for (int i = 0; i < 100; i++)
         for (int j = 0; j < 100; j++)
            A[i][j] = -1;
         // end for
      // end for
   }
   // end method InitializeMatrix

   public static boolean isPalindrome(String S)
   {
      int i, N = S.length();

      for (i = 0; i < N/2; i++)
         if (S.charAt(i) != S.charAt(N-i-1))
            return false;
         // end if
      // end for

      return true;
   }
   // end method isPalindrome

   public static int MinimalCuts(String S, int L, int R)
   {
      int i, Min1, Min2, Cuts = 1000000, N = S.length();

      if (ResultsMatrix[L][R] != -1)
         return ResultsMatrix[L][R];
      // end if

      if (isPalindrome(S))
      {
         ResultsMatrix[L][R] = 0;
         return 0;
      }
      else
      {
         for (i = 1; i < N; i++)
         {
            Min1 = MinimalCuts(S.substring(0, i), L, L+i-1);
            Min2 = MinimalCuts(S.substring(i, N), L+i, L+N-1);
            Cuts = Math.min(1 + Min1 + Min2, Cuts);
         }
         // end for
      }
      // end if

      ResultsMatrix[L][R] = Cuts;
      return Cuts;
   }
   // end method MinimalCuts
}
// end class Palindromes2
