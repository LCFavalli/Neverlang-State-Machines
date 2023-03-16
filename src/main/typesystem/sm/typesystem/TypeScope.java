package sm.typesystem;

import neverlang.typesystem.Scope;
import neverlang.typesystem.defaults.SingleTypeTypeBinder;
import neverlang.typesystem.symbols.Token;
import neverlang.typesystem.typenv.TypingEnvironment;

public abstract class TypeScope implements Scope<String> {
    private TypingEnvironment<String> typingEnvironment;

    @Override
    public TypingEnvironment<String> getTypingEnvironment() {
        if(typingEnvironment == null){
            this.typingEnvironment = new TypingEnvironment.Builder<String>()
                    .setTypeBinder(SingleTypeTypeBinder.class)
                    .build();
        }
        return typingEnvironment;
    }

    @Override
    public String identifierFromToken(Token token) {
        return token.text();
    }
}
