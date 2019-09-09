

public class Donald18C 
{
	public static void main(String[] args) 
	{
		//time snowfall begins
		double T = 6.0;
		double Tmax = 12.0;
		double Tmin = 0.0;
		//rate snow falls
		double R = 1.0;
		//rate snow blower removes snow
		double Qmax = 20.0;
		double Qmin = 0.0;
		double Q = 0.0;
		double mile = 5280.0;
		double timeslice = 1/3600, tolerance = 0.00001;
		//double time = 0.0;
		
		
		Q = findQ(Tmax, Tmin);
		//System.out.println(Q);
		
		//double distanceTraveled = 5280.0;
		//double time = 3600.0;
		//double length = 0.0;
		//double depth = R*T + 1.0;
		
		//for (int i = 0; i < 3; i++) 
		while (true)
		{
		
			double distanceTraveled = 5280.0;
			double time = 3600.0;
			double length = 0.0;
			double depth = R*T + 1.0;
			Q = findQ(Tmax, Tmin);
			//System.out.println(Q);
			
			while ((7200.0 - time) > tolerance)
			{
				length = Q/depth;
				depth = depth + (R/3600);
				distanceTraveled = distanceTraveled + length;
				time = time + 1.0;
			}
			//System.out.println(length);
			//System.out.println(time);
			//System.out.println("distanceTraveled);
			//System.out.println(T);
			
			if ((distanceTraveled - (mile*1.5)) > tolerance)
			{
				//System.out.println("too far");
				Tmax = T;
				T = (Tmin + Tmax)/2;
			}
			else if (((mile*1.5) - distanceTraveled) > tolerance)
			{
				//System.out.println("too short");
				Tmin = T;
				T = (Tmin + Tmax)/2;
			}
			else
			{
				//System.out.println("perfect");
				//System.out.println(T);
				break;
			}

		
		}
		
		String startTime = getTime(T);
		System.out.println("Snow began to fall at " + startTime + " am.");
		
	}
	
	
	public static String getTime(double x)
	{
		double time = 12.0 - x;
		int hour = (int)(time);
		double mindec = time%hour;
		double min = mindec*60;
		min = Math.round(min);
		int min1 = (int)(min);
		
		if (min1 == 0)
			return hour + ":00";
		
		String Time = hour + ":" + min1;
		return Time;
	}
	
	public static double findQ(double Tmax, double Tmin)
	{
		double Qmax = 100.0;
		double Qmin = 0.0;
		double tolerance = 0.00001;
		double mile = 5280.0;
		double R = 1.0;
		double T = (Tmax + Tmin)/2;
		double Q = (Qmax + Qmin)/2;
		
		while (true)
		{
			double time = 0.0;
			double distanceTraveled = 0.0;
			double depth = R*T;
			while ((3600.0 - time) > tolerance)
			{
				double length = Q/depth;
				depth = depth + (R/3600);
				distanceTraveled = distanceTraveled + length;
				time = time + 1.0;
				
			}
			//System.out.println(distanceTraveled);
			//System.out.println(Q);
			
			if ((distanceTraveled - mile) > tolerance)
			{
				Qmax = Q;
				Q = (Qmax + Qmin)/2;
			}
			else if ((mile - distanceTraveled) > tolerance)
			{
				Qmin = Q;
				Q = (Qmax + Qmin)/2;
			}
			else
			{
				//System.out.println(time);
				break;
			}

		}
		return Q;
	}
}
