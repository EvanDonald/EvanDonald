package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Donald {

    static Queue<Double> plow1 = new LinkedList<Double>();
    static Queue<Double> plow2 = new LinkedList<Double>();
    static double plow1counter;
    static double plow2counter;
    static double snowfall = 1;

    public static void main(String[] args) {
        double  depth, plowRate, A, B, input, snowplow1 =0, snowplow2=0, snowplow3=0, startTime, meetTime, plow2depth, plow3depth;
        double  timeSlice=(1.0/3600.0), tolerance=0.000001, distanceTolerance = 0.015;
        int time, time2;
        boolean rightTime = false;
        plowRate = 1.5/3600;
        A = 0;
        B = 12;
        meetTime = 0;

        while (!rightTime) {
            depth = 0;
            startTime = (B + A) / 2;
            meetTime = 0;
            snowplow1 = 0;
            snowplow2 = 0;
            snowplow3 = 0;
            plow1 = new LinkedList<>();
            plow2 = new LinkedList<>();
            plow1counter = 0;
            plow2counter = 0;
            while (startTime > 0) {
                depth += snowfall * timeSlice;
                startTime -= timeSlice;
            }

            while (meetTime < 1){
                depth += (snowfall * timeSlice);
                snowplow1 += (plowRate / depth);
                meetTime += timeSlice;
                plow1.add(snowplow1);
            }

            while(meetTime < 2){
                plow1counter--;
                meetTime += timeSlice;
                depth += (snowfall * timeSlice);
                snowplow1 += (plowRate / depth);
                plow1.add(snowplow1);

                plow2depth = (plow1.peek()-snowplow2) * (snowfall * ((3600.0 - plow1counter)/3600.0));
                if (plow2depth > plowRate){
                    snowplow2 += (plow1.peek()-snowplow2)*(plowRate/plow2depth);
                    plow2.add(snowplow2);
                }else {
                    snowplow2 = depthplow1(plow2depth, snowplow2, plowRate);
                    plow2.add(snowplow2);
                }

                if (plow1.isEmpty()) {
                    B = (B + A) / 2;
                    break;
                }
            }

            while(!plow1.isEmpty() && !plow2.isEmpty()){
                plow1counter--;
                plow2counter--;
                meetTime += timeSlice;
                depth += (snowfall * timeSlice);
                snowplow1 += (plowRate / depth);
                plow1.add(snowplow1);

                plow2depth = (plow1.peek()-snowplow2) * (snowfall * ((3600.0 - plow1counter)/3600.0));
                if (plow2depth > plowRate){
                    snowplow2 += (plow1.peek()-snowplow2) * (plowRate/plow2depth);
                    plow2.add(snowplow2);
                }else {
                    snowplow2 = depthplow1(plow2depth, snowplow2, plowRate);
                    plow2.add(snowplow2);
                }

                plow3depth = (plow2.peek()-snowplow3) * (snowfall * ((3600.0 - plow2counter)/3600.0));
                if (plow3depth > plowRate){
                    snowplow3 += (plow2.peek()-snowplow3)* (plowRate/plow3depth);
                }else {
                    snowplow3 = depthplow2(plow3depth, snowplow3, plowRate);
                }
            }

            if (snowplow1 - snowplow2 < distanceTolerance && snowplow2 - snowplow3 < distanceTolerance){
                rightTime = true;
            }else if (plow1.isEmpty() && !plow2.isEmpty() && snowplow3 != 0){
                B = (B + A) / 2;
            }else if (!plow1.isEmpty() && plow2.isEmpty() && snowplow3 != 0){
                A = ((B + A) / 2);
            }

        }
        double temp = ((A+B)/2);
        time = (int)(12-(temp));
        time2 = (int) (((12 - temp) - (double) time) * 60 + 0.5);
        System.out.println("Start time: " + time+":"+time2);

        time = (int) meetTime;
        time2 = (int) ((meetTime - time) * 60 + .5);
        System.out.println("Meet time: " + time +":"+time2);
    }

    public static double depthplow1(double totalDepth, double snowplowDistance, double plowRate){
        if (plowRate - totalDepth < 0){
            snowplowDistance += (plow1.peek()-snowplowDistance) * (plowRate/totalDepth);
            return snowplowDistance;
        }else{
            snowplowDistance = plow1.poll();
            if (plow1.isEmpty()){
                return snowplowDistance;
            }
            plow1counter++;
            plowRate -= totalDepth;
            totalDepth = (plow1.peek()-snowplowDistance) * (snowfall * ((3600.0 - plow1counter)/3600.0));
            return depthplow1(totalDepth,snowplowDistance,plowRate);
        }
    }

    public static double depthplow2(double totalDepth, double snowplowDistance, double plowRate){
        if (plowRate - totalDepth < 0){
            snowplowDistance += (plow2.peek()-snowplowDistance) * (plowRate/totalDepth);
            return snowplowDistance;
        }else{
            snowplowDistance = plow2.poll();
            if (plow2.isEmpty()){
                return snowplowDistance;
            }
            plow2counter++;
            plowRate -= totalDepth;
            totalDepth = (plow2.peek()-snowplowDistance) * (snowfall * ((3600.0 - plow2counter)/3600.0));
            return depthplow2(totalDepth,snowplowDistance,plowRate);
        }
    }

}

