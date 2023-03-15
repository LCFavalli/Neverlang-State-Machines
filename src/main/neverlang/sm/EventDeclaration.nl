module sm.EventDeclaration {
    reference syntax {
        event:
            EventDefinition <-- "on" SMIdentifier "=>" SMIdentifier ";";
    }
    role(register) {
        event: @{
            String eventId = $event[1].id;
            String nextId = $event[2].id;
            try {
                State next = $$StateMap.get(nextId);
                Class<? extends SMEvent> event =
                    (Class<? extends SMEvent>)
                        Class.forName("sm.%s".formatted(eventId));
                EventRecord transition = new EventRecord(event, next);
                $event.transition = transition;
            } catch(Throwable t) {
                throw new UnsupportedOperationException(t);
            }
        }.
    }
}