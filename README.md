# Presentation
### This is a game inspired by Brians Logic Puzzles at (http://www.logic-puzzles.org/)
#### Basic Game Functions
- ability to choose board size
- the board auto generates puzzles
- hint will populate the clues box
- clear errors will clear anything wrong with the board
- undo will allow the user to return to previous states of the board
- start over will clear the board
![gameFunction](https://cdn.discordapp.com/attachments/857973303880187916/950453575279067146/show.gif)
#### Upon Completion
![win](https://cdn.discordapp.com/attachments/857973303880187916/950453575673327666/win.gif)
#### Known Bug
Upon pressing start over the hints button stops functionality. This is because instead of clearing the board we create a new board instance, this seems at first fast and easy, but doing this, the board loses it's reference to it's hints update. This could be easily solved by resetting all the cells manually.
# Project Discription
## CS-225-Assignment-2

Now that you have played the puzzle games (http://www.logic-puzzles.org/) you will write the program for it.
Your program will introduce one puzzle only, but your code should be written such that it can be easily extended to offer more puzzles.
Note that, you need to provide clues, clear errors, and hints functionality; the same way that was given in the puzzles you solved. The GUI can be simple, but have the essential parts.
Start with an easy puzzle with 3x4 grid.

Your final product includes:

    the list of 20 requirements, fully organized, complete, well written and edited
    the UML design for classes and their relationships
    the code that includes comments (class, method, complex code)
        On top of every file, the name of the student who has written or fixed/debugged should be included. Do not add everyone's name on every file.


Each team will submit ONE file on Bb for each part of assignment, listed below. You can resubmit 3 times, one per part.

The deadlines and grade breakdown for each part is:

    Requirements (20% of the grade)  2/14 - Word document (one per team)
    UML diagrams - class diagrams (10% of the grade) 2/18 - jpg file (one per team)
    Completed code (70% of the grade) 2/25 - (one per team)
        All previous instructions for submission of code applies -- zip (.zip not any other) of only java files (no package info that your IDE adds) and other necessary files
        The zip should also include the final versions of both the requirement document and UML design

Teams:
Arjun & Christian

The first task for the team is to make sure that everyone is aware of communication and scheduling strategies by setting the ground rules for how your team will work together.  
Establishing ground rules early in the project will help your team alleviate frustrations, meet deadlines and communicate effectively. For example:

    Team member's contact information
        cell phone
        e-mail: which e-mail address?
        Other methods
    How will you contact each other? When will you respond?
        the best day, time, and method to reach each other
        what is everyone’s schedule?
            if your teammates are trying to reach you, how long to wait before you respond?
    What are the basic rules (general expectations for every member) for teamwork to keep the project going?
    How can the team manage any issues with timely and effective communication?
    What are some of the strategies that will be used to keep the project on task

        everyone adheres to deadlines set by the team
        majority rules
        treat each other with respect and professionalism
