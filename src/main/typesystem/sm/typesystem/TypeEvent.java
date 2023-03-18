package sm.typesystem;

import neverlang.typesystem.Signature;
import neverlang.typesystem.SymbolTableEntry;
import neverlang.typesystem.Type;
import org.eclipse.lsp4j.SemanticTokenTypes;
import org.eclipse.lsp4j.SymbolKind;
import typelang.annotations.DocumentSymbol;
import typelang.annotations.SemanticToken;
import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

@TypeLangAnnotation(
        id = "event",
        language = StateMachineModule.LANGUAGE,
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
