package sm.typesystem;

import org.eclipse.lsp4j.SemanticTokenTypes;
import typelang.annotations.SemanticToken;
import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

@TypeLangAnnotation(
        id = "file",
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.TYPE
)
public class TypeFile extends TypeScope {
    @Override
    public String id() {
        return "file";
    }
}
