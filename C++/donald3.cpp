/**********************************************************************************
*                                                                                 *
*     Program Filename:  donald3.cpp                                              *
*     Author          :  Evan Donald                                              *
*     Date Written    :  October 5, 2017                                          *
*     Purpose         :  Reads records and produces output to file                *
*     Input from      :  Program3.dat                                             *
*     Output to       :  Louie.dat                                                *
*                                                                                 *
**********************************************************************************/

#include <iostream.h>
#include <iomanip.h>
#include <conio.h>
#include <time.h>
#include <ctype>
#include <string.h>
#include <fstream.h>
#include <donald.h>

struct NameRecord
{
     string LastName;
     string FirstName;
     string MidInit;
};

struct StudentRecord
{
     NameRecord Name;
     string SSNumber;
     string Major;
     int Programs[6];
     int Tests[3];
     int FinalExam;
};

struct Output
{
     string Last4ofSocial;
     double NumGrade;
     string LetGrade;
};

void OpenInFile(ifstream&);
void GetNextRecord(StudentRecord&, ifstream&);
void CalculatingGrades(Output[]);
void CloseInFile(ifstream&);
void OpenOutFile(ofstream&);
void CloseOutFile(ofstream&);

int main()
{
     ifstream InFile;
     ofstream louie;
     StudentRecord X;
     StudentRecord StudentRecordArray[50];
     Output O;
     Output OutputArray[50];

     OpenInFile(InFile);
	 OpenOutFile(louie);

     int counter = 0;

     GetNextRecord(X, InFile);
     while (!InFile.eof())
     { 
          StudentRecordArray[counter] = X;
          counter++;
          GetNextRecord(X, InFile);
     }
	 
     //curving grades
     int MaxGradeOnTest1 = 0;
     for (int i = 0; i < counter; i++)
     {
          if (StudentRecordArray[i].Tests[0] >= MaxGradeOnTest1)
          {
                MaxGradeOnTest1 = StudentRecordArray[i].Tests[0];
          }
     }
     int curve1 = 100 - MaxGradeOnTest1;
     for (int i = 0; i < counter; i++)
     {
          StudentRecordArray[i].Tests[0] = StudentRecordArray[i].Tests[0] + curve1;
     }
   
     int MaxGradeOnTest2 = 0;
     for (int i = 0; i < counter; i++)
     {
          if (StudentRecordArray[i].Tests[1] >= MaxGradeOnTest2)
          {
                MaxGradeOnTest2 = StudentRecordArray[i].Tests[1];
          }
     }
     int curve2 = 100 - MaxGradeOnTest2;
     for (int i = 0; i < counter; i++)
     {
          StudentRecordArray[i].Tests[1] = StudentRecordArray[i].Tests[1] + curve2;
     }

     int MaxGradeOnTest3 = 0;
     for (int i = 0; i < counter; i++)
     {
          if (StudentRecordArray[i].Tests[2] >= MaxGradeOnTest3)
          {
                MaxGradeOnTest3 = StudentRecordArray[i].Tests[2];
          }
     }
     int curve3 = 100 - MaxGradeOnTest3;
     for (int i = 0; i < counter; i++)
     {
          StudentRecordArray[i].Tests[2] = StudentRecordArray[i].Tests[2] + curve3;
     }
     
	 
     //making output
     double grade;
     for (int i = 0; i < counter; i++)
     {
          OutputArray[i].Last4ofSocial = StudentRecordArray[i].SSNumber.substr(5, 4);
          
          grade = 0;
          for (int j = 0; j < 6; j++)
          {
               grade = grade + (StudentRecordArray[i].Programs[j]);
          }
          OutputArray[i].NumGrade = ((grade*5)/6)*.3;

          grade = 0;
          for (int j = 0; j < 3; j++)
          {
               grade = grade + (StudentRecordArray[i].Tests[j]);
          }
          OutputArray[i].NumGrade = OutputArray[i].NumGrade + (grade/3)*.4;

          grade = 0;
          grade = grade + (StudentRecordArray[i].FinalExam);
          OutputArray[i].NumGrade = OutputArray[i].NumGrade + grade*.3;
     }

     for (int i = 0; i < counter; i++)
     {
          if (OutputArray[i].NumGrade >= 90)
          {
               OutputArray[i].LetGrade = "A";
          }
          if (OutputArray[i].NumGrade >= 80 && OutputArray[i].NumGrade < 90)
          {
               OutputArray[i].LetGrade = "B";
          } 
          if (OutputArray[i].NumGrade >= 70 && OutputArray[i].NumGrade < 80)
          {
               OutputArray[i].LetGrade = "C";
          } 
          if (OutputArray[i].NumGrade >= 60 && OutputArray[i].NumGrade < 70)
          {
               OutputArray[i].LetGrade = "D";
          } 
          if (OutputArray[i].NumGrade < 60)
          {
               OutputArray[i].LetGrade = "F";
          }      
     }
     
	 Output temp;
	 for (int i = counter; i >= 0; i--)
     {
		 for (int j = 0; j < i; j++)
		 {
			 if (OutputArray[j-1].NumGrade < OutputArray[j].NumGrade)
			 {
				 temp = OutputArray[j];
				 OutputArray[j] = OutputArray[j-1];
				 OutputArray[j-1] = temp;
			 }
	     }
     }
	 
     //printing to screen
	 //louie << setprecision(2) << fixed;
	 for (int i = 0; i < counter; i++)
     { 
		  louie << OutputArray[i].Last4ofSocial << '\t' << OutputArray[i].NumGrade << '\t' << '\t' << OutputArray[i].LetGrade << endl;
     }
	 
     CloseInFile(InFile);
	 CloseOutFile(louie);

}


