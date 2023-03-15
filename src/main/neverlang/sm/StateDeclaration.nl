module sm.StateDeclaration {
    reference syntax {
        declaration:
            StateDeclaration <-- StateType SMIdentifier "{" StateBlock "}";
        normal:
            StateType <-- "state";
        initial:
            StateType <-- "initial" "state";
        final:
            StateType <-- "final" "state";
        empty:
            StateBlock <-- "";
    }
    role(evaluation) {
        normal: .{
            $normal.stateType = State.Type.NORMAL;
        }.
        initial: .{
            $initial.stateType = State.Type.INITIAL;
        }.
        final: .{
            $final.stateType = State.Type.FINAL;
        }.
        declaration: .{
            eval $declaration[1];
            eval $declaration[2];
            State.Type type = $declaration[1].stateType;
            State s = new State($declaration[2].id, type);
            $$StateMap.put($declaration[2].id, s);
            $declaration.state = s;
            $declaration[3].state = s;
        }.
    }

    role(register) {
        declaration: .{
            eval $declaration[2];
            String id = $declaration[2].id;
            State s = $$StateMap.get(id);
            $declaration[3].state = s;
            eval $declaration[3];
        }.
    }
}