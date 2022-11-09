import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class finalRoundRobin {
    public static void main(String args[]){
//        Scanner sc = new Scanner(System.in);
//        int userArr, userBurst, userPrio;
//        System.out.print("Enter number of processes: ");
//        int numofProc = sc.nextInt();
//        System.out.print("Enter the time quantum: ");
//        int timeQuan = sc.nextInt();
//        if (numofProc < 0 || timeQuan < 0){ throw new IllegalArgumentException();}
//
//        ArrayList<Process> processArr = new ArrayList<Process>();
//
//        for (int i = 0; i < numofProc; i++){
//            System.out.print("Enter Arrival Time: ");
//            userArr = sc.nextInt();
//            System.out.print("Enter Burst Time: ");
//            userBurst = sc.nextInt();
//            System.out.print("Enter Priority: ");
//            userPrio = sc.nextInt();
//            processArr.add(i, new Process(userArr, userBurst, userPrio));
//        }
        Process p1 = new Process(0,10,3);
        Process p2 = new Process(0,1,1);
        Process p3 = new Process(0,2,4);
        Process p4 = new Process(0,1,5);
        Process p5 = new Process(0,5,2);

        ArrayList<Process> processArr = new ArrayList<Process>();
        processArr.add(p1);
        processArr.add(p2);
        processArr.add(p3);
        processArr.add(p4);
        processArr.add(p5);

        int timeQuan = 10;


        WaitTime(processArr, timeQuan); //Calls the WaitTime function with the process Array function and length
    }
    public static void WaitTime(ArrayList<Process> processArr, int timeQuan) {
        ArrayList<Process> currQueue = new ArrayList<Process>();
        int totalProc = processArr.size();
        int completedProc = 0;
        int time = 0;
        int totalWaitTime = 0;

        while (totalProc > completedProc){
            for (int i = 0; i < totalProc-completedProc; i++){
                if (processArr.get(i).arrTime == time){
                    currQueue.add(processArr.get(i));
                }
            }
            currQueue.sort(Comparator.comparing(Process::getUserPrio));

            if (currQueue.get(0).burstTime > 0){
                currQueue.get(0).burstTime -= timeQuan;
                totalWaitTime += time;
            }

            if (currQueue.get(0).burstTime <= 0){
                currQueue.remove(0);
                completedProc++;
            }

            time++;
        }

        System.out.println("Average wait time="+totalWaitTime/totalProc);
    }

}
