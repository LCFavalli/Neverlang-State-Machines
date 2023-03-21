package lsp;

import neverlang.core.lsp.launcher.NeverlangLSPSocketLauncher;

public class SocketLauncher {

    public static void main(String[] args) {
        var lspProvider = new LSPProvider();
        new NeverlangLSPSocketLauncher(lspProvider, 5123)
                .run();
    }
}
