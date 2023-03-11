module example.ExampleModule {
    reference syntax {
        Program <-- Hello World "!";
        Hello <-- "Hello";
        Hello <-- "hello";
        World <-- "World";
        World <-- "world";
    }
    role(hello) {
        0 .{
            System.out.println("Welcome to the Neverlang Language Workbench!");
        }.
    }
}
