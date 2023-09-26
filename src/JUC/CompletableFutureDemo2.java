package JUC;

import java.util.concurrent.*;

public class CompletableFutureDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        final Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName() + "\t ---end主线程 ");

    }

    static class MyThread implements Callable<String>{

        @Override
        public String call() throws Exception {

            System.out.println("------------- come in calL()");
            return "hello callable";
        }
    }
}
