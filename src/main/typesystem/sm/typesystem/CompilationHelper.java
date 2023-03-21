package sm.typesystem;


import neverlang.core.typelang.annotations.TypeLangAnnotation;
import neverlang.core.typelang.annotations.TypeSystemKind;
import neverlang.core.typesystem.Scope;
import neverlang.core.typesystem.defaults.DefaultTypeSourceSet;
import sm.StateMachineModule;

@TypeLangAnnotation(
        label = StateMachineModule.LABEL,
        kind = TypeSystemKind.COMPILATION_HELPER
)
public class CompilationHelper extends neverlang.core.typesystem.CompilationHelper<String, Priorities> {
    @Override
    protected Scope<String> generateRootType() {
        return new DefaultTypeSourceSet();
    }

    @Override
    public void beforeAll() {
        BaseLang.events().forEach(e -> e.withCompilationHelper(this).inScope(getRoot()).bind());
    }
}
