//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Thread[] threads = new Thread[1000];

        long startTime = System.currentTimeMillis();
        long[] totals  = new long[1000];
        for (int i = 0; i < threads.length; i++) {
            final int index = i; // needed for inner classes

            threads[i] = new Thread(new Runnable(){
                public void run() {
                    long sum = 0;
                    for(int j = 1; j <= 1000000; j++) {
                        sum += j;
                    }
                    totals[index] = sum;
                }
            });
        }

        // how to join each thread to the main thread
        for(Thread thread : threads) {
            thread.start();
        }
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//

        // should be for the actual timer branch, i did it wrong like a fucking idiot
        long finalTotal = 0;
        for(long total: totals){
            finalTotal += total;
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Total execution time: "+duration/1000000000.0+"sec");
    }
}