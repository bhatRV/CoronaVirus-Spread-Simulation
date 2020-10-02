 
# Project Title: COVID Apocalypse

This is a simulation of a strange incident due to a deadly infection. COVID spread infection and keep moving with in a given dimention. This application simulates and counts the infection spread. 
Note: name zombie ==> covid in all the class names

### Author : RASHMI VISHNU
 
### Prerequisites

```
Java 1.8
```
## RUNNING THE SIMULATION

**_To Run with the jar from command prompt_**:

```diff

Build the project using the below command
   ./gradlew clean build

To run with  default input use the below command: 
   java -jar ./build/libs/zombie-apocalypse-simulation.jar

To run with  custom input
   java -jar ./build/libs/zombie-apocalypse-simulation.jar <fileName>
      
```
**_To run from Eclipse/IntelliJ interface_**

```diff

(To run simulation using the sample inputs)
 ZombieApocalypse.java 

(To run with  custom input)

"ZombieApocalypse.java  <inputFilePath>"
   eg. ZombieApocalypse.java inputFile.json

```


**_To build the application_**
```diff
./gradlew clean build

Then to Start the simulation using default input File which has pre populated test data
 ./gradlew run
  
```
## ASSUMPTIONS AND SCOPE FOR ENHANCEMENT

1. Default Log level is at INFO, and can be configured from resources/logback.xml 
1. Extensive test cases to cover more scenarios can be added
1. Validations can be enhanced on the inputs. Basic validations have been provided for now.
1. User Interface can be provided currently uses input as json from a file
1. Assumption: Grid's are generated based on the given dimention (1st line in the file), if X is the input the grid/matrix will be a SQUARE MATRIX of X by X
1. Assumption: Starting point for the grid/matrix is the top Left corner.
1. Assumption: Input will be provided using a file as Json

## Pre configured Input File and It's Format

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
=======================POINTS TALLY=================
20:35:46.581 [main] INFO  c.a.z.apocalypse.ZombieApocalypse - COVID score : [ 3 ]
20:35:46.582 [main] INFO  c.a.z.apocalypse.ZombieApocalypse - COVID positions [ (1,2)(0,1)(3,1)(2,1) ]

 ```

 ## ADDITIONAL INFORMATION
 
1. Test reports can be found at:
 build/reports/tests/test/index.html
 
  
2. Coverage reports can be found at :
   build/jacoco/coverage/index.html
   
3. Default Log level is at INFO, change to DEBUG for more details

      
    
