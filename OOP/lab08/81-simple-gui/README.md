# Simple View

# Ex01: simple monolithic GUI

## Understand
1. Read the class carefully
2. Read the comments carefully
3. Note the use of ``Toolkit`` to get the current screen resolution, and dimension the window correctly
4. Note the use of ``JFrame. setLocationByPlatform()`` to delegate the window positioning to the system composer
   (the part of the graphical stack in charge of effectively drawing windows, borders, decorations and effects).

## Do
### Part 1
1. Create a new `JPanel`
2. Use an horizontal `BoxLayout` as layout
3. Set the new `JPanel` as the only content of the center of the previously existing `BorderLayout`
4. Add the `JButton` to the new panel
5. Test your application: it should appear similar, but the button will get smaller
6. In `display()`, use `JFrame.pack()` to resize the frame to the minimum size prior to displaying

### Part 2
1. Create a new text field labeled "Result"
2. Add it to the external `JPanel` in such a way that it gets on the top of the frame (`Borderlayout.NORTH`)
3. Test your application. Verify that you can see a new Text field, but it is useless

### Part 3
1. Modify the application in such a way that the text field displays the same number that gets printed
2. Test it
