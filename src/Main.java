//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Thread[] threads = new Thread[1000];

        long isd = 0;
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
        }

        long finalTotal = 0;
        for(long total: totals){
            finalTotal += total;
        }
    }
}