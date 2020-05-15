 
# Project Title: Zombie Apocalypse

This is a simulation of a strange incident due to a deadly infection. Zombies spread infection and keep moving with in a given dimention. This application simulates and counts the infection spread.

### Author : RASHMI VISHNU
 
### Prerequisites

```
Java 1.8
```
## RUNNING THE SIMULATION

**_To run from Eclipse/IntelliJ_**

```diff
(To run simulation using the sample inputs)
RUN: ZombieApocalypse.java 
```

**_To Run with custom inputs from intelliJ_**:
```diff

"ZombieApocalypse.java  <inputFilePath>"
   eg. ZombieApocalypse.java inputFile.json

```

**_To Run with the jar from command prompt_**:

```diff

Build the project using the below command
   ./gradlew clean build

To run with  default input use the below command: 
   java -jar ./build/libs/zombie-apocalypse-simulation.jar

To run with  custom input
   java -jar ./build/libs/zombie-apocalypse-simulation.jar <fileName>
      
```
**_To build the application_**
```diff
./gradlew clean build

Then to Start the simulation using default input File which has pre populated test data
 ./gradlew run
  
```
## ASSUMPTIONS AND SCOPE FOR ENHANCEMENT

1. Extensive test cases to cover more scenarios can be added
1. Validations can be enhanced on the inputs. Basic validations have been provided for now.
1. User Interface can be provided currently uses input as json from a file
1. Assumption: Grid's are generated based on the given dimention (1st line in the file), if X is the input the grid/matrix will be a SQUARE MATRIX of X by X
1. Assumption: Starting point for the grid/matrix is the top Left corner.
1. Assumption: Input will be provided using a file as Json
 
#### Scenarios covered
1. Zombie can start from any location in the grid
2. Zombie starts moving , and if it finds a creature in its path the creature will be convereted to a new Zombie(Infected)
3. Input is based will be from a file and all the

## Pre configured InputFile and It's Format

FileName: inputFile.json

Format:
```diff

+ Line 1: Grid Dimension.  
        eg: x=4 y=4
+ Line 2: Zombie Location 
       eg: x,y 
+ Line 3: creatures Locations seperated by space 
       eg. x1,y1 x2,y2 x3,y3 
+ Line 4: Directions for the Zombie. where ==> D: Down, R: Right L:Left and U: UP 
        eg.DLLURL  
  ```
## Sample Input (JSON file)

 ```json
{
  "gridDimension": {
    "x": "4",
    "y": "4"
  },
  "zombieLocation": {
    "x": "2",
    "y": "1"
  },
  "creatureLocations": [
    {
      "x": "0",
      "y": "1"
    },
    {
      "x": "1",
      "y": "2"
    },
    {
      "x": "3",
      "y": "1"
    }
  ],
  "command": "DLUURR"
}

  ```

 ## OUTPUT 
 
 Output will be displayed on the console along with the activeZombie positions at the end with INFO LOG like below:
 
 
  ```diff
 ## 19:06:40.121 [main] INFO  c.a.z.apocalypse.ZombieApocalypse - CONSOLIDATED POINTS TALLY
 ## 19:06:40.121 [main] INFO  c.a.z.apocalypse.ZombieApocalypse - Zombies score : [ 3 ]
 ## 19:06:40.121 [main] INFO  c.a.z.apocalypse.ZombieApocalypse - Zombies positions [ (1,2)(0,1)(3,1)(2,1) ]
 ```

 
 
 

     
    
