module sm.StateDeclaration {

    imports {
        neverlang.typesystem.symboltable.EntryKind;
        neverlang.typesystem.defaults.*;
        neverlang.typesystem.symbols.*;
        sm.typesystem.*;
    }

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

    role(type-checker) {
        0 <typeLang> .{
            try {
                eval $1
                eval $2
                define scope state $2 from #0 to #1 modifier $1.stateType [
                    run $3 priority state
                ]
            }
        }.

        normal: .{
            $normal.stateType = StateModifier.NORMAL;
        }.
        initial: .{
            $initial.stateType = StateModifier.INITIAL;
        }.
        final: .{
            $final.stateType = StateModifier.FINAL;
        }.
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