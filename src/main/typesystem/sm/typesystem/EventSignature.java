package sm.typesystem;

import neverlang.typesystem.Signature;
import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

@TypeLangAnnotation(
        id = "event",
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.SIGNATURE
)
public class EventSignature implements Signature {
}
