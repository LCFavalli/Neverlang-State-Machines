module sm.StateMachineDeclaration {
    reference syntax {
        declaration:
            StateMachineDeclaration <-- "state" "machine" SMIdentifier "{" SMStatementList "}";
    }

    role(type-checker) {
        0 <typeLang> .{
            initRoot
            define scope file ($file ?? global)
            enter scope
            eval $1
            define scope machine $1 from #2 to #3 [
                run $2 priority machine then validateFinalState
            ]
            exit scope
        }.
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