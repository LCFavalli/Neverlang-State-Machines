package sm;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class State {
    public enum Type {
        NORMAL,
        INITIAL,
        FINAL
    }
    private final String name;
    private final Type type;
    private Map<Class<? extends SMEvent>, State> stateTransitionFunction;


    public State(String name, Type type) {
        this.name = name;
        this.type = type;
        this.stateTransitionFunction = new HashMap<>();
    }
    public State(String name) {
        this(name, Type.NORMAL);
    }

    public String name() {
        return this.name;
    }

    public void handle(SMEvent event, StateMachine context) {
        State next = Optional.ofNullable(stateTransitionFunction.get(event.getClass()))
            .orElseThrow(UnsupportedOperationException::new);
        event.run();
        context.setState(next);
    }

    public void register(EventRecord transition) {
        this.stateTransitionFunction.put(transition.event(), transition.next());
    }

    public boolean isInitial() {
        return this.type.equals(Type.INITIAL);
    }

    public boolean isFinal() {
        return this.type.equals(Type.FINAL);
    }

    public boolean isNormal() {
        return this.type.equals(Type.NORMAL);
    }
}
