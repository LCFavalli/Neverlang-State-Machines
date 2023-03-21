package sm.typesystem;


import neverlang.core.typelang.annotations.TypeLangAnnotation;
import neverlang.core.typelang.annotations.TypeSystemKind;
import sm.StateMachineModule;

@TypeLangAnnotation(
        label = StateMachineModule.LABEL,
        kind = TypeSystemKind.PRIORITY
)

public enum Priorities implements Comparable<Priorities> {
    MACHINE,
    STATE,
    EVENTS
}
