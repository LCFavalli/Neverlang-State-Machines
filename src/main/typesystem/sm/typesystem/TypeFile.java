package sm.typesystem;

import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

@TypeLangAnnotation(
        id = "file",
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.TYPE
)
public class TypeFile extends TypeScope {
    @Override
    public String id() {
        return "file";
    }
}
