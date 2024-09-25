package com.toxicstoxm;

import com.toxicstoxm.Application.ExampleApp;

import java.io.File;

public class Main {
    // default project directory path
    public static String projectDir = System.getProperty("user.home") + "/YAJSI-Example";

    public static void main(String[] args) {

        // tries to get the desired project directory path from the first CLI argument
        if (args.length != 0 && !args[0].isBlank()) {
            projectDir = args[0];
        }

        // loads the project directory
        File projectDir = new File(Main.projectDir);

        // verifies that the project directory exists
        // if it doesn't exist, tries to create it and exits if an error occurs
        if (!projectDir.exists()) {
            if (!projectDir.mkdirs()) {
                System.out.println("Failed to create project directory '" + projectDir + "'!");
                System.exit(1);
            }
        }

        // Create a new instance of the main app and run it;
        var app = new ExampleApp();

        // initialize the main app
        // run it and then call shutdown to save
        app.init();
        app.run();
        app.shutdown();

        System.exit(0);
    }
}