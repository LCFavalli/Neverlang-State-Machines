package sm;

public record EventRecord(Class<? extends SMEvent> event, State next) {}