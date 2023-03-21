package sm.typesystem;


import neverlang.core.typelang.annotations.*;
import neverlang.core.typesystem.SymbolTableEntry;
import neverlang.core.typesystem.defaults.DefaultTypeScope;
import neverlang.core.typesystem.defaults.SingleTypeTypeBinder;
import neverlang.core.typesystem.symboltable.EntryKind;
import neverlang.core.typesystem.typenv.EntryTypeBinder;
import org.eclipse.lsp4j.SemanticTokenTypes;
import org.eclipse.lsp4j.SymbolKind;
import sm.StateMachineModule;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.LogRecord;

@TypeLangAnnotation(
        keyword = "machine",
        label = StateMachineModule.LABEL,
        kind = TypeSystemKind.TYPE
)
public class TypeMachine extends DefaultTypeScope {
    @Override
    public String id() {
        return "machine";
    }

    @Callback(id = "validateFinalState")
    public void validate(SymbolTableEntry entry, CompilationHelper helper) {
        var initialCounter = new AtomicInteger(0);
        var finalCounter = new AtomicInteger(0);
        streamAllEntries()
                .filter(e -> e.getValue().details() instanceof ModifierDetails)
                .map(Map.Entry::getValue)
                .forEachOrdered(e -> {
                    switch (((ModifierDetails)e.details()).modifier()){
                        case INITIAL -> initialCounter.incrementAndGet();
                        case FINAL -> finalCounter.incrementAndGet();
                    }
                });

        if(finalCounter.get() == 0){
            var logRecord = new LogRecord(Level.WARNING, "Machine should have at least one final state");
            logRecord.setParameters(new Object[]{entry.location()});
            helper.submit(logRecord);
        }
        if(initialCounter.get() != 1) {
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

    @Override
    public Class<? extends EntryTypeBinder> getTypeBinder() {
        return SingleTypeTypeBinder.class;
    }
}
