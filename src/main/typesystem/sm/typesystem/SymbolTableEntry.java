package sm.typesystem;

import neverlang.typesystem.EntryDetails;
import neverlang.typesystem.Type;
import neverlang.typesystem.symbols.Range;
import neverlang.typesystem.symbols.Token;
import neverlang.typesystem.typenv.EntryType;
import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

import java.util.concurrent.atomic.AtomicReference;

@TypeLangAnnotation(
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.SYMBOL_TABLE_ENTRY
)
public record SymbolTableEntry(
        EntryType entryType,
        EntryDetails details,
        Range foldingRange
) implements neverlang.typesystem.SymbolTableEntry {


    @Override
    public String toString() {
        return String.format("%s in %s", entryType().token().text(), entryType().token().range());
    }

}