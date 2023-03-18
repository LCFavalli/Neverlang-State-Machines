package sm.typesystem;

import neverlang.typesystem.Signature;
import neverlang.typesystem.SymbolTableEntry;
import neverlang.typesystem.symboltable.EntryKind;
import org.eclipse.lsp4j.SemanticTokenTypes;
import org.eclipse.lsp4j.SymbolKind;
import typelang.annotations.*;

@TypeLangAnnotation(
        id = "state",
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.TYPE
)

public class TypeState extends TypeScope {
    @Override
    public String id() {
        return "state";
    }

    @Override
    public boolean matchSignature(Signature signature) {
        return signature instanceof StateSignature;
    }

    @DocumentSymbol
    public SymbolKind documentSymbol(SymbolTableEntry entry) {
        if(entry.entryKind().equals(EntryKind.DEFINE)){
            return SymbolKind.Property;
        } else {
            return null;
        }
    }

    @SemanticToken(SemanticTokenTypes.Property)
    public String semanticToken(SymbolTableEntry entry) {
        return SemanticTokenTypes.Property;
    }

    @SemanticTokenModifier({"final", "initial"})
    public String semanticTokenModifier(SymbolTableEntry entry) {
        if(entry.details() instanceof ModifierDetails modifierDetails){
            return switch (modifierDetails.modifier()){
                case FINAL -> "final";
                case INITIAL -> "initial";
                default -> null;
            };
        } else {
            return null;
        }
    }
}
