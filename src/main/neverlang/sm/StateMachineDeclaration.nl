module sm.StateMachineDeclaration {
    reference syntax {
        declaration:
            StateMachineDeclaration <-- "state" "machine" SMIdentifier "{" SMStatementList "}";
    }
    role(evaluation) {
        declaration: .{
            eval $declaration[1];
            StateMachine sm = new StateMachine($declaration[1].id);
            $declaration[2].sm = sm;
            eval $declaration[2];
            $declaration.sm = sm;
        }.
    }
}