package sm;

import neverlang.core.typesystem.defaults.Constants;
import sm.typesystem.StateModifier;
import sm.typesystem.TypeMachine;

public class TypeLangTranslatorPlugin extends neverlang.core.typelang.TypeLangTranslatorPlugin {
    public TypeLangTranslatorPlugin() {
        super(new TypeLangModule(), new StateMachineModule());
        importAllPackages(Constants.class, TypeMachine.class, StateModifier.class);
    }
}
