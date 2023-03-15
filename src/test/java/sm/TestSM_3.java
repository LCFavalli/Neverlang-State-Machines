package sm;

import neverlang.junit.NeverlangExt;
import neverlang.junit.NeverlangUnit;
import neverlang.junit.NeverlangUnitParam;
import neverlang.runtime.ASTNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(NeverlangExt.class)
@NeverlangUnit(
    language = StateMachinesLang.class
)
public class TestSM_3 {
    @Test
    void testSMwithEventsSyntax(@NeverlangUnitParam(files = "sm/sm_with_events.sm") ASTNode node) {

    }

    @Test
    void testUnsupportedEvent(@NeverlangUnitParam(files= "sm/sm_with_events.sm") ASTNode node) {
        StateMachine context = node.getValue("sm");
        assertThrows(UnsupportedOperationException.class, () -> context.handle(new close()));
    }

    @Test
    void testSMwithEventsSemantics(@NeverlangUnitParam(files = "sm/sm_with_events.sm") ASTNode node) {
        StateMachine context = node.getValue("sm");
        var state = context.getState();
        assertEquals("Closed", state.name());
        assertTrue(state.isInitial());
        context.handle(new open());
        assertEquals("Open", context.getState().name());
    }

    @Test
    void testFinalState(@NeverlangUnitParam(files = "sm/sm_with_events.sm") ASTNode node) {
        StateMachine context = node.getValue("sm");
        IntStream.range(0, 10).forEach(i -> {
            context.handle(new open());
            context.handle(new close());
        });
        context.handle(new open());
        context.handle(new exit());
        assertTrue(context.getState().isFinal());
    }
}
