package sm;


import neverlang.core.typelang.TypeMapperGenerator;
import neverlang.core.typesystem.defaults.Constants;
import sm.typesystem.TypeMachine;

public class StateMachineModule extends TypeMapperGenerator {

    public final static String LANGUAGE = "statemachine";

    public final static String LABEL = "state machine";
    public final static String TYPE_PACKAGE = TypeMachine.class.getPackageName();

    public StateMachineModule() {
        initPackage(TYPE_PACKAGE, LABEL);
        initPackage(Constants.DEFAULT_TYPES_PACKAGE, Constants.DEFAULT_TYPES_LABEL);
    }
}
