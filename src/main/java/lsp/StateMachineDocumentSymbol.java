package lsp;

import neverlang.lsp.defaults.DefaultDocumentSymbol;
import neverlang.typesystem.SymbolTableEntry;
import org.eclipse.lsp4j.SymbolKind;
import sm.typesystem.TypeEvent;
import sm.typesystem.TypeMachine;
import sm.typesystem.TypeState;

public class StateMachineDocumentSymbol extends DefaultDocumentSymbol {
    @Override
    public SymbolKind getSymbolKind(SymbolTableEntry symbolTableEntry) {
        if(symbolTableEntry.type() instanceof TypeMachine) {
            return SymbolKind.Class;
        } else if (symbolTableEntry.type() instanceof TypeState) {
            return SymbolKind.Field;
        } else if (symbolTableEntry.type() instanceof TypeEvent) {
            return SymbolKind.Event;
        } else {
            return null;
        }
    }
}
