package example;

import neverlang.junit.NeverlangExt;
import neverlang.junit.NeverlangUnit;
import neverlang.junit.NeverlangUnitParam;
import neverlang.runtime.ASTNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(NeverlangExt.class)
@NeverlangUnit(
    language = ExampleLanguage.class
)
public class HelloWorldTest {
    @Test
    void testHelloWorldFromSource(@NeverlangUnitParam(source = "Hello world!") ASTNode node) {
        Assertions.assertEquals("Program", node.getProduction().getNonTerminal().getSymbolIdentifier());
    }

    @Test
    void testHelloWorldFromFile(@NeverlangUnitParam(files = "example/HelloWorldExample.ex") ASTNode node) {
        Assertions.assertEquals("Program", node.getProduction().getNonTerminal().getSymbolIdentifier());
    }
}
