package JUC.lock;


/*
* 可重入锁又称递归锁
* 是指同一个线程在外层方法获取锁时，再进入改线程的内层方法会自动获取锁 （前提，锁对象得是同一个对象），不会因为之前已经获取过还没有释放而阻塞
*
* java中只有reentrantlock和synchronize是可重入锁，可重入锁的一个优点是可一定程度避免死锁
*
* 每个锁对象拥有一个锁计数器和一个指向持有该锁的线程的指针
* 当执行jvm底层monitorEnter时， 如果目标锁的计数器为零，说明他没有被其他线程持有，java虚拟机会将该锁对象的持有线程设置为当前线程，并将计数器加1
*
* 在目标锁对象的计数器不为零的情况下，如果锁对象的持有线程是当前线程，那么java虚拟机可以将其计数器加1，否则需要等待 知道该线程释放锁
*
* 当执行 monitorExit时， java虚拟机将锁对象的计数器减1，计数器为0代表锁已被释放
* */
public class ReentrantLock {
}
