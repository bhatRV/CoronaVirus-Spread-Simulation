 
# Project Name : Zombie Apocalypse

## Author : RASHMI VISHNU
 
## HOW TO START THE APPLICATION / HOW TO RUN THE SIMULATION


 **_To run from Eclipse/IntelliJ_**

Run as application "ZombieApocalypse.java   <inputFilePath>"

**_To build the application_**

./gradlew clean build

 Then to Start the application using
   **./gradlew run <filePath>**



#### ASSUMPTIONS and SCOPE for ENHANCEMENT

1. Extensive test cases to cover more scenarios can be added
1. Validations can be enhanced to include more on the inputs. Basic validations are provided for now.
1. User Interface can be provided
1. Assumption:Grid's are generated based on the given input, if X is the input the grid will be a matrix of X by X
1. Assumption: Starting point for the gris is the top Left corner.
3. Assumption: Input will be provided using a file and the order for the input values will be followed. No Validations are provided

 
#### Scenarios covered
1. Zombie can start from any location in the grid
2. Grid's are generated based on the given input, if X is the input the grid will be a matrix of X by X
3. Input is based will be from a file and all the


   
#### PRE configured Data

/input/InputFile.txt

#### Input File Format
```diff


+ 1. Line 1: Grid Dimension.  
        eg: 4 
+ 1. Line 2: Zombie Location 
       eg: x,y 
+ 1. Line 3: creatures Locations seperated by space 
       eg. x1,y1 x2,y2 x3,y3 
+ 1. Line 4: Directions for the Zombie. where ==> D: Down, R: Right L:Left and U: UP 
        eg.DLLURL    
