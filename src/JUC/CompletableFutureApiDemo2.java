package JUC;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureApiDemo2 {

    /*
    * thenApply 计算结果存在依赖关系，使这两个线程串行化 如果步骤有异常就叫停
    * handle    计算结果存在依赖关系，使这两个线程串行化  如果步骤有异常也可以带着异常参数往下走 进一步处理
    * */
    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
//        CompletableFuture.supplyAsync(()->{
//            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//            System.out.println("111");
//            return 1;
//        },executorService).thenApply(f ->{
//            System.out.println("222");
//            return f + 2;
//        }).thenApply(f ->{
//            System.out.println("333");
//            return f + 3;
//        }).whenComplete((v,e)->{
//            if (e == null){
//                System.out.println("计算结果 ：  " + v);
//            }
//        }).exceptionally(e ->{
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            return null;
//        });
        CompletableFuture.supplyAsync(()->{
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("111");
            return 1;
        },executorService).handle((f,e)  ->{
            System.out.println("222");
            return f + 2;
        }).handle((f,e) ->{
            System.out.println("333");
            return f + 3;
        }).whenComplete((v,e)->{
            if (e == null){
                System.out.println("计算结果 ：  " + v);
            }
        }).exceptionally(e ->{
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });


        executorService.shutdown();
        System.out.println(Thread.currentThread().getName() + " ---main 线程完成了----");
    }
}
