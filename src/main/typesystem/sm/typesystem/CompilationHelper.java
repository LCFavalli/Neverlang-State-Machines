package sm.typesystem;

import neverlang.typesystem.Scope;
import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

@TypeLangAnnotation(
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.COMPILATION_HELPER
)
public class CompilationHelper extends neverlang.typesystem.CompilationHelper<String, Priorities> {
    @Override
    protected Scope<String> generateRootType() {
        return new TypeSourceSet();
    }
}
