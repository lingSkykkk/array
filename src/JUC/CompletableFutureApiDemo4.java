package JUC;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureApiDemo4 {


    /*
    * 对计算速度进行选用
    * applyToEither  谁快用谁
    * */
    public static void main(String[] args) {

        final CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playA";
        });


        final CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playB";
        });

        final CompletableFuture<String> result = playA.applyToEither(playB, f -> {
            return f + "is winner";
        });

        System.out.println(Thread.currentThread().getName() + "\t" + "-------" + result.join());
    }
}