/**********************************************************************************
*                                                                                 *
*     Function Name   :  OpenInFile                                               *
*     Purpose         :  To open the input file                                   *
*     Called by       :  Main                                                     *
*     Functions Called:  None                                                     *
*                                                                                 *
**********************************************************************************/

#include <stdlib.h>
#include <iostream.h>

void OpenInFile(ifstream& x)
{
     x.open("Program3.dat", ios::in);

     if(x.fail())
     {
          cout << "Failure to open Program3.dat";
          exit(-1);
     }
     return;
}


/**********************************************************************************
*                                                                                 *
*     Function Name   :  CloseInFile                                              *
*     Purpose         :  To close the input file                                  *
*     Called by       :  Main                                                     *
*     Functions Called:  None                                                     *
*                                                                                 *
**********************************************************************************/

void CloseInFile(ifstream& x)
{
     x.close();
     return;
}


/**********************************************************************************
*                                                                                 *
*     Function Name   :  GetNextRecord                                            *
*     Purpose         :  Get the next record from input file                      *
*     Called by       :  Main                                                     *
*     Functions Called:  None                                                     *
*                                                                                 *
**********************************************************************************/

void GetNextRecord(StudentRecord& X, ifstream& InputFile)
{
     int j;
     string NextRecord;

     getline(InputFile, NextRecord);
     if (InputFile.eof())
     {
          return;
     }
     
     X.Name.LastName = NextRecord.substr(0, 15);
     X.Name.FirstName = NextRecord.substr(15, 10);
     X.Name.MidInit = NextRecord.substr(25, 1);
     X.SSNumber = NextRecord.substr(26, 9);
     X.Major = NextRecord.substr(35, 20);

     for (j = 0; j <= 5; j++)
     {
         X.Programs[j] = atoi(NextRecord.substr(55+j*2, 2).c_str());
     }

     for (j = 0; j <= 2; j++)
     {
         X.Tests[j] = atoi(NextRecord.substr(67+j*3, 3).c_str());
     }

     X.FinalExam = atoi(NextRecord.substr(76, 3).c_str());
     
     return;
}


/**********************************************************************************
*                                                                                 *
*     Function Name   :  OpenOutFile                                              *
*     Purpose         :  To open the output file                                  *
*     Called by       :  Main                                                     *
*     Functions Called:  None                                                     *
*                                                                                 *
**********************************************************************************/

#include <stdlib.h>
#include <iostream.h>

void OpenOutFile(ofstream& x)
{
     x.open("louie.dat", ios::in);

     if(x.fail())
     {
          cout << "Failure to open louie.dat";
          exit(-1);
     }
     return;
}


/**********************************************************************************
*                                                                                 *
*     Function Name   :  CloseOutFile                                             *
*     Purpose         :  To close the output file                                 *
*     Called by       :  Main                                                     *
*     Functions Called:  None                                                     *
*                                                                                 *
**********************************************************************************/

void CloseOutFile(ofstream& x)
{
     x.close();
     return;
}














