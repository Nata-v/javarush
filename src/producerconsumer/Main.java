package producerconsumer;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;


public class Main {
    public static void main(String[] args) throws InterruptedException{
        TransferQueue<ShareItem> queue = new LinkedTransferQueue<>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();

        Thread.sleep(1500);

        producer.interrupt();
        consumer.interrupt();
    }
}
