module sm.SMIdentifier {

    imports {
        neverlang.core.typesystem.symbols.Token;
    }

    reference syntax {
        id:
            SMIdentifier <-- /[a-zA-Z]+/;
    }

    role(type-checker) {
        0 .{
            $0.token = Token.fromASTNode($n, 0);
        }.
    }
    role(evaluation) {
        id: .{
            $id.id = #0.text;
        }.
    }
}