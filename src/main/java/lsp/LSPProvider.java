package lsp;

import neverlang.lsp.defaults.*;
import neverlang.lsp.launcher.NeverlangLSPProvider;
import neverlang.lsp.services.NeverlangLSPDocumentService;
import neverlang.lsp.services.NeverlangLSPLanguageServer;
import neverlang.lsp.services.NeverlangLSPWorkspaceService;
import sm.typesystem.StateMachineModule;

import java.util.List;

public class LSPProvider extends NeverlangLSPProvider {

    final static String typesystem = "sm.typesystem";

    public LSPProvider() {
        super(List.of(
                new DefaultDiagnostic(StateMachineModule.LANGUAGE),
                new DefaultDocumentSymbol(typesystem),
                new DefaultGoToDefinition(),
                new DefaultReferences(),
                new DefaultFoldingRange(),
                new DefaultSemanticToken(typesystem)
        ));
    }
    @Override
    public NeverlangLSPLanguageServer newLanguageServer() {
        return new NeverlangLSPLanguageServer<>(
                capabilityList,
                new StateMachineCompilerHandler(),
                new NeverlangLSPDocumentService<>(capabilityList),
                new NeverlangLSPWorkspaceService()
        );
    }
}
