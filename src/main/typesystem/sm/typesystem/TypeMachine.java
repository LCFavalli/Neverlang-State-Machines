package sm.typesystem;


import neverlang.typesystem.SymbolTableEntry;
import neverlang.typesystem.symbols.Location;
import org.eclipse.lsp4j.SemanticTokenTypes;
import typelang.annotations.SemanticToken;
import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@TypeLangAnnotation(
        id = "machine",
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.TYPE
)
@SemanticToken(SemanticTokenTypes.Class)
public class TypeMachine extends TypeScope {
    @Override
    public String id() {
        return "machine";
    }

    @Override
    public void validate(SymbolTableEntry entry) {
        var count = streamAllEntries()
                .filter(e -> e.getValue().details() instanceof ModifierDetails)
                .map(Map.Entry::getValue)
                .filter(e -> ((ModifierDetails)e.details()).modifier() == StateModifier.INITIAL)
                .count();
        if(count != 1) {
            throw new ModifierException("Machine must have exactly one initial state", entry.location());
        }
    }
}
