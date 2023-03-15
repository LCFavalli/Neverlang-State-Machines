module sm.SMStatementList {
    imports {
        neverlang.utils.AttributeList;
        java.util.List;
    }
    reference syntax {
        list:
            SMStatementList <-- StateDeclarationList;
            StateDeclarationList <-- StateDeclaration StateDeclarationList;
            StateDeclarationList <-- StateDeclaration;
    }
    role(evaluation) {
        list: @{
            List<State> states = AttributeList.collectFrom($list[1], "state");
            State initialState = states.stream()
                .filter(State::isInitial)
                .findFirst()
                .get();
            StateMachine sm = $list.sm;
            sm.setState(initialState);
        }.
    }
}