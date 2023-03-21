package lsp;

import neverlang.core.lsp.defaults.*;
import neverlang.core.lsp.launcher.NeverlangLSPProvider;
import neverlang.core.lsp.services.NeverlangLSPDocumentService;
import neverlang.core.lsp.services.NeverlangLSPLanguageServer;
import neverlang.core.lsp.services.NeverlangLSPWorkspaceService;
import sm.StateMachineModule;

import java.util.List;

public class LSPProvider extends NeverlangLSPProvider {

    public LSPProvider() {
        super(List.of(
                new DefaultDiagnostic(StateMachineModule.LANGUAGE),
                new DefaultDocumentSymbol(StateMachineModule.TYPE_PACKAGE),
                new DefaultGoToDefinition(),
                new DefaultReferences(),
                new DefaultFoldingRange(),
                new DefaultSemanticToken(StateMachineModule.TYPE_PACKAGE)
        ));
    }
    @Override
    public NeverlangLSPLanguageServer<?> newLanguageServer() {
        return new NeverlangLSPLanguageServer<>(
                capabilityList,
                new StateMachineCompilerHandler(),
                new NeverlangLSPDocumentService<>(capabilityList),
                new NeverlangLSPWorkspaceService()
        );
    }
}
