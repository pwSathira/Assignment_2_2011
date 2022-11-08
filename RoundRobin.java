import java.util.Scanner;

public class RoundRobin {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int userArr, userBurst;
        System.out.print("Enter number of processes: ");
        int numofProc = sc.nextInt();
        System.out.print("Enter the time quantum: ");
        int timeQuan = sc.nextInt();
        if (numofProc < 0 || timeQuan < 0){ throw new IllegalArgumentException();}
        Process processArr[] = new Process[numofProc];
        for (int i = 0; i < numofProc; i++){
            System.out.print("Enter Arrival Time: ");
            userArr = sc.nextInt();
            System.out.print("Enter Burst Time: ");
            userBurst = sc.nextInt();
            if (userArr < 0 || userBurst < 0){ throw new IllegalArgumentException();}
            processArr[i] = new Process(userArr, userBurst);
        }
        WaitTime(processArr, timeQuan); //Calls the WaitTime function with the process Array function and length
    }

    public static void WaitTime(Process[] processArr, int timeQuan) {
        int lengthArr = processArr.length;
        int waitTime[] = new int[lengthArr]; //Wait time function array
        int remainTime[] = new int[lengthArr]; //Remaining time function array
        int time = 0;
        int totalWaitTime = 0;

        for (int i = 0; i < lengthArr; i++){ //Makes a copy of processArr and assigns to remainTime
            remainTime[i] = processArr[i].burstTime;
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
                        time = time + remainTime[i];
                        waitTime[i] = time - processArr[i].burstTime;
                        remainTime[i] = 0;
                    }
                }
            }
            if (complete == true) {
                break;
            }
        }

        for (int i = 0; i < lengthArr; i++){ //Adds total wait time of waitTime array
            System.out.println(waitTime[i]);
            totalWaitTime += waitTime[i];
        }
        System.out.print("Total Wait Time is " + (float)totalWaitTime/lengthArr + "ms."); //Prints average wait time
    }
}
