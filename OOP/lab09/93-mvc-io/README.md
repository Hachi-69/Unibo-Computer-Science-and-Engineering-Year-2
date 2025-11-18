# Model-View-Controller

Write the interface `Controller`: it must model a simple controller responsible of I/O access.
It considers only the standard output, and it is able to print on it.

Then, implement it in a class in such a way that it includes:

1. A method for setting the next string to print. Null values are not
acceptable, and an exception should be produced
2. A method for getting the next string to print
3. A method for getting the history of the printed strings (in form of a `List` of `Strings`)
4. A method that prints the current string. 
If the current string is unset, an `IllegalStateException` should be thrown


Once the `Controller` is done, implement the `SimpleGUI` class in such a way that:

1. It has a main method that starts the graphical application
2. In its constructor, sets up the whole view
3. The graphical interface consists of a `JTextField` in the upper part of the frame,
a `JTextArea` in the center and two buttons below it: "Print", and "Show history".
SUGGESTION: Use a `JPanel` with `BorderLayout`
4. By default, if the graphical interface is closed the program must exit (call `setDefaultCloseOperation`) 
5. The behavior of the program is that, if "Print" is pressed, the controller is asked to show the string contained in the text field on standard output.
If "show history" is pressed instead, the GUI must show all the prints that have been done to this moment in the text area.