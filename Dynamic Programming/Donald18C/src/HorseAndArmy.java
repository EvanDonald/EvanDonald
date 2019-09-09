/**************************************************************************
*                                                                         *
*     Program Filename:  HorseAndArmy.java                                *
*     Author          :  John B. Student                                  *
*     Date Written    :  April 1, 2019                                    *
*     Purpose         :  Demonstrate predictor-corrector solution         *
*     Input from      :  None                                             *
*     Output to       :  Screen                                           *
*                                                                         *
**************************************************************************/

public class HorseAndArmy
{
   public static void main(String[] args) 
   {
      double  Horse, Front, Rear, HorseSpeed, ArmySpeed, A, B, Distance;
      double  TimeSlice=0.0000001, Tolerance=0.00001;
   
      A = 8.0;
      B = 25.0;

      for (;;)
      {
         Front = 40.0;
         Rear = Horse = Distance = 0.0; 
         HorseSpeed = (A+B)/2.0;
         ArmySpeed = 10.0;

         while (Horse < Front)
         {
            Front = Front + TimeSlice*ArmySpeed;
            Horse = Horse + TimeSlice*HorseSpeed;
            Distance = Distance + TimeSlice*HorseSpeed;
         }
         // end while
                    
         Rear = Front - 40.0;

         while (Horse > Rear)
         {
            Rear = Rear + TimeSlice*ArmySpeed;
            Horse = Horse - TimeSlice*HorseSpeed;
            Distance = Distance + TimeSlice*HorseSpeed;
         }
         // end while

         if (Rear - 40.0 > Tolerance)
            A = HorseSpeed;
         else if (40.0 - Rear > Tolerance)
            B = HorseSpeed;
         else
            break;
         // end if
      }
      // end for

      System.out.println(ArmySpeed);
      System.out.println(HorseSpeed);
      System.out.println(ArmySpeed*(1+Math.sqrt(2)));

      System.out.println(Distance);
   }
   // end main method
}
// end class HoresAndArmy
