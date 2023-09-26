package JUC.lock;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// 模拟3个售货员卖完50张票
public class Ticker {

    private int number = 50;
    ReentrantLock lock = new ReentrantLock(true);  // 是否开启公平锁

    /*
    * 为什么会有公平锁和非公平锁的设计
    *
    * 1》  恢复挂起的线程到真正锁的获取还是有时间差的， 从开发人员来看这个时间微乎其微，但是从cpu的角度来看，这个时间差存在的还是很明显的
    * 所以非公平锁能更加的充分的利用cpu的时间片，尽量减少cpu的空闲时间
    *
    * 2》 使用多线程很重要的考量点是线程切换的开销，当采用非公平锁时， 当一个线程请求锁获取同步状态，然后释放同步状态，所以刚释放锁的线程在此刻
    * 再次获取同步状态的概率就变得非常大，所以就减少了线程的开销
    * */
    public  void sale(){
        lock.lock();
        try {
            if (number > 0){

                System.out.println(Thread.currentThread().getName() + "\t" + "卖出第：" + (number--) + "张"+ "还剩下： " + number + "张");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        final ExecutorService threadPool = Executors.newFixedThreadPool(3);
        final Ticker ticker = new Ticker();

        final CompletableFuture<Void> thread1 = CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i < 55; i++) {
                    ticker.sale();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, threadPool);

        final CompletableFuture<Void> thread2 = CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i < 55; i++) {
                    ticker.sale();
                }      } catch (Exception e) {
                e.printStackTrace();
            }
        }, threadPool);

        final CompletableFuture<Void> thread3 = CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i < 55; i++) {
                    ticker.sale();
                }            } catch (Exception e) {
                e.printStackTrace();
            }
        }, threadPool);

        threadPool.shutdown();
        System.out.println(Thread.currentThread().getName() + "\t" + "主线程已经结束了");

    }
}
