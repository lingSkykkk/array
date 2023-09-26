package JUC;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureApiDemo3 {

    /*
    * 对计算结果进行消费  接受任务的处理结果，并消费处理 无返回结果
    * thenAccept(Consumer action) 任务a执行完执行b 。 b需要a的结果 但是任务b无返回值
    * thenRun(Runnable runnable)  任务a执行完执行b。 并且b不需要a的结果
    * thenApply（Function fn）  任务a执行完执行b 。 b需要a的结果 同时任务b有返回值
    * */


    /*
    * 线程池的选择
    * 1>  没有传入自定义的线程池，都用默认线程池ForkJoinPool
    * 2>  传入了一个自定义线程池
    *  如果在执行第一个任务的时候，传入了一个自定义线程池
    *       调用thenRun方法执行第二个任务时，则第二个任务和第一个任务共用同一个线程池
    *       调用thenRunSync执行第二个任务时，则第一个任务用自己传入的线程池。第二使用默认线程池
    *
    * 3》 备注
    *      有可能处理太快， 系统优化切换原则， 直接使用main线程处理
    *       其他的方法 也是同理 区别在于有没有sync
    *
    * */
    public static void main(String[] args) {

        System.out.println(CompletableFuture.supplyAsync(()-> "resultA").thenRun(()->{}).join());
        System.out.println(CompletableFuture.supplyAsync(()-> "resultB").thenAccept((r)->{System.out.println(r);}).join());
        System.out.println(CompletableFuture.supplyAsync(()-> "resultC").thenApply((r)-> r + "resultC").join());

    }
}
