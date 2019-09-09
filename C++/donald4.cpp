/**********************************************************************************
*                                                                                 *
*     Program Filename:  donald4.cpp                                              *
*     Author          :  Evan Donald                                              *
*     Date Written    :  October 26, 2017                                         *
*     Purpose         :  Simulates the movement of a billiards ball               *
*     Input from      :  None                                                     *
*     Output to       :  Screen                                                   *
*                                                                                 *
**********************************************************************************/

#include <iostream.h>
#include "Point.h"

int main()
{
	double x;
	double y;
	int N;
	
	cout << "Please enter your x coordinate: " << endl;
	cin >> x;
	cout << "Please enter your y coordinate: " << endl;
	cin >> y;
	cout << "Please enter the number of collisions you would like: " << endl;
	cin >> N;
	
	cout << endl << endl;
	
	Point BilliardBallLocation;
	BilliardBallLocation.setX(x);
	BilliardBallLocation.setY(y);
	
	//cout << BilliardBallLocation.getX() << endl << BilliardBallLocation.getY() << endl;
	
	int counter = 0;
	int direction = 45;
	double topRail = 45.00, bottomRail = 0.00, rightRail = 125.00, leftRail = 0.00;
	
	while (counter < N)
	{
		BilliardBallLocation.moveInDirection(direction, 0.001);

		if (direction == 45)
		{
			//top and right same time
			if (BilliardBallLocation.getY() > topRail && BilliardBallLocation.getX() > rightRail)
			{
				BilliardBallLocation.setY(topRail);
				BilliardBallLocation.setX(rightRail);
				direction = 225;
				cout << "TOP" << endl;
				cout << "RIGHT" << endl;
				counter++;
				counter++;
			}
			//45 top 315
			else if (BilliardBallLocation.getY() > topRail)
			{
				BilliardBallLocation.setY(topRail);
				direction = 315;
				cout << "TOP" << endl;
				counter++;
			}
			//45 right 135
			else if (BilliardBallLocation.getX() > rightRail)
			{
				BilliardBallLocation.setX(rightRail);
				direction = 135;
				cout << "RIGHT" << endl;
				counter++;
			}
		}
		
		if (direction == 315)
		{	
			//bottom and right same time
			if (BilliardBallLocation.getY() < bottomRail && BilliardBallLocation.getX() > rightRail)
			{
				BilliardBallLocation.setY(bottomRail);
				BilliardBallLocation.setX(rightRail);
				direction = 135;
				cout << "BOTTOM" << endl;
				cout << "RIGHT" << endl;
				counter++;
				counter++;
			}
			//315 bottom 45
			else if (BilliardBallLocation.getY() < bottomRail)
			{
				BilliardBallLocation.setY(bottomRail);
				direction = 45;
				cout << "BOTTOM" << endl;
				counter++;
			}
			//315 right 225
			else if (BilliardBallLocation.getX() > rightRail)
			{
				BilliardBallLocation.setX(rightRail);
				direction = 225;
				cout << "RIGHT" << endl;
				counter++;
			}
		}
		
		if (direction == 225)
		{
			//bottom and left same time
			if (BilliardBallLocation.getY() < bottomRail && BilliardBallLocation.getX() < leftRail)
			{
				BilliardBallLocation.setY(bottomRail);
				BilliardBallLocation.setX(leftRail);
				direction = 45;
				cout << "BOTTOM" << endl;
				cout << "LEFT" << endl;
				counter++;
				counter++;
			}
			//225 bottom 135
			else if (BilliardBallLocation.getY() < bottomRail)
			{
				BilliardBallLocation.setY(bottomRail);
				direction = 135;
				cout << "BOTTOM" << endl;
				counter++;
			}
			//225 left 315
			else if (BilliardBallLocation.getX() < leftRail)
			{
				BilliardBallLocation.setX(leftRail);
				direction = 315;
				cout << "LEFT" << endl;
				counter++;
			}
		}
		
		if (direction == 135)
		{
			//top and left same time
			if (BilliardBallLocation.getY() > topRail && BilliardBallLocation.getX() < leftRail)
			{
				BilliardBallLocation.setY(topRail);
				BilliardBallLocation.setX(leftRail);
				direction = 315;
				cout << "TOP" << endl;
				cout << "LEFT" << endl;
				counter++;
				counter++;
			}
		    //135 top 225
			else if (BilliardBallLocation.getY() > topRail)
			{
				BilliardBallLocation.setY(topRail);
				direction = 225;
				cout << "TOP" << endl;
				counter++;
			}
			//135 left 45
			else if (BilliardBallLocation.getX() < leftRail)
			{
				BilliardBallLocation.setX(leftRail);
				direction = 45;
				cout << "LEFT" << endl;
				counter++;
			}
		}
	}
}


























