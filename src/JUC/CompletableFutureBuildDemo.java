package JUC;

import java.util.concurrent.*;

/*
* 1> runAsync 无返回值 建议使用线程池创建
*
* 2》 supplyAsync 有返回值 建议使用线程池创建
*
*  - 如果没有指定executor的方法 ，则直接使用默认的ForkJoinPool.commonPool()作为它的线程池执行异步代码
* */
public class CompletableFutureBuildDemo {

    public static void main(String[] args) {
        final ExecutorService threadPool = Executors.newFixedThreadPool(3);


        try {
            CompletableFuture.supplyAsync(()->{
                System.out.println(Thread.currentThread().getName() + "=--- come in");
                final int result = ThreadLocalRandom.current().nextInt(10);
                try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("一秒钟后出结果：---" + result);
                return result;
            },threadPool).whenComplete((v,e)->{
                if (e == null){
                    System.out.println("计算完成--- 模拟更新数据0---");
                }
            }).exceptionally(e ->{
                e.printStackTrace();
                System.out.println("出现了异常！！" + e.getCause() + "\t" + e.getMessage());
                return null;
            });

            System.out.println("线程先去忙其他事情了");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
