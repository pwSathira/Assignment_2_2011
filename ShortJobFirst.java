public class ShortJobFirst {
    public static void main(String[] args){
        Process processArr[] = {//Creates a process object array, with inputs Arrival Time and Burst Time
                new Process(0,8),
                new Process(1,4),
                new Process(2,9),
                new Process(3,5)};
        WaitTime(processArr); //Calls the WaitTime function with the process Array function and length
    }

    public static void WaitTime(Process[] processArr) {
        int lengthArr = processArr.length;
        int waitTime[] = new int[lengthArr]; //Wait time function array
        int remainTime[] = new int[lengthArr]; //Remaining time function array

        for (int i = 0; i < lengthArr; i++){ //Makes a copy of processArr and assigns to remainTime
            remainTime[i] = processArr[i].burstTime;
        }

        int time = 0; //Starting time
        int minimum = 100000; //Initial minimum which will be compared against remainTime
        int shortest = 0; //Initial shortest job
        
        int complete = 0; //Once complete finishes through every process it will exit the loop
        while (complete != lengthArr){

            for (int i = 0; i < lengthArr; i++){ //Iterates through both processArr and remainTime
                if ((processArr[i].arrTime <= time) && (remainTime[i] < minimum) && remainTime[i] > 0) {
                    //Checked if it is the short job
                    minimum = remainTime[i];
                    shortest = i;
                }
            }

            remainTime[shortest]--; //Take time off the short job
            minimum = remainTime[shortest]; //Set the new minimum

            if (minimum == 0){minimum = 100000;}  //If the minimum is 0 reset for next process

            if (remainTime[shortest] == 0) { //Checks if the process is complete
                complete++; //One process is complete
                waitTime[shortest] = (time+1) - processArr[shortest].burstTime - processArr[shortest].arrTime;
                //Calculated the waitTime of that process
                if (waitTime[shortest] < 0) {waitTime[shortest] = 0;}
            }
            
            time++; //Go up 1ms in time
        }

        int totalWaitTime = 0;
        for (int i = 0; i < lengthArr; i++){ //Adds total wait time of waitTime array
            totalWaitTime += waitTime[i];
        }
        System.out.print((double)totalWaitTime/lengthArr); //Prints average wait time
    }
}

class Process{
    int arrTime;
    int burstTime;

    public Process(int arrTime, int burstTime){
        this.arrTime = arrTime;
        this.burstTime = burstTime;
    }
}
