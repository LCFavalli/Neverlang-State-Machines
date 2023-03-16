package sm.typesystem;

import neverlang.typesystem.Signature;
import neverlang.typesystem.Type;
import org.eclipse.lsp4j.SemanticTokenTypes;
import typelang.annotations.SemanticToken;
import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

@TypeLangAnnotation(
        id = "event",
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.TYPE
)
@SemanticToken(SemanticTokenTypes.Event)
public class TypeEvent implements Type {
    @Override
    public String id() {
        return "event";
    }

    @Override
    public boolean matchSignature(Signature signature) {
        return signature instanceof EventSignature;
    }
}
