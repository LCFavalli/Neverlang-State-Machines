// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
import * as vscode from "vscode";
import * as net from "net";
import {
  LanguageClient,
  LanguageClientOptions,
  StreamInfo,
  ServerOptions,
} from "vscode-languageclient/node";
import { workspace } from "vscode";

let client: LanguageClient;
let outputChannelName = "StateMachineLSP";
let outputChannel = vscode.window.createOutputChannel(outputChannelName);
// This method is called when your extension is activated
// Your extension is activated the very first time the command is executed
export function activate() {
  // Use the console to output diagnostic information (console.log) and errors (console.error)
  // This line of code will only be executed once when your extension is activated

  client = new LanguageClient(
    "statemachine",
    outputChannelName,
    getServerOptions(),
    getClientOptions()
  );

  client.start();
  vscode.window.showInformationMessage(
    "Language server for State Machine started successfully"
  );
}

function getServerOptions() {
  const { NODE_ENV } = process.env;
  if (NODE_ENV === "production") {
    let serverOptions: ServerOptions = {
      command: "aspectj-language-server",
      args: [],
      options: {},
    };
    return serverOptions;
  } else {
    outputChannel.appendLine("Start with Socket");
    return async () => {
      let socket = net.connect({
        port: 5123,
      });
      let result: StreamInfo = {
        writer: socket,
        reader: socket,
      };
      socket.on("error", () =>
        vscode.window.showErrorMessage("Language server unreachable")
      );
      return result;
    };
  }
}

function getClientOptions(): LanguageClientOptions {
  return {
    outputChannel,
    synchronize: {
      fileEvents: [workspace.createFileSystemWatcher("**/*.sm")],
    },
    documentSelector: [{ scheme: "file", language: "statemachine" }],
  };
}

// this method is called when your extension is deactivated
export function deactivate() {
  client?.stop();
}
