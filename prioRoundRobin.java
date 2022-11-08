import java.util.Scanner;

public class prioRoundRobin {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int userArr, userBurst, userPrio;
        System.out.print("Enter number of processes: ");
        int numofProc = sc.nextInt();
        System.out.print("Enter the time quantum: ");
        int timeQuan = sc.nextInt();
        if (numofProc < 0 || timeQuan < 0){ throw new IllegalArgumentException();}
        Process processArr[][] = new Process[5][numofProc];
        for (int i = 0; i < numofProc; i++){
            System.out.print("Enter Arrival Time: ");
            userArr = sc.nextInt();
            System.out.print("Enter Burst Time: ");
            userBurst = sc.nextInt();
            System.out.print("Enter Priority: ");
            userPrio = sc.nextInt();
            if (userArr < 0 || userBurst < 0 || userPrio < 0){ throw new IllegalArgumentException();}
            processArr[userPrio][i] = new Process(userArr, userBurst);
        }



        WaitTime(processArr, timeQuan); //Calls the WaitTime function with the process Array function and length
    }
    public static void WaitTime(Process[][] processArr, int timeQuan) {
        int lengthArr = processArr.length;
        int waitTime[] = new int[lengthArr]; //Wait time function array
        int remainTime[] = new int[lengthArr]; //Remaining time function array
        int time = 0;
        int totalWaitTime = 0;

        for (int currPrio = 0; currPrio < 5; currPrio++){

            for (int i = 0; i < lengthArr; i++){ //Makes a copy of processArr and assigns to remainTime
                remainTime[i] = processArr[currPrio][i].burstTime;
            }

            while(true){
                boolean complete = true;
                for (int i = 0; i < lengthArr; i++){
                    if (remainTime[i] > 0){
                        complete = false;
                        if (remainTime[i] > timeQuan){
                            time += timeQuan;
                            remainTime[i] -= timeQuan;
                        } else {
                            time += remainTime[i];
                            waitTime[i] = time - processArr[currPrio][i].burstTime;
                            remainTime[i] = 0;
                        }
                    }
                }
                if (complete == true) {
                    break;
                }
            }
            for (int i = 0; i < lengthArr; i++){ //Adds total wait time of waitTime array
                totalWaitTime += waitTime[i];
            }
        }

        System.out.print("Total Wait Time is " + (double)totalWaitTime/lengthArr + "ms."); //Prints average wait time
    }
}
