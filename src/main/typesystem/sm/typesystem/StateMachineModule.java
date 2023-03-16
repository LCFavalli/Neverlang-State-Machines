package sm.typesystem;

import typelang.TypeMapperGenerator;

public class StateMachineModule extends TypeMapperGenerator {

    public final static String LANGUAGE = "statemachine";

    public StateMachineModule() {
        super("sm", LANGUAGE);
    }
}
