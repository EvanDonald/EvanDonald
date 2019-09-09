/**********************************************************************************
*                                                                                 *
*     Program Filename:  donald5A.cpp                                             *
*     Author          :  Evan Donald                                              *
*     Date Written    :  November 27, 2017                                        *
*     Purpose         :  Reads records and produces output to random access file  *
*     Input from      :  Program5A.dat                                            *
*     Output to       :  random.dat                                               *
*                                                                                 *
**********************************************************************************/

#include <iostream.h>
#include <fstream.h>


void OpenInFile(ifstream&);
void GetNextRecord(string&, ifstream&);
int HashFunction(string&);
void CloseInFile(ifstream&);

int main()
{
	
	ifstream Program5A;
	fstream RandomFile;
	string X;
	int HashCode;
	
	OpenInFile(Program5A);
	RandomFile.open("Random.dat", ios::in | ios::out);
	
	for (int i = 0; i < 60; i++)
	{
		RandomFile << "*********                                          00.00";
		RandomFile << endl;
	}
	
	
	getline(Program5A, X);
	//cout << X << endl;
	while (!Program5A.eof())
	{
		
		HashCode = 0;
		HashCode = HashFunction(X);
		string hashstring;
		RandomFile.seekg(58*HashCode, ios::beg);
		RandomFile >> hashstring;
		//cout << hashstring << endl;
		
		if (hashstring.substr(0, 9) == "*********")
		{
			RandomFile.seekp(58*HashCode, ios::beg);
			RandomFile << X;
			//cout << X << endl;
		}
		else
		{
			while(hashstring.substr(0, 9) != "*********")
			{
				HashCode++;
				if (HashCode == 60)
				{
					HashCode = 0;
				}
				RandomFile.seekg(58*HashCode, ios::beg);
				RandomFile >> hashstring;
			}
			RandomFile.seekp(58*HashCode, ios::beg);
			RandomFile << X;
			//cout << X << endl;
		}
		
		
		getline(Program5A, X);
		//cout << X << endl;
	}
	
	CloseInFile(Program5A);
	RandomFile.close();
	
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
     x.open("Program5A.dat", ios::in);

     if(x.fail())
     {
          cout << "Failure to open Program5A.dat";
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

/*void GetNextRecord(string& X, ifstream& InputFile)
{
     int j;
     string NextRecord;

     getline(InputFile, NextRecord);
     if (InputFile.eof())
     {
          return;
     }
     
     X = NextRecord;

     
     return;
}*/



/**********************************************************************************
*                                                                                 *
*     Function Name   :  HashFunction                                             *
*     Purpose         :  Get the hash code                                        *
*     Called by       :  Main                                                     *
*     Functions Called:  None                                                     *
*                                                                                 *
**********************************************************************************/

int HashFunction(string& X)
{
	int total = 0;
    for (int i = 0; i < 9; i++)
	{
		total = total + X[i];
	}
    int hash = total % 60;
	return hash;	
}






