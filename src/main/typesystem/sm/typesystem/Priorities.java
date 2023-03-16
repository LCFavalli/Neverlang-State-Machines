package sm.typesystem;

import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

@TypeLangAnnotation(
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.PRIORITY
)

public enum Priorities implements Comparable<Priorities> {
    MACHINE,
    STATE,
    EVENTS
}
