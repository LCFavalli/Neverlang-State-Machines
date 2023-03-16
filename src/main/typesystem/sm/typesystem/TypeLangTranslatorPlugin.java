package sm.typesystem;

import sm.TypeLangModule;

import java.util.List;

public class TypeLangTranslatorPlugin extends typelang.TypeLangTranslatorPlugin {
    public TypeLangTranslatorPlugin() {
        super(new TypeLangModule(), new StateMachineModule());
    }

    @Override
    protected String formatImports(List<String> importList) {
        importList.addAll(List.of(
                "neverlang.typesystem.symboltable.EntryKind",
                "neverlang.typesystem.defaults.*",
                "neverlang.typesystem.symbols.*",
                "sm.typesystem.*"
        ));
        return super.formatImports(importList);
    }
}
