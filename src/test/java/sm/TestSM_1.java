package sm;

import neverlang.junit.NeverlangExt;
import neverlang.junit.NeverlangUnit;
import neverlang.junit.NeverlangUnitParam;
import neverlang.runtime.ASTNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(NeverlangExt.class)
@NeverlangUnit(language = SMLang_1.class)
public class TestSM_1 {
    @Test
    void testParsingEmptyDeclaration(@NeverlangUnitParam(source = "state machine Door {}") ASTNode node) {
        assertEquals("Program", node.getProduction().getNonTerminal().getSymbolIdentifier());
    }

    @Test
    void testSMReturnsContext(@NeverlangUnitParam(source = "state machine Door {}") ASTNode node) {
        var o = node.getAttributes().get("sm");
        assertInstanceOf(StateMachine.class, o);
        assertEquals("Door", ((StateMachine) o).name());
    }
}
