# scrabble_cs401
CS 401 Semester Project.
TO COMPILE:
1. Open the shell and navigate to the /bin subfolder.
2. Run the compileme.sh script. This compiles the program with some errors due to circular dependencies, but generates all necessary .class files to run the program.

TO RUN:
1. Open the shell and navigate to the /bin subfolder.
2. Run the following command: java view.claire.MainView




PACKAGE STRUCTURE:
I structured the packages in this project with the goal of keeping track of which files belong to which group member. I also assumed an MVC architecture, but we can change this later if we want to use a different design pattern. Under the src directory, save your files to model/yourname, controller/yourname, or view/yourname, and add a package declaration at the very beginning of your java files such as:     
      package view.claire;
depending on the directory you saved the file to.

In order to compile after you've saved the project to your computer, follow the following instructions: 
  1. Open cmd.exe or your command line program of choice and use it to navigate to the /bin subdirectory of the project.
  2. Use the javac command:
           javac ../src/model/claire/*.java -d .
     replacing model/claire with the directory that your .java files are saved to. 
     The -d option specifies the destination directory that the .class files should be saved to and is required for the package structure        to work.
  3. In order to execute your .class file, use the java command while in the /bin subdirectory:
            java model.claire.WordCheckerDriver
     replacing model.claire.WordCheckerDriver with the package and class that you want to run.
     
     
NOTES ON GIT/GITHUB:
In order to use the GitHub repository effectively, you need to install the Git program onto your computer:  https://git-scm.com/

It's a command-line based program, but you can also install a GUI wrapper/add-on for it to make it easier to use: https://git-scm.com/download/gui/windows

From whatever Git GUI you install, you can login to your GitHub account and download the repositories you have access to onto your machine. Then, Git tracks any changes you make to files within the project, as well as the addition or deletion of new files into the project. 

When you want to save the changes you make to GitHub, you first Commit the changes (save them to the local record/history of the project that Git maintains on your local machine), and then Push the changes (save the changes to GitHub so that the entire group can see them). 
