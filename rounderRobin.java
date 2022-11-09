import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class rounderRobin {
    public static void main(String args[]){
        ArrayList<int[]> procArray = new ArrayList<int[]>();
        ArrayList<int[]> finalArray = new ArrayList<int[]>();
        int timeQuan = 10;
        int numOfProc = 6;

        int[] p1 = {0,15,8,0};
        int[] p2 = {0,20,3,0};
        int[] p3 = {20,20,4,0};
        int[] p4 = {25,20,4,0};
        int[] p5 = {45,5,5,0};
        int[] p6 = {55,15,5,0};
        procArray.add(p1);
        procArray.add(p2);
        procArray.add(p3);
        procArray.add(p4);
        procArray.add(p5);
        procArray.add(p6);

        int completedProc = 0;
        int clockTime = 0;
        ArrayList<int[]> prioList = new ArrayList<int[]>();
        while (numOfProc > completedProc){ // 0 = at , 1 = bt, 2 = prio
            if (clockTime > 100){
                break;
            }
            System.out.println("Completed Procecess: " + completedProc);
            System.out.println("Time is currently at " + clockTime + "ms");
            sortList(0, procArray); //sorts based of arrival time
            int endTime = 0;

            for (int i = 0; i < procArray.size(); i++){ //adds processes to queue for current clockTime
                if (procArray.get(i)[0] == clockTime){
                    prioList.add(procArray.get(i));
                }
            }

            int lastIndex = prioList.size()+completedProc;

            if (lastIndex == numOfProc){
                int[] increment = prioList.get(0);
                increment[3] += timeQuan;
                prioList.set(0, increment);
                finalArray.add(prioList.get(0));
                break;
            }

            endTime = procArray.get(lastIndex)[0] - clockTime;
            if (endTime < 0) {

                clockTime++;
                for (int i = 0; i < prioList.size(); i++){
                    int[] increment = prioList.get(i);
                    increment[3]++;
                    prioList.set(i, increment);
                }

                System.out.println("Completed Procecess: " + completedProc);
                System.out.println("Time is currently at " + clockTime + "ms");
                continue;
            }
            sortList(2, prioList); // sorts off priority
            int roundRobinIndex = 0;
            int index = roundRobinIndex;
            for (int i = endTime; i > 0 && prioList.size() > 0; i--){


                int[] change = prioList.get(index);
                index++;
                if (index > prioList.size()-1){
                    index = 0;
                }
                change[1] -= timeQuan;
                clockTime++;
                for (int j = 0; j < prioList.size(); j++){
                    int[] increment = prioList.get(j);
                    if (j == index){
                        increment[3] += timeQuan;
                    }
                    increment[3]++;
                    prioList.set(j, increment);
                }
                System.out.println("Completed Procecess: " + completedProc);
                System.out.println("Time is currently at " + clockTime + "ms");
                if (change[1] <= 0){
                    change[1] = 0;
                    finalArray.add(prioList.get(index));
                    prioList.remove(index);
                    completedProc++;
                } else {
                    prioList.set(index,change);
                }
                roundRobinIndex++;
            }

            clockTime++;
            for (int i = 0; i < prioList.size(); i++){
                int[] increment = prioList.get(i);
                increment[3]++;
                prioList.set(i, increment);
            }


        }
        int totalWaitTime = 0;
        for (int[] is : finalArray) {
            System.out.println(Arrays.toString(is));
        }
        for (int i = 0; i< finalArray.size(); i++){
            totalWaitTime += finalArray.get(i)[3];
        }
        System.out.println("Average Wait Time is: " + (double)totalWaitTime/numOfProc);
    }

     public static void sortList(int INDEX, ArrayList<int[]> procArray){
        Collections.sort(procArray, new Comparator<int[]>() { //sorts array in terms of arrival time
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[INDEX], o2[INDEX]);
            }
        });
    }
}
