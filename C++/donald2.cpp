/**********************************************************************************
*                                                                                 *
*     Program Filename:  donald2.cpp                                              *
*     Author          :  Evan Donald                                              *
*     Date Written    :  September 23, 2017                                       *
*     Purpose         :  To show the moves a Knight can make on a chess board     *
*     Input from      :  Keyboard                                                 *
*     Output to       :  Screen                                                   *
*                                                                                 *
**********************************************************************************/

#include <iostream.h>
#include <iomanip.h>
#include <conio.h>
#include <time.h>
#include <ctype>
#include <donald.h>


typedef int ChessBoard[13][13];
void initializeBoard(ChessBoard);
void printBoard(ChessBoard);
void knightMoves(ChessBoard);

int main()
{
     ChessBoard B;
     initializeBoard(B);
     knightMoves(B);
     printBoard(B);
}

/**********************************************************************************
*                                                                                 *
*     Function Name   :  initializeBoard                                          *
*     Purpose         :  To place zeros in board                                  *
*     Called by       :  Main                                                     *
*     Functions Called:  None                                                     *
*                                                                                 *
**********************************************************************************/

void initializeBoard(ChessBoard x)
{
     int i, j;

     for (i = 0; i <= 1; i++)
          for (j = 0; j <= 11; j++)
               x[i][j] = -1;
     for (i = 10; i <= 11; i++)
          for (j = 0; j <= 11; j++)
               x[i][j] = -1;
     for (i = 2; i <= 11; i++)
          for (j = 0; j <= 1; j++)
               x[i][j] = -1;
     for (i = 2; i <= 11; i++)
          for (j = 10; j <= 11; j++)
               x[i][j] = -1;
     for (i = 2; i <= 9; i++)
          for (j = 2; j <= 9; j++)
               x[i][j] = 0;
     
     return;
}

/**************************************************************************
*                                                                         *
*     Function Name   :  printBoard                                       *
*     Purpose         :  To print the current state of the board          *
*     Called by       :  Main                                             *
*     Functions Called:  None                                             *
*                                                                         *
**************************************************************************/

void  printBoard(ChessBoard x)
{
   int i,j;

   cout << endl;

   cout << endl << "-----------------------------------------" << endl;

   for (i = 2; i <= 9; i++)
   {
      for (j = 2; j <= 9; j++)
      {
	 cout << '|' << ' ' << setw(2) << setfill('0') << x[i][j] << ' ';
      }
      // end for

      cout << '|' << endl << "-----------------------------------------";
      
      cout << endl;
   }
   // end for

   return;
}

/**************************************************************************
*                                                                         *
*     Function Name   :  knightMoves                                      *
*     Purpose         :  To move the knight until he can no longer move   *
*     Called by       :  Main                                             *
*     Functions Called:  None                                             *
*                                                                         *
**************************************************************************/

void  knightMoves(ChessBoard x)
{

     int StartingRow, StartingColumn;
     cout << endl << "Please enter the starting row: ";
     cin >> StartingRow;
     cout << endl << "Please enter the starting column: ";
     cin >> StartingColumn;
     
     StartingRow = StartingRow+1;
     StartingColumn = StartingColumn+1;
  
     int NumberMove = 2;
     x[StartingRow][StartingColumn] = 1;
     bool FinishedMoving = false;
     int CurrentRow = StartingRow, CurrentColumn = StartingColumn;

     while (FinishedMoving == false)
     {
          if (x[CurrentRow-2][CurrentColumn+1] == 0)
          {
               CurrentRow = CurrentRow-2;
               CurrentColumn = CurrentColumn+1;
               x[CurrentRow][CurrentColumn] = NumberMove++;
               //printBoard(x);
          }
          else if (x[CurrentRow-1][CurrentColumn+2] == 0)
          {
               CurrentRow = CurrentRow-1;
               CurrentColumn = CurrentColumn+2;
               x[CurrentRow][CurrentColumn] = NumberMove++;
               //printBoard(x);
          }
          else if (x[CurrentRow+1][CurrentColumn+2] == 0)
          {
               CurrentRow = CurrentRow+1;
               CurrentColumn = CurrentColumn+2;
               x[CurrentRow][CurrentColumn] = NumberMove++;
               //printBoard(x);
          }
          else if (x[CurrentRow+2][CurrentColumn+1] == 0)
          {
               CurrentRow = CurrentRow+2;
               CurrentColumn = CurrentColumn+1;
               x[CurrentRow][CurrentColumn] = NumberMove++;
               //printBoard(x);
          }
          else if (x[CurrentRow+2][CurrentColumn-1] == 0)
          {
               CurrentRow = CurrentRow+2;
               CurrentColumn = CurrentColumn-1;
               x[CurrentRow][CurrentColumn] = NumberMove++;
               //printBoard(x);
          }
          else if (x[CurrentRow+1][CurrentColumn-2] == 0)
          {
               CurrentRow = CurrentRow+1;
               CurrentColumn = CurrentColumn-2;
               x[CurrentRow][CurrentColumn] = NumberMove++;
               //printBoard(x);
          }
          else if (x[CurrentRow-1][CurrentColumn-2] == 0)
          {
               CurrentRow = CurrentRow-1;
               CurrentColumn = CurrentColumn-2;
               x[CurrentRow][CurrentColumn] = NumberMove++;
               //printBoard(x);
          }
          else if (x[CurrentRow-2][CurrentColumn-1] == 0)
          {
               CurrentRow = CurrentRow-2;
               CurrentColumn = CurrentColumn-1;
               x[CurrentRow][CurrentColumn] = NumberMove++;
               //printBoard(x);
          }
          else
          {
               FinishedMoving = true;
          }
     }

     cout << endl << "The knight made " << NumberMove-1 << " moves before it was unable to move again.";

}	
	