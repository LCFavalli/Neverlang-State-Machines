package sm.typesystem;


import neverlang.typesystem.SymbolTableEntry;
import neverlang.typesystem.symbols.Location;
import neverlang.typesystem.symboltable.EntryKind;
import org.eclipse.lsp4j.SemanticTokenTypes;
import org.eclipse.lsp4j.SymbolKind;
import typelang.annotations.*;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@TypeLangAnnotation(
        id = "machine",
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.TYPE
)
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

    @DocumentSymbol
    public SymbolKind documentSymbol(SymbolTableEntry entry) {
        if(entry.entryKind().equals(EntryKind.DEFINE)){
            return SymbolKind.Method;
        } else {
            return null;
        }
    }

    @SemanticToken(SemanticTokenTypes.Class)
    public String semanticToken(SymbolTableEntry entry) {
        return SemanticTokenTypes.Class;
    }
}
