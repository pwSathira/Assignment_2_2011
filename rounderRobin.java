import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class rounderRobin {
    public static void main(String args[]){
        ArrayList<int[]> procArray = new ArrayList<int[]>();
        int timeQuan = 10;
        int numOfProc = 6;

        int[] p1 = {0,15,8};
        int[] p2 = {0,20,3};
        int[] p3 = {20,20,4};
        int[] p4 = {25,20,4};
        int[] p5 = {45,5,5};
        int[] p6 = {55,15,5};

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
            sortList(0, procArray);

            for (int i = 0; i < procArray.size(); i++){
                if (procArray.get(i)[0] == clockTime){
                    prioList.add(procArray.get(i));
                }
            }

            roundRobin(prioList, timeQuan);
            if (prioList.get(0)[1] <= 0){

                completedProc++;
                prioList.remove(0);
            }
            completedProc++;
        }

        for (int[] is : prioList) {
            System.out.println(Arrays.toString(is));
        }
    }

    public static void roundRobin(ArrayList<int[]> prioList, int timeQuan) {
        sortList(2, prioList);
        int[] change = prioList.get(0);
        change[1] -= timeQuan;
        prioList.set(0,change);
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
