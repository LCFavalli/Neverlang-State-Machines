module sm.SMIdentifier {
    reference syntax {
        id:
            SMIdentifier <-- /[a-zA-Z]+/;
    }
    role(evaluation) {
        id: .{
            $id.id = #0.text;
        }.
    }
}