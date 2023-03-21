package sm.typesystem;

import neverlang.core.typelang.annotations.*;
import neverlang.core.typesystem.Signature;
import neverlang.core.typesystem.SymbolTableEntry;
import neverlang.core.typesystem.defaults.DefaultTypeScope;
import neverlang.core.typesystem.defaults.SingleTypeTypeBinder;
import neverlang.core.typesystem.symboltable.EntryKind;
import neverlang.core.typesystem.typenv.EntryTypeBinder;
import org.eclipse.lsp4j.SemanticTokenTypes;
import org.eclipse.lsp4j.SymbolKind;
import sm.StateMachineModule;

@TypeLangAnnotation(
        keyword = "state",
        label = StateMachineModule.LABEL,
        kind = TypeSystemKind.TYPE
)

public class TypeState extends DefaultTypeScope {
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

    @Override
    public Class<? extends EntryTypeBinder> getTypeBinder() {
        return SingleTypeTypeBinder.class;
    }
}
