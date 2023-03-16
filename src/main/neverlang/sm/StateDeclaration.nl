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
        /*
        catch {
            eval $2
            define scope state $2 from 0 to 1 {
                run $3 priority state
            }
        }
        */
        0 .{
            try {

            eval $1;
            eval $2;
            var unit = $ctx.root().<DefaultCompilationUnit>getValue("$DefaultCompilationUnit");
            var helper = $ctx.root().<CompilationHelper>getValue("$CompilationHelper");
            var scope0 = new SymbolTableEntryFactory()
            	.withCompilationUnit(unit)
            	.withToken($ctx.nt(2).getValue($ctx.attr(2, "token")))
            	.withType(new TypeState())
            	.withFoldingRange(Range.foldingRangeFrom($n,0,1))
            	.withEntryKind(EntryKind.DEFINITION)
            	.withCompilationHelper(helper)
                    .withModifier($1.stateType)
            	.bindScopeOrReuse();
            helper.getTaskBuilder()
            	.withContext($ctx)
            	.insideScope(scope0)
            	.withPriority(Priorities.STATE)
            	.withAstNodes($ctx.nt(3))
            	.createAndRegisterTask();

            } catch (neverlang.typesystem.NeverlangTypesystemException e) { e.submit($ctx.root().<CompilationHelper>getValue("$CompilationHelper")); }
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