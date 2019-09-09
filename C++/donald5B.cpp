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



int HashFunction(string&);

int main()
{
	fstream RandomFile;
	RandomFile.open("Random.dat", ios::in | ios::out);
	
		char userSelection;
		
		cout << "A   add a record (to random.dat)" << endl;
		cout << "D   delete a record" << endl;
		cout << "S   display a record" << endl;
		cout << "N   change last name" << endl;
		cout << "M   change major" << endl;
		cout << "H   change hours" << endl;
		cout << "G   change GPA" << endl;
		cout << "X   stop the program" << endl << endl;
		cin >> userSelection;
		
		if (userSelection == 'X' || userSelection == 'x')
		{
			//exitbool = true;
			cout << endl << "You have chosen to exit the program!!" << endl;
			return 0;
		}
		else if (userSelection == 'A' || userSelection == 'a') 
		{
			string social;
			string last;
			string first;
			string major;
			string hours;
			string gpa;
			string finalRecord;
			cout << endl << "You have chosen to add a record. Please enter the social security number." << endl << endl;
			cin >> social;
			cout << endl << "Please enter the last name." << endl << endl;
			cin >> last;
			cout << endl << "Please enter the first name." << endl << endl;
			cin >> first;
			cout << endl << "Please enter the major." << endl << endl;
			cin >> major;
			cout << endl << "Please enter the number of hours." << endl << endl;
			cin >> hours;
			cout << endl << "Please enter the GPA" << endl << endl;
			cin >> gpa;
			
			finalRecord = social;
			while (last.length() < 10)
			{
				last = last + " ";
			}
			while (first.length() < 10)
			{
				first = first + " ";
			}
			while (major.length() < 20)
			{
				major = major + " ";
			}
			while (hours.length() < 3)
			{
				hours = hours + " ";
			}
		
			finalRecord = finalRecord + last + first + major + hours + gpa;
			//cout << finalRecord;
			
			int addhash = HashFunction(social);
			string addhashstring;
			RandomFile.seekg(58*addhash, ios::beg);
			RandomFile >> addhashstring;
			//cout << hashstring << endl;
			
			if (addhashstring.substr(0, 1) == "*" || addhashstring.substr(0, 1) == "#")
			{
				RandomFile.seekp(58*addhash, ios::beg);
				RandomFile << finalRecord;
			}
			else
			{
				while(addhashstring.substr(0, 1) != "*" && addhashstring.substr(0, 1) != "#")
				{
					addhash++;
					if (addhash == 60)
					{
						addhash = 0;
					}
					RandomFile.seekg(58*addhash, ios::beg);
					RandomFile >> addhashstring;
				}
				RandomFile.seekp(58*addhash, ios::beg);
				RandomFile << finalRecord;
			}
			
			
			
		}
		else if (userSelection == 'D' || userSelection == 'd')
		{
			string social;
			cout << endl << "You have chosen to delete a record. Please enter the social security number." << endl << endl;
			cin >> social;
			
			string deletedRecord = "#********                                          00.00";
			
			int HashCode = HashFunction(social);
			string hashstring;
			
			int deletecounter = 0;
			
			RandomFile.seekg(58*HashCode, ios::beg);
			RandomFile >> hashstring;
			if (hashstring.substr(0, 9) == social)
			{
				RandomFile.seekp(58*HashCode, ios::beg);
				RandomFile << deletedRecord;
			}
			else 
			{
				while(hashstring.substr(0, 9) != social)
				{
					deletecounter++;
					HashCode++;
					if (HashCode == 60)
					{
						HashCode = 0;
					}
					RandomFile.seekg(58*HashCode, ios::beg);
					RandomFile >> hashstring;
					
					if (deletecounter > 60)
					{
						cout << endl << "This record is not in the file";
						return 0;
					}
				}
				RandomFile.seekp(58*HashCode, ios::beg);
				RandomFile << deletedRecord;
			}
		}
		else if (userSelection == 'S' || userSelection == 's')
		{
			string social;
			cout << endl << "You have chosen to display a record. Please enter the social security number." << endl << endl;
			cin >> social;
			
			string displayRecord;
			
			int HashCode = HashFunction(social);
			string hashstring;
			
			int displaycounter = 0;
			
			RandomFile.seekg(58*HashCode, ios::beg);
			RandomFile >> hashstring;
			if (hashstring.substr(0, 9) == social)
			{
				RandomFile.seekp(58*HashCode, ios::beg);
				getline(RandomFile, displayRecord);
			}
			else 
			{
				while(hashstring.substr(0, 9) != social)
				{
					displaycounter++;
					HashCode++;
					if (HashCode == 60)
					{
						HashCode = 0;
					}
					RandomFile.seekg(58*HashCode, ios::beg);
					RandomFile >> hashstring;
					
					if (displaycounter > 60)
					{
						cout << endl << "This record is not in the file";
						return 0;
					}
				}
				RandomFile.seekp(58*HashCode, ios::beg);
				getline(RandomFile, displayRecord);
			}
			
			//cout << endl << displayRecord << endl;
		
		}
		else if (userSelection == 'N' || userSelection == 'n')
		{
			string social;
			string last;
			cout << endl << "You have chosen to change a last name. Please enter the social security number." << endl << endl;
			cin >> social;
			cout << endl << "Please enter the new last name." << endl << endl;
			cin >> last;
			
			while (last.length() < 10)
			{
				last = last + " ";
			}
			
			int HashCode = HashFunction(social);
			string hashstring;
			string changerecord;
			string beg;
			string end;
			string newrec;
			
			int namecounter = 0;
			
			RandomFile.seekg(58*HashCode, ios::beg);
			RandomFile >> hashstring;
			if (hashstring.substr(0, 9) == social)
			{
				RandomFile.seekp(58*HashCode, ios::beg);
				getline(RandomFile, changerecord);
				RandomFile.seekg(58*HashCode, ios::beg);
				beg = changerecord.substr(0, 9);
				end = changerecord.substr(19, 37);
				newrec = beg + last + end;
				RandomFile << newrec;
			}
			else 
			{
				while(hashstring.substr(0, 9) != social)
				{
					namecounter++;
					HashCode++;
					if (HashCode == 60)
					{
						HashCode = 0;
					}
					RandomFile.seekg(58*HashCode, ios::beg);
					RandomFile >> hashstring;
					
					if (namecounter > 60)
					{
						cout << endl << "This record is not in the file";
						return 0;
					}
				}
				
				RandomFile.seekp(58*HashCode, ios::beg);
				getline(RandomFile, changerecord);
				RandomFile.seekg(58*HashCode, ios::beg);
				beg = changerecord.substr(0, 9);
				end = changerecord.substr(19, 37);
				newrec = beg + last + end;
				RandomFile << newrec;
				
			}
			
		}
		
		else if (userSelection == 'M' || userSelection == 'm')
		{
			string social;
			string major;
			cout << endl << "You have chosen to change a major. Please enter the social security number." << endl << endl;
			cin >> social;
			cout << endl << "Please enter the new major." << endl << endl;
			cin >> major;
			
			while (major.length() < 20)
			{
				major = major + " ";
			}
			
			int HashCode = HashFunction(social);
			string hashstring;
			string changerecord;
			string beg;
			string end;
			string newrec;
			
			int majorcounter = 0;
			
			RandomFile.seekg(58*HashCode, ios::beg);
			RandomFile >> hashstring;
			if (hashstring.substr(0, 9) == social)
			{
				RandomFile.seekp(58*HashCode, ios::beg);
				getline(RandomFile, changerecord);
				RandomFile.seekg(58*HashCode, ios::beg);
				beg = changerecord.substr(0, 29);
				end = changerecord.substr(49, 7);
				newrec = beg + major + end;
				RandomFile << newrec;
			}
			else 
			{
				while(hashstring.substr(0, 9) != social)
				{
					majorcounter++;
					HashCode++;
					if (HashCode == 60)
					{
						HashCode = 0;
					}
					RandomFile.seekg(58*HashCode, ios::beg);
					RandomFile >> hashstring;
					
					if (majorcounter > 60)
					{
						cout << endl << "This record is not in the file";
						return 0;
					}
				}
				
				RandomFile.seekp(58*HashCode, ios::beg);
				getline(RandomFile, changerecord);
				RandomFile.seekg(58*HashCode, ios::beg);
				beg = changerecord.substr(0, 29);
				end = changerecord.substr(49, 7);
				newrec = beg + major + end;
				RandomFile << newrec;
				
			}
			
		}
		
		else if (userSelection == 'H' || userSelection == 'h')
		{
			string social;
			string hours;
			cout << endl << "You have chosen to change a students hours. Please enter the social security number." << endl << endl;
			cin >> social;
			cout << endl << "Please enter the new hour number." << endl << endl;
			cin >> hours;
			
			while (hours.length() < 3)
			{
				hours = hours + " ";
			}
			
			int HashCode = HashFunction(social);
			string hashstring;
			string changerecord;
			string beg;
			string end;
			string newrec;
			
			int hourcounter = 0;
			
			RandomFile.seekg(58*HashCode, ios::beg);
			RandomFile >> hashstring;
			if (hashstring.substr(0, 9) == social)
			{
				RandomFile.seekp(58*HashCode, ios::beg);
				getline(RandomFile, changerecord);
				RandomFile.seekg(58*HashCode, ios::beg);
				beg = changerecord.substr(0, 49);
				end = changerecord.substr(52, 4);
				newrec = beg + hours + end;
				RandomFile << newrec;
			}
			else 
			{
				while(hashstring.substr(0, 9) != social)
				{
					hourcounter++;
					HashCode++;
					if (HashCode == 60)
					{
						HashCode = 0;
					}
					RandomFile.seekg(58*HashCode, ios::beg);
					RandomFile >> hashstring;
					
					if (hourcounter > 60)
					{
						cout << endl << "This record is not in the file";
						return 0;
					}
				}
				
				RandomFile.seekp(58*HashCode, ios::beg);
				getline(RandomFile, changerecord);
				RandomFile.seekg(58*HashCode, ios::beg);
				beg = changerecord.substr(0, 49);
				end = changerecord.substr(52, 4);
				newrec = beg + hours + end;
				RandomFile << newrec;
				
			}
			
		}
		
		else if (userSelection == 'G' || userSelection == 'g')
		{
			string social;
			string gpa;
			cout << endl << "You have chosen to change a students GPA. Please enter the social security number." << endl << endl;
			cin >> social;
			cout << endl << "Please enter the new GPA." << endl << endl;
			cin >> gpa;
			
			int HashCode = HashFunction(social);
			string hashstring;
			string changerecord;
			string beg;
			string newrec;
			
			int gpacounter = 0;
			
			RandomFile.seekg(58*HashCode, ios::beg);
			RandomFile >> hashstring;
			if (hashstring.substr(0, 9) == social)
			{
				RandomFile.seekp(58*HashCode, ios::beg);
				getline(RandomFile, changerecord);
				RandomFile.seekg(58*HashCode, ios::beg);
				beg = changerecord.substr(0, 52);
				newrec = beg + gpa;
				RandomFile << newrec;
			}
			else 
			{
				while(hashstring.substr(0, 9) != social)
				{
					gpacounter++;
					HashCode++;
					if (HashCode == 60)
					{
						HashCode = 0;
					}
					RandomFile.seekg(58*HashCode, ios::beg);
					RandomFile >> hashstring;
					
					if (gpacounter > 60)
					{
						cout << endl << "This record is not in the file";
						return 0;
					}
				}
				
				RandomFile.seekp(58*HashCode, ios::beg);
				getline(RandomFile, changerecord);
				RandomFile.seekg(58*HashCode, ios::beg);
				beg = changerecord.substr(0, 52);
				newrec = beg + gpa;
				RandomFile << newrec;
				
			}
			
		}
		
		
	
	RandomFile.close();
}	

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










