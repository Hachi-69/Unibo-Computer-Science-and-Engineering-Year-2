# mini MVC

The class `Controller` class must implement a simple controller responsible of I/O access. 
It considers a single file at a time, and it is able to serialize objects in it.

Implement this class with:

1. A method for setting a File as current file
2. A method for getting the current File
3. A method for getting the path (in form of String) of the current `File`
4. A method that gets a `String` as input and saves its content on the current file.
This method may throw an `IOException`.
5. By default, the current file is "output.txt" inside the user home folder.

A String representing the local user home folder can be accessed using `System.getProperty("user.home")`.
The separator symbol (/ on *nix, \ on Windows) can be obtained as String through the method `System.getProperty("file.separator")`.
The combined use of those methods leads to a software that runs correctly on every platform.

Once the `Controller` is done, implement `SimpleGUI` class in such a way that:

1. It has a main method that starts the graphical application
2. In its constructor, sets up the whole view
3. The graphical interface consists of a `JTextArea` with a button "Save" right below (see `src/test/resources/ex02.png` for the expected result). 
4. SUGGESTION: Use a `JPanel` with `BorderLayout`
5. By default, if the graphical interface is closed the program must exit (call `setDefaultCloseOperation`)
6. The program asks the controller to save the file if the button "Save" gets pressed.

See `src/test/resources/ex02.png` to verify the expected aspect.

Finally, implement `SimpleGUIWithFileChooser` class as follows:

1. Add a `JTextField` and a button "Browse..." on the upper part of the graphical interface.
Suggestion: use a second `JPanel` with a second `BorderLayout`, put the panel in the North of the main panel, 
put the text field in the center of the new panel and put the button in the line_end of the new panel.
2. The JTextField should be non modifiable. And, should display the current selected file.
3. On press, the button should open a `JFileChooser`. The program should use the method `showSaveDialog()` to display the file chooser, 
and if the result is equal to `JFileChooser.APPROVE_OPTION` the program should set as new file in the `Controller` the file chosen. 
If `CANCEL_OPTION` is returned, then the program should do nothing. 
Otherwise, a message dialog should be shown telling the user that an error has occurred (use `JOptionPane.showMessageDialog()`).
4. When in the controller a new `File` is set, also the graphical interface must reflect such change. 
Suggestion: do not force the controller to update the UI: in this example the UI knows when should be updated, so
try to keep things separated.

See `src/test/resources/ex03.png` to verify the expected aspect.
