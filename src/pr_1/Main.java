package pr_1;

public class Main {

    public static void main(String[] args) {
        Mess mess = new Mess();

        Thread thread1 = new Thread(mess);
        thread1.setName("Ping");
        thread1.start();

        Thread thread2 = new Thread(mess);
        thread2.setName("Pong");
        thread2.start();
    }

    static class Mess implements Runnable {
        @Override
        public void run() {
            synchronized(this) {
                for(int i = 0; i < 10; i++) {
                    try {
                        notify();
                        System.out.println( Thread.currentThread().getName());
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
