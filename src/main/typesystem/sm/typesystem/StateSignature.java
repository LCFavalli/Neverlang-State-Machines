package sm.typesystem;

import neverlang.core.typelang.annotations.TypeLangAnnotation;
import neverlang.core.typelang.annotations.TypeSystemKind;
import neverlang.core.typesystem.Signature;
import sm.StateMachineModule;

@TypeLangAnnotation(
        keyword = "state",
        label = StateMachineModule.LABEL,
        kind = TypeSystemKind.SIGNATURE
)
public class StateSignature implements Signature {

}
