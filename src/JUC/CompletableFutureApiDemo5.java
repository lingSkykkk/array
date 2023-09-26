package JUC;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureApiDemo5 {

    /*
        * 对计算 结果进行合并  两个completionStage任务都完成后， 最终把两个任务的结果一起交给 thenCombine来处理
        * 先完成的先等着，等待其他分支任务
    * */
    public static void main(String[] args) {
        final CompletableFuture<Integer> thread1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        final CompletableFuture<Integer> thread2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t");
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                    return 20;
                });

        // 合并结果
        final CompletableFuture<Integer> result = thread1.thenCombine(thread2, (x, y) -> {
            System.out.println("开始两个线程合并");
            return x + y;
        });
        System.out.println(result.join());
    }
}
