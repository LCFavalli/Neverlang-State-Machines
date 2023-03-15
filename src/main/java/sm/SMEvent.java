package sm;

public interface SMEvent {
    default void run() {
        System.out.printf("Event %s triggered\n", this.getClass().getSimpleName());
    }
}
