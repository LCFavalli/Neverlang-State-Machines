package sm.typesystem;

import neverlang.typesystem.Signature;
import org.eclipse.lsp4j.SemanticTokenTypes;
import typelang.annotations.SemanticToken;
import typelang.annotations.TypeLangAnnotation;
import typelang.annotations.TypeSystemKind;

@TypeLangAnnotation(
        id = "state",
        language = StateMachineModule.LANGUAGE,
        kind = TypeSystemKind.TYPE
)
@SemanticToken(SemanticTokenTypes.Property)
public class TypeState extends TypeScope {
    @Override
    public String id() {
        return "state";
    }

    @Override
    public boolean matchSignature(Signature signature) {
        return signature instanceof StateSignature;
    }
}
