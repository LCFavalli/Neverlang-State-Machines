slice sm.SMIdentifierAlias {
    concrete syntax from sm.SMIdentifier
    module sm.SMIdentifier with role type-checker, evaluation, evaluation => ids, evaluation => register
}