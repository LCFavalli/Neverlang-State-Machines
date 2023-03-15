language sm.SMLang_2 {
    slices
        sm.StateMachineDeclaration
        sm.SMIdentifier
        sm.SMStatementList
        sm.StateDeclaration
    endemic slices
        sm.StateCache
    roles syntax <+ evaluation
    rename {
        StateMachineDeclaration --> Program;
    }
}