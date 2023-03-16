language sm.StateMachinesLSPLang {
    slices
        sm.StateMachineDeclaration
        sm.SMIdentifierAlias
        sm.SMStatementList
        sm.StateDeclaration
        sm.EventList
        sm.EventDeclaration
    endemic slices
        sm.LSPEndemicSlice
    roles syntax <+ type-checker
    rename {
        StateMachineDeclaration --> Program;
    }
}