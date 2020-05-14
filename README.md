 
# Project Title: Zombie Apocalypse

This is a simulation of a starnge incident due to a deadly infection. Zombies spread infection and keep moving with in a given dimention. This application simulates and counts the infection spread.

### Author : RASHMI VISHNU
 
### Prerequisites

```
Java 1.8
```
## RUNNING THE SIMULATION

**_To run from Eclipse/IntelliJ_**

Run as application "ZombieApocalypse.java  <inputFilePath>"
   eg. ZombieApocalypse.java input/inputFile.txt

**_To build the application_**

./gradlew clean build

 Then to Start the application using
   **./gradlew run <filePath>**



## ASSUMPTIONS AND SCOPE FOR ENHANCEMENT

1. Extensive test cases to cover more scenarios can be added
1. Validations can be enhanced on the inputs. Basic validations are provided for now.
1. User Interface can be provided and input can be as a json
1. Assumption: Grid's are generated based on the given dimention (1st line in the file), if X is the input the grid/matrix will be a SQUARE MATRIX of X by X
1. Assumption: Starting point for the grid/matrix is the top Left corner.
1. Assumption: Input will be provided using a file and the order for the input values will be followed.
 
#### Scenarios covered
1. Zombie can start from any location in the grid
2. Zombie starts moving , and if it finds a creature in its path the creature will be convereted to a new Zombie(Infected)
3. Input is based will be from a file and all the

## PRE configured InputFile

/input/InputFile.txt

## Input File Format
```diff

+ Line 1: Grid Dimension.  
        eg: 4 
+ Line 2: Zombie Location 
       eg: x,y 
+ Line 3: creatures Locations seperated by space 
       eg. x1,y1 x2,y2 x3,y3 
+ Line 4: Directions for the Zombie. where ==> D: Down, R: Right L:Left and U: UP 
        eg.DLLURL  
  ```
#### Sample File 

```diff
4
1,2
2,2 0,1 2,1
DLLDUR

```

 ## OUTPUT 
 
 Output will be displayed on the console along with the zombie positions changes.
 
 
 

     
    
