module example.ExampleModule {
    reference syntax {
        Program <-- "Hello" "world" "!";
    }
    role(hello) {
        0 .{
            System.out.println("Welcome to the Neverlang Language Workbench!");
        }.
    }
}
