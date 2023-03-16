language sm.StateMachinesLang {
    slices
        sm.StateMachineDeclaration
        sm.SMIdentifierAlias
        sm.SMStatementList
        sm.StateDeclaration
        sm.EventList
        sm.EventDeclaration
    endemic slices
        sm.StateCache
    roles syntax <+ type-checker < ids <+ evaluation <+ register
    rename {
        StateMachineDeclaration --> Program;
    }
}