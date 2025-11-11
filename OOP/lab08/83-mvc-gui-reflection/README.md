# Work with MVC

This application is a simple "draw number" game with an MVC architecture (ECB-like).
`DrawNumber` is the application model (Entity in ECB).
`DrawNumberController` represents the controller (Control in ECB).
Note that the controller can be reused across *any* kind of view,
whether graphical or on command line (or network, or whatever).
`DrawNumberView` represents the view interface (Boundary in ECB).

## Multiple views

* Modify the architecture in such a way that multiple views are supported at the same time.
* Develop a new view (class implementing `DrawNumberView`) that writes on stdout.
  This view is *output only* (it does not send new input to the controller).
* Extend the controller to support multiple views.
  To doing so, make sure that the controller has a collection of views (and not a single one),
  and that it notifies all of them (for instance with a `for` cicle) every time a new event should be displayed.
* Attach at the same time two graphical views and a console view, and verify that the application works as expected.

## Reflection loading

Instead of hard-coding the implementation of the views, in `LaunchApp` do as follows:
* Via reflection, load the classes that implement `DrawNumberView` by their name
  * Note: you need the *qualified* name
* Find the 0-ary constructor
* Using a `for` cycle, attach three graphical and three command line views built with the previously found constructor
* Launch the application
