
# LDTS_1304 - Asteroids
Control the spaceship to destroy asteroids and flying saucers in this classic arcade multidirectional shooter video game. Be careful not to collide with the asteroids that are all around you, and avoid counter-fire from the saucers.

This project was developed by Afonso Baldo, João Teixeira and José Gaspar for LDTS 21⁄22.

## DESIGN PATTERNS

### FUNDAMENTAL ORGANIZATION OF THE CODE

**Problem in Context** 

When we were organizing the project, we realized that the code would get very messy and hard to read without an appropriate structure.

**The Pattern**

We have applied the **MVC** pattern. This pattern separates the game logic from the display code, allowing us to easily fix bugs and improve code readability.

**Implementation**

The following image shows how we implemented the pattern.

![](image/mvc.svg)

These packages can be found in:

- [Controller](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/tree/main/src/main/java/control)
- [Model](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/tree/main/src/main/java/model)
- [View](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/tree/main/src/main/java/view)

**Consequences**

The use of the MVC Pattern in the current design has the following benefits:

- When we want to add a new feature to our game, the code will be more modular and organized. All we have to do is create another controller, model, or view, depending on the requirements of the new feature. Then it is easier to integrate it with the existing code.
- The code is both more readable and easier to write.
- Allows for a more adjustable code. For example, if we want to change the game's aesthetics, we can easily update its View.

However, applying MVC requires more thinking and organizing before-hand.

### INPUT MANAGEMENT

**Problem in Context**

We realized that we had to inform the objects of the application when an input was read, so it could act accordingly.

**The Pattern**

To solve this issue, we implemented the **Observer** pattern. This pattern lets you notify multiple objects about any events that happen to the object they’re observing.

**Implementation**

We implemented a class, InputListener, that works as the publisher, reading the events and notifying the interface, InputObserver, that works as the subscriber.

![](image/ObserverUml.png)

- [InputListener](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/control/input/InputListenner.java)
- [InputObserver](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/control/input/InputObserver.java)
- [PlayerController](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/control/PlayerController.java)
- [GameController](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/control/states/GameController.java)
- [MenuController](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/control/states/MenuController.java)

**Consequences**

Using the **Observer** Pattern allows to:
-   You can introduce new subscriber classes without having to change the publisher’s code (and vice versa if there’s a publisher interface).
-   Establish relations between objects at runtime.

### CREATING OBJECTS

**Problem in Context**



#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.

**Example of such a subsection**:

------

#### DATA CLASS

The `PlatformSegment` class is a **Data Class**, as it contains only fields, and no behavior. This is problematic because […].

A way to improve the code would be to move the `isPlatformSegmentSolid()` method to the `PlatformSegment` class, as this logic is purely concerned with the `PlatformSegment` class.

### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

### SELF-EVALUATION

> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.

**Example**:

- John Doe: 40%
- Jane Doe: 60%
## Images
![Original vector-based Asteroids game (Atari 1979), showing ship in... |  Download Scientific Diagram](https://www.researchgate.net/profile/Kc-Collins/publication/262309733/figure/fig2/AS:694796872081408@1542663891658/Original-vector-based-Asteroids-game-Atari-1979-showing-ship-in-centre-and-floating.ppm)
