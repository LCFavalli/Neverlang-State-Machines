package sm.typesystem;

import neverlang.typesystem.NeverlangTypesystemException;
import neverlang.typesystem.symbols.Location;

public class ModifierException extends NeverlangTypesystemException {
    private final Location location;

    public ModifierException(String message, Location location) {
        super(message);
        this.location = location;
    }

    @Override
    public Location location() {
        return location;
    }
}
