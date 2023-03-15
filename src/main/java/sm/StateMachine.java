package sm;

public class StateMachine {
    private final String name;
    private State currentState;

    public StateMachine(String name) {
        this.name = name;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public State getState() {
        return this.currentState;
    }

    public String name() {
        return this.name;
    }

    public void handle(SMEvent event) {
        this.getState().handle(event, this);
    }
}
