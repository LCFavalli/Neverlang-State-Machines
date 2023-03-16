package sm;

import neverlang.junit.NeverlangExt;
import neverlang.junit.NeverlangUnit;
import neverlang.junit.NeverlangUnitParam;
import neverlang.runtime.ASTNode;
import neverlang.typesystem.CompilationContext;
import neverlang.typesystem.CompilationUnitToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import sm.typesystem.CompilationHelper;
import sm.typesystem.StateMachineModule;

import javax.annotation.Nullable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(NeverlangExt.class)
@NeverlangUnit(
        language = StateMachinesLSPLang.class,
        injectors = StateMachineModule.class
)
public class TestStateMachineLang {

    private static CompilationHelper evalASTNode(ASTNode astNode, @Nullable Flow.Subscriber<Object> subscriber) {
        var compilationHelper = CompilationHelper.getFromASTNode(astNode, CompilationHelper.class);
        if(subscriber != null) {
            compilationHelper.subscribe(subscriber);
        }
        compilationHelper.setCompilationContext(new CompilationContext(new CompilationUnitToken(), Stream.empty(), false));
        compilationHelper.eval();
        return compilationHelper;
    }
    @Test
    void testSMwithEventsSyntax(@NeverlangUnitParam(files = "sm/sm_with_events.sm") ASTNode node) {
        var recordSubscriber = new RecordSubscriber(o -> o instanceof LogRecord logRecord && logRecord.getLevel().equals(Level.SEVERE));
        var compilationHelper = evalASTNode(node, recordSubscriber);
        StringWriter writer = new StringWriter();
        Utils.dotExporter(compilationHelper.getGraph(), writer);
    }

    @Test
    void testWithSemanticError(@NeverlangUnitParam(source = """
            state machine Door {
                state Open {
                    on close => Closed;
                    on exit => Tano;
                }
                initial state Closed {
                    on open => Open;
                }
                initial state Tano {
                    on open => Open;
                }
                final state Exit {}
            }
            """) ASTNode node) {
        var recordSubscriber = new RecordSubscriber(o -> o instanceof LogRecord logRecord && logRecord.getLevel().equals(Level.SEVERE));
        evalASTNode(node, recordSubscriber);
        assertEquals(1, recordSubscriber.getList().size());
    }



}
