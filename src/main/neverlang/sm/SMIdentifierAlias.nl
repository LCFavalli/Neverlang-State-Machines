slice sm.SMIdentifierAlias {
    concrete syntax from sm.SMIdentifier
    module sm.SMIdentifier with role
        evaluation,
        evaluation => ids,
        evaluation => register
}