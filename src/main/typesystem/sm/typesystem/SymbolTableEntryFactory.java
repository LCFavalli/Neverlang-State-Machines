package sm.typesystem;


import neverlang.core.typelang.annotations.TypeDetail;
import neverlang.core.typelang.annotations.TypeLangAnnotation;
import neverlang.core.typelang.annotations.TypeSystemKind;
import neverlang.core.typesystem.EntryDetails;
import neverlang.core.typesystem.SymbolTableEntry;
import neverlang.core.typesystem.defaults.DefaultSymbolTableEntry;
import neverlang.core.typesystem.symbols.Token;
import sm.StateMachineModule;

import java.util.Optional;

@TypeLangAnnotation(
        label = StateMachineModule.LABEL,
        kind = TypeSystemKind.SYMBOL_TABLE_ENTRY_FACTORY
)
public class SymbolTableEntryFactory extends neverlang.core.typesystem.SymbolTableEntryFactory<String, SymbolTableEntryFactory> {
    private StateModifier modifier = StateModifier.NORMAL;

    @Override
    public String getIdentifierFromToken() {
        return Optional.ofNullable(token()).map(Token::text).orElse(null);
    }

    @Override
    public EntryDetails entryDetails() {
        return new ModifierDetails(modifier);
    }
    @TypeDetail(id = "modifier")

    public SymbolTableEntryFactory withModifier(StateModifier modifier) {
        this.modifier = modifier;
        return this;
    }

    @Override
    public SymbolTableEntry getSymbolTableEntry() {
        return new DefaultSymbolTableEntry(
                entryType(),
                entryDetails(),
                foldingRange(),
                entryKind()
        );
    }
}
