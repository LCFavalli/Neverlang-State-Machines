package lsp;

import neverlang.core.lsp.compiler.LSPCompilerHandler;
import neverlang.core.typesystem.compiler.Compiler;
import neverlang.core.typesystem.compiler.SourceSet;
import neverlang.core.typesystem.defaults.DefaultSourceSet;
import sm.StateMachinesLSPLang;
import sm.typesystem.CompilationHelper;
import sm.StateMachineModule;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Flow;

public class StateMachineCompilerHandler extends LSPCompilerHandler<CompilationHelper> {
    @Override
    public SourceSet getSourceSet(Path path) {
        return new DefaultSourceSet.Builder(".sm").buildFromRootDir(path);
    }

    @Override
    public Compiler<CompilationHelper> getCompiler(List<Flow.Subscriber<Object>> subscribers) {
        return new Compiler<>(
                    new StateMachinesLSPLang(new StateMachineModule()),
                    CompilationHelper.class,
                    subscribers
        );
    }
}
