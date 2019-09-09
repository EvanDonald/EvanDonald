/**************************************************************************
*                                                                         *
*     Program Filename:  donald1.cpp                                      *
*     Author          :  Evan Donald                                      *
*     Date Written    :  September 12, 2017                               *
*     Purpose         :  To show a credit card payment month by month     *
*     Input from      :  Keyboard                                         *
*     Output to       :  Screen                                           *
*                                                                         *
**************************************************************************/

#include <iostream.h>
#include <iomanip.h>
#include <conio.h>
#include <time.h>
#include <ctype>
#include <donald.h>

int main()
{
     double AmountOwed, TotalOwed, AnnualRate, ExtraPaid;
     
     cout << endl << "Please enter the amount owed on your payment: ";
     cin  >> AmountOwed;
     TotalOwed = AmountOwed;

     cout << endl << "Please enter the annual interest rate: ";
     cin  >> AnnualRate;

     cout << endl << "Please enter the additional amount you will pay beyond the minimum each month: ";
     cin  >> ExtraPaid;

     cout << setprecision(2) << fixed;
  
     int pg = 1;

     while (AmountOwed > 0)
     {

          cout << "Page " << pg << ":" << endl << "========";

          cout << endl << endl << "Amount Owed: " << TotalOwed;
          cout << endl << "Annual Interest Rate: " << AnnualRate;
          cout << endl << "Additional Payment Each Month: " << ExtraPaid << endl;

     
          cout << endl << "======================================================================================" << endl;
     


          cout << endl << endl << "MONTH" << setw(15) << "PAYMENT" << setw(15) << "PRINCIPAL" << setw(15) 
                                                                         << "INTEREST" << setw(15) << "BALANCE";
          cout << endl << "=====" << setw(15) << "=======" << setw(15) << "=========" << setw(15) 
                                                                         << "========" << setw(15) << "=======";
          
          int i;
         
          for (i = 1; i <= 12; i++)
          {
               double Payment, Principal, Interest, CurrentBalance = AmountOwed, NewBalance;
             
               Interest = CurrentBalance * ((AnnualRate*.01)/12);
               Payment = (CurrentBalance * .02) + ExtraPaid;
               Principal = Payment - Interest;
               NewBalance = CurrentBalance - Principal;
               AmountOwed = NewBalance;

               cout << endl << setw(3) << i + ((pg-1)*12) << ".";
               cout << setw(15) << Payment << setw(14) << Principal << setw(16) << Interest << setw(16) << CurrentBalance;

               if (Principal > CurrentBalance)
               {
                    cout << endl << setw(3) << (i + ((pg-1)*12)) + 1 << ".";
                    cout << setw(15) << CurrentBalance + Interest << setw(14) << CurrentBalance << 
                                                                          setw(16) << Interest << setw(16) << "0.00";
                    
                    cout << endl << "You have paid off the debt";
                    return 0;
               }

          }

          
          char Continue;
           
          cout << endl << endl << endl << "Please enter 'c' to show next set of months: " << endl << endl << endl;
          
          cin  >> Continue;
          if (Continue != 'c')
          {
               return 0;
          }
          
          pg++;
     }
              


}

	
	