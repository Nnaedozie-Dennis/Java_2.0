import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {

    private volatile String currentTime;
    private final DateTimeFormatter timeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

    /**
     * Continuously updates the current time and date.
     */
    public void updateTime() {

        while (!Thread.currentThread().isInterrupted()) {

            LocalDateTime now = LocalDateTime.now();
            currentTime = now.format(timeFormatter);

            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Continuously displays the latest time and date.
     */
    public void displayTime() {

        while (!Thread.currentThread().isInterrupted()) {

            if (currentTime != null) {
                System.out.println(
                        "Current Time: " + currentTime
                );
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {

        Clock clock = new Clock();

        Thread updateThread = new Thread(
                clock::updateTime,
                "Clock Update Thread"
        );

        Thread displayThread = new Thread(
                clock::displayTime,
                "Clock Display Thread"
        );

        updateThread.setPriority(Thread.NORM_PRIORITY);
        displayThread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("===== SIMPLE CLOCK APPLICATION =====");

        System.out.println(
                "Update Thread Priority: "
                        + updateThread.getPriority()
        );

        System.out.println(
                "Display Thread Priority: "
                        + displayThread.getPriority()
        );

        System.out.println("Clock is running...\n");

        updateThread.start();
        displayThread.start();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

        updateThread.interrupt();
        displayThread.interrupt();

        System.out.println("\nClock application stopped.");
    }
}