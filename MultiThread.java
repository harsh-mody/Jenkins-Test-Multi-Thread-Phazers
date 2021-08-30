import java.util.concurrent.Phaser;
public class MultiThread
{
    public static void main(String[] args) throws InterruptedException
    {
        Phaser phaser = new Phaser();
        phaser.register();
        System.out.println("Printing Table of 3, 4, 6");
        new MultiThread().PhaserRegister(phaser,200, 3);
        new MultiThread().PhaserRegister(phaser,600, 4);
        new MultiThread().PhaserRegister(phaser,800, 6);
        phaser.arriveAndDeregister();
        Thread.sleep(15000);
        System.out.println("Done Printing Table ");
        System.exit(0);
    }
    public static int i = 1;
    public static int count = 0;
    void PhaserRegister(Phaser phaser,int sleepTime, int no)
    {
        phaser.register();
        new Thread()
        {
            @Override
            public void run() 
            {
                for(count = 1; count <= 3 && phaser.arriveAndAwaitAdvance() > 0 && i <= 12; count++) 
                {
                    try
                    {
                        phaser.arriveAndAwaitAdvance();
                        Thread.sleep(sleepTime);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                        break;
                    }
                    System.out.print(no + " X " + i + " = " + no* i+ "\t\t\t");
                    if (count % 3 == 0)
                    {
                        count = 0;
                        System.out.println();
                        i += 1; 
                    }
               }
            }
        }.start();
    }
}

/*
Output Generated:

harshmody@Harshs-MacBook-Air Java Labs % javac MultiThread.java
harshmody@Harshs-MacBook-Air Java Labs % java MultiThread.java 
Printing Table of 3, 4, 6
3 X 1 = 3                       4 X 1 = 4                       6 X 1 = 6
3 X 2 = 6                       4 X 2 = 8                       6 X 2 = 12
3 X 3 = 9                       4 X 3 = 12                      6 X 3 = 18
3 X 4 = 12                      4 X 4 = 16                      6 X 4 = 24
3 X 5 = 15                      4 X 5 = 20                      6 X 5 = 30
3 X 6 = 18                      4 X 6 = 24                      6 X 6 = 36
3 X 7 = 21                      4 X 7 = 28                      6 X 7 = 42
3 X 8 = 24                      4 X 8 = 32                      6 X 8 = 48
3 X 9 = 27                      4 X 9 = 36                      6 X 9 = 54
3 X 10 = 30                     4 X 10 = 40                     6 X 10 = 60
3 X 11 = 33                     4 X 11 = 44                     6 X 11 = 66
3 X 12 = 36                     4 X 12 = 48                     6 X 12 = 72
*/