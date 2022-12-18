package bbfx;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BBfx extends Application {

    //public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/bbfx/cinmaTheater.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Producer-Consumer problem");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Producer implements Runnable {

    private final List<Integer> queue;

    private final int size;

    public Producer(List<Integer> queue, int size) {
        this.queue = queue;
        this.size = size;
    }
    int count = 1;

    @Override
    public void run() {
        try {
            produce(count++);

        } catch (InterruptedException ex) {
            System.out.println("interupted at producer");
            ex.printStackTrace();
        }
        count++;
    }

    private void produce(int i) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == size) {
                System.out.println("overflow");
                System.out.println(Thread.currentThread().getName() + " is waiting,size: " + queue.size());
                queue.wait();
            }
            Thread.sleep(500);
            queue.add(i);
            System.out.println("Produced: " + i);
            queue.notifyAll();
        }
    }
}

class Consumer implements Runnable {

    private final List<Integer> queue;

    public Consumer(List<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException ex) {
            System.out.println("interupted at consumer");
            ex.printStackTrace();
        }
    }

    private void consume() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                System.out.println("underflow");
                System.out.println(Thread.currentThread().getName() + " is waiting , size: " + queue.size());
                queue.wait();
            }
            Thread.sleep(500);
            int i = (Integer) queue.remove(0);
            System.out.println("consumed: " + i);
            queue.notifyAll();
        }
    }
}
