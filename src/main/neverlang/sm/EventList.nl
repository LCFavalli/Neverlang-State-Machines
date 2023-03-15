module sm.EventList {
    imports {
        neverlang.utils.AttributeList;
        java.util.List;
    }
    reference syntax {
        block:
            StateBlock <-- EventDefinitionList;
        list:
            EventDefinitionList <-- EventDefinition EventDefinitionList;
        item:
            EventDefinitionList <-- EventDefinition;
    }
    role(register) {
        block: @{
            List<EventRecord> transitions = AttributeList.collectFrom($block[1], "transition");
            State s = $block.state;
            transitions.forEach(s::register);
        }.
    }
}