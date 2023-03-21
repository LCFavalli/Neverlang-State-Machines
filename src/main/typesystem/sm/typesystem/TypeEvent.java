package sm.typesystem;

import neverlang.core.typelang.annotations.DocumentSymbol;
import neverlang.core.typelang.annotations.SemanticToken;
import neverlang.core.typelang.annotations.TypeLangAnnotation;
import neverlang.core.typelang.annotations.TypeSystemKind;
import neverlang.core.typesystem.Signature;
import neverlang.core.typesystem.SymbolTableEntry;
import neverlang.core.typesystem.Type;
import org.eclipse.lsp4j.SemanticTokenTypes;
import org.eclipse.lsp4j.SymbolKind;
import sm.StateMachineModule;

@TypeLangAnnotation(
        keyword = "event",
        label = StateMachineModule.LABEL,
        kind = TypeSystemKind.TYPE
)

public class TypeEvent implements Type {
    @Override
    public String id() {
        return "event";
    }

    @Override
    public boolean matchSignature(Signature signature) {
        return signature instanceof EventSignature;
    }

    @DocumentSymbol
    public SymbolKind documentSymbol(SymbolTableEntry entry) {
        return SymbolKind.Event;
    }

    @SemanticToken(SemanticTokenTypes.Event)
    public String semanticToken(SymbolTableEntry entry) {
        return SemanticTokenTypes.Event;
    }
}
