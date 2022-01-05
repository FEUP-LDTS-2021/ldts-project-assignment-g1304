

# LDTS_1304 - Asteroids

## GAME DESCRIPTION

**Asteroids** is a space-themed multidirectional shooter arcade game where you control a spaceship to destroy asteroids and flying saucers. Be careful not to collide with the asteroids that are all around you, and avoid counter-fire from the saucers. The game becomes harder as the number of asteroids increases each time the player destroys them all. When you achieve 10000 points, you earn an extra life. The machine "turns over" at 99,990 points, which is the maximum high score that can be achieved.

This project was developed by Afonso Baldo, João Teixeira and José Gaspar for LDTS 21⁄22.

## FEATURES
 - [x] Movement - The spaceship is able to move in all directions when the arrow keys are pressed.
 - [x] Shooting - The spaceship can shoot laser beams. 
 - [x] Asteroids - The asteroids appear randomly throughout the map and move in a single random direction. 
 - [x] Enemy Ships - The enemy flying saucers appear randomly on the map and try to shoot at your spaceship.
 - [ ] Player Collisions - If the player collides with an asteroid, enemy ship or with a laser beam, it dies.
 - [x] Asteroids Collisions - If the asteroid collides with a ship, kills it.
 - [ ] Asteroid Splitting - The asteroids get split in half when shot by the laser beams. The smaller ones get instantly destroyed.
 - [x] Borders of the Map - When an object crosses the borders of the map, it appears on the opposite side.
 - [ ] Score - When the player destroys asteroids and enemy ships, he gains points.
 - [ ] Lives - The player has an amount of lives, that decreases everytime he dies.
 - [ ] Game Over - When the player runs out of lives, the game ends.
 - [ ] Instructions - A page that shows the basic controls for the game.
 - [ ] Leaderboard - When the user loses, his score is saved to a file.

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
-   Introduce new subscriber classes without having to change the publisher’s code (and vice versa if there’s a publisher interface).
-   Establish relations between objects at runtime.

### CREATING OBJECTS

**Problem in Context**

The entitities of our game have significant similarities, but demonstrate no reuse of common interface or implementation. Considering this, if they were separate classes, when we wanted to make a change common to all the components, more effort would be expendend, because we would have to change all of the component's classes. This way, we came to the conclusion that we had to standardize an architectural model for our entities, but allow for individual applications to define their own domain objects and provide for their creation.

**The Pattern**

We decided to implement the **Factory** pattern. This design pattern defines an interface for creating objects, but lets subclasses decide which classes to instantiate.

**Implementation**

![](image/FactoryUml.png)

- [MovingObject](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/model/Entities/MovingObject.java)
- [LaserBeam](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/model/Entities/LaserBeam.java)
- [Asteroid](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/model/Entities/Asteroid.java)
- [EnemyShip](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/model/Entities/EnemyShip.java)
- [Creator](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/model/Entities/Creator.java)
- [LaserBeamCreator](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/model/Entities/LaserBeamCreator.java)
- [EnemyLaserBeamCreator](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/model/Entities/EnemyLaserBeamCreator.java)
- [AsteroidCreator](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/model/Entities/AsteroidCreator.java)
- [EnemyShipCreator](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1304/blob/main/src/main/java/model/Entities/EnemyShipCreator.java)

**Consequences**

Using the **Factory** pattern has the following benefits:

- We managed to move the entities creation code into one place in the program, making the code easier to update.
- We can introduce new entities into the application without changing existing code.
- There is a separation between creation and the entities themselves.

### CHANGING PROGRAM STATES

**Problem in Context**

Developing the menu, we came to the conclusion that there was a finite number of states which our application could be in, at any given moment. The program needed to be able to switch from one state to another instantaneously. However, depending on a current state, the program may or may not switch to certain other states.

**The Pattern**

The most obvious solution to our problem is implementing the **State** pattern. With this pattern, we can separate all the states in individual classes and implement their specific methods in these classes.

**Implementation**

![](image/StatesFA.png)

![](image/StatesUml.png)

**Consequences**

Applying the **State** pattern allows to:

- Organize the code of the various states into individual classes
- Introduce new states in a much easier way, without having to change existing state classes or the context.
