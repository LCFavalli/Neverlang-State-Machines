module sm.EventDeclaration {
    reference syntax {
        event:
            EventDefinition <-- "on" SMIdentifier "=>" SMIdentifier ";";
    }


    role(type-checker) {

        0 <typeLang> .{
            catch {
                eval $1
                infer event $1
            }
            catch {
                eval $2
                infer state $2
            }
        }.
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