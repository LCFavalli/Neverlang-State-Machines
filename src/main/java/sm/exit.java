package sm;

public class exit implements SMEvent {
    @Override
    public void run() {
        System.out.println("Custom event action --> REACHING THE FINAL STATE!");
    }
}
