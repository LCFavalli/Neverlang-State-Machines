package sm.typesystem;

import neverlang.typesystem.Signature;
import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

@TypeLangAnnotation(
        id = "state",
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.SIGNATURE
)
public class StateSignature implements Signature {

}
