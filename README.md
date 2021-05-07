# Milstone 2 Final Submission: Testing the System and Discussion of Design Patterns

## Running the System
  To run the system, execute JUMPSystem.java's main file. The system uses CLI and entering in any text (other than a valid command) will display a list of all the valid commadnds
  
  A teams recording of a video demonstration of this system can be found here:


## Testing UC1
Start by entering one of the following commands:

    makeSampleExperiment
  
    makeReagentExperiment
  
    makeComplexExperiment
  
  Then follow the UI prompts to enter data into the system.
  
## Testing UC2
Start by entering:

    processReagentExperiment
    
Then follow the system's prompts. 


## Testing UC3 
Start by entering:

    processSampleExperiment
    
Then follow the system's prompts.    
    
    
## Strategy Pattern Satsfaction
To see one place where the strategy pattern is being used, look at the UIProcess interface in the presetation layer.  All commands being input into the system are implemented through the strategy pattern.  Running through one of the use cases shows the functionality of this strategy pattern implementation.  When adding a new strategy (command), a programmer would:

  1.) Make new Java class that implements the Cmd interface


  3.) Put new strategy in the new class's "execute" function (function for the Cmd interface)


  5.) Add instantiation of this strategy into the InputReader file (one line of code)

Implementing the strategy pattern in this way keeps this portion of our system open to extension by adding new classes that implement the interface, but closed to modification because adding new strategies does not requrie modificiation to any existing code (outside of one line to instantiate it).


  
## Observer Pattern Test case
1.) 


