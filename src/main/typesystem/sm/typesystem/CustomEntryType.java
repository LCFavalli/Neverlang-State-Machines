package sm.typesystem;

import neverlang.typesystem.Type;
import neverlang.typesystem.symbols.Token;
import neverlang.typesystem.typenv.EntryType;

import java.util.concurrent.atomic.AtomicReference;

public record CustomEntryType (Token token,  AtomicReference<Type> refType) implements EntryType {
    public CustomEntryType(Token token,  Type type) {
        this(token, new AtomicReference<>(type));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CustomEntryType other){
            return token.equals(other.token) && refType.get() != null && refType.get().equals(other.refType.get());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return token.hashCode() + (refType.get() != null ? refType.get().hashCode() : 0);
    }
}

