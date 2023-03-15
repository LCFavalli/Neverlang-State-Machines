package sm;

import neverlang.junit.NeverlangExt;
import neverlang.junit.NeverlangUnit;
import neverlang.junit.NeverlangUnitParam;
import neverlang.runtime.ASTNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(NeverlangExt.class)
@NeverlangUnit(language = SMLang_2.class)
public class TestSM_2 {

    @Test
    void testStates(@NeverlangUnitParam(files = "sm/sm_with_states.sm") ASTNode node) {
        StateMachine context = node.getValue("sm");
        var state = context.getState();
        assertEquals("Closed", state.name());
        assertTrue(state.isInitial());
    }
}
