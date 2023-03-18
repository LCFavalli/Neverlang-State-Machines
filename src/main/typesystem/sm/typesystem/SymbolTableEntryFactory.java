package sm.typesystem;


import neverlang.typesystem.EntryDetails;
import neverlang.typesystem.SymbolTableEntry;
import neverlang.typesystem.defaults.DefaultEntryType;
import neverlang.typesystem.defaults.DefaultSymbolTableEntry;
import neverlang.typesystem.symbols.Token;
import neverlang.typesystem.typenv.EntryType;
import org.checkerframework.checker.nullness.Opt;
import typelang.annotations.TypeDetail;
import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

import java.util.Optional;

@TypeLangAnnotation(
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.SYMBOL_TABLE_ENTRY_FACTORY
)
public class SymbolTableEntryFactory extends neverlang.typesystem.SymbolTableEntryFactory<String, SymbolTableEntryFactory> {
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
        return new sm.typesystem.SymbolTableEntry(
                entryType(),
                entryDetails(),
                foldingRange(),
                entryKind()
        );
    }
}
