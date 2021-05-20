# Milstone 3 Final Submission: Testing the System and Discussion of Design Patterns
## Milestone 2: https://github.com/MilanowskiJ/jupiter-universal-experimenter-group-3/tree/Milestone2
## Demo: To be added (?)
## Running the System
  To run the system, execute JUMPSystem.java's main file. The system uses CLI and entering in any text (other than a valid command) will display a list of all the valid commadnds


## Testing UC4
Start by entering:

    makeMacro
  
  Then follow the UI prompts to enter data into the system.
  
## Testing UC5 <<To be added, Jake?>>



## Testing UC6 
Start by entering:

    editMacro
    
Then follow the system's prompts.    
    
    
## Factory Pattern Satisfaction
This design uses the factory pattern for handling the translation of text input into executables (this is a stripped down command pattern, without the undo/redo functionality). The creation of a new executable is as follows:
  
  1.) Scientist enters command input text to the input reader
  2.) Input reader, which has the factory, passes the input from the user into the factory
  3.) The factory processes the input and returns the appropriate executable (UIProcess)
  4.) InputReader passes that executable to the JUMPSystem for execution

This design makes use of an abstract superclass for the factory, so the InputReader is only dependent on the abstract superclass, not on the concrete factory.  This means that we could extend the system by adding a new way of handling inputs without having to modify existing code.
  
##Singleton pattern Satisfaction - Jake
  
## Strategy Pattern Satsfaction
To see one place where the strategy pattern is being used, look at the UIProcess interface in the presetation layer.  All commands being input into the system are implemented through the strategy pattern.  Running through one of the use cases shows the functionality of this strategy pattern implementation.  When adding a new strategy (command), a programmer would:

  1.) Make new Java class that implements the Cmd interface


  2.) Put new strategy in the new class's "execute" function (function for the Cmd interface)


  3.) Add instantiation of this strategy into the InputReader file (one line of code)



Implementing the strategy pattern in this way keeps this portion of our system open to extension by adding new classes that implement the interface, but closed to modification because adding new strategies does not requrie modificiation to any existing code (outside of one line to instantiate it).


  
## Observer Pattern Satisfaction
This system uses the observer design pattern to handle communication with the (simulated) UAV. The CommunicationSubsystem simulates receiving messages from the UAV by running a server asynchronously from the jumps system. The observers watch the CommunicationSubsystem and whenever it receives a JSON file from the dummyUAV, it notifies the Observer which grab all received JSON files from the Comm system and store them. Then the JumpsSystem may look at the observer whenever is required and get all of the sent JSON files to update the database. The observer pattern is beneficial here because the communication subststem is running asynchronously from the rest of the system, so the jumps system should not stop to check the subsystem. Instead it can simply ask the observer for whenever it has new reports. It can also be expanded with new types of observers for when the need arises.


