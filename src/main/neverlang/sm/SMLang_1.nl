language sm.SMLang_1 {
    slices
        sm.StateMachineDeclaration
        sm.EmptySMStatementList
        sm.SMIdentifier
    endemic slices
        sm.StateCache
    roles syntax <+ evaluation
    rename {
        StateMachineDeclaration --> Program;
    }
}