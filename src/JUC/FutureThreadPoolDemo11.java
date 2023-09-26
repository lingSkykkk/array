package JUC;

import java.util.concurrent.*;

public class FutureThreadPoolDemo11 {

    /*
    * 1》 future的get方法容易导致阻塞
    * 如果非要使用 要放到最后再get
    * 如果不希望在get（）方法调用时候等候 则应该使用 --》get(ling time , timeunit.....) 进行try --catch捕获超时 后面再重新get
    * join() 方法也能获取数据 但是不会在编译期报错 而get（）会
    *
    * completableFuture 中还有 puble T getNow(T valuelfAbsent) 在没有计算完成的情况下 给我一个替代结果 valuelfAbsent  而且是立即不阻塞的
    *                         puble boolean complete(T value)  是否打断get方法立即返回括号值
    *
    * 2》 轮询容易导致cpu空转 浪费资源
    * 使用 futuretask.isDone() 方法来判断是否完成
    * */
    public static void main(String[] args) {
        // 三个任务，目前开启多个异步任务线程来处理 ，请问耗时多少
        final long begin = System.currentTimeMillis();
        final ExecutorService threadPool = Executors.newFixedThreadPool(3);
        final FutureTask<String> futureTask = new FutureTask<String>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task1 over";
        });


        threadPool.submit(futureTask);

        final FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task2 over";
        });

        threadPool.submit(futureTask2);

        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

        threadPool.shutdown();

        final long end = System.currentTimeMillis();
        System.out.println("花费 :" + (end - begin) + "毫秒");
        System.out.println(Thread.currentThread().getName() + "\t ---end主线程 ");
    }

}
