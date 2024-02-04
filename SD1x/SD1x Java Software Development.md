# Variables

## Variables
- Declare data type in java
- double (=float)
- int
- String
- char (=one character, denoted in single quotes)
- boolean (&& || !)
- 'Single quotes' for char and "double quotes" for String

## Loops
- while (condition) {} - Use when you dont know when loop will end
- for (start, condition, increment) {} - Otherwise use this
- Increment can use i++ and i--

x % y computes remainder when x is divided by y

## Rules
- Be consistent
- Do it right the first time

## Style
- Indend nested code
- } on the following line
- Break up long lines (max 80 chars)
- Use spaces between operators (eg. `x + y` not `x+y`)
- Use meaningful names
- Constants are in all caps and _
- Comments: /**    */ is documentation for outside world

## Arrays:
- double[] myArray = new double[365]
- Define how long it is
- Define datatype inside
- Uses 0 indexing

## ArrayLists
- Flexible length
- Unknown size of data collection
- `ArrayList<String> nameList = new ArrayList<String>()`
- Doesnt take primitive datatypes
- Use "Double" "Boolean" and "String" instead of int, double, boolean (just wrappers so they work in Java)
- NB:
	- `array.length`
	- `arraylist.size()`
- import java.util.*
- .contains(i) .get(i) .remove(i)
- `ArrayList<Card>` - Card can be any object
- Collections.shuffle(Cards)

## Enhanced For Loop
- for (datatype : collection) {}
- Cannot modify the collection when using the enhanced for loop
- "concurrent modification" exception
- Just do looping the other (old) way if you need to change it

# Classes
- Model real world "nouns" as classes (eg. Car)
- Model real world "verbs" as methods (eg. Sell)
- "void" means the function returns nothing

## Constructor
- A method with the same name as class and no return type
- Like an `__init__` function
- Parameters in constructor method are passed when initialising class
- "this.variable = variable" sets the class attribute to variable (like "self" in python)
- `this` is a special word that you cannot use for variables

## Methods
- by format "return_type method_name(parameters) {}"
- Variables defined here are local to the method (within scope)
- void is nothing being returned
- Can optionally include the main class that is called when the class/file is run

## Documentation
- /**  Some documentation */
- @param = What does a parameter do
- @return = What does the method return
- Every public method should have a javadoc
- Can generate it as an html file

# Testing & Debugging

## Unit Testing
- Black box testing = Test if you get expected output from input (dont care how)
- White box testing = You test HOW the function does something
- Unit testing = Testing things in small blocks (also encourages you to write more modular code)

## Test Driven Development
- Use the test to specify what you want a funciton to do
- Write the test first
- Then write code that will satisfy the tests

## Assertions
- assertEquals(expected, function)
- Wont work with objects (need to write your own equals method)
- Works for primitive data types

## Debugging
- Errors give you a stacktrace
- NullPointerException = Referencing something that doesnt exist
- Eclipse debugger
- Breakpoints
- F6 to skip over

## Exception Handling
- try {code}
- catch (exception e) {e.printStackTrace()}
- Some exceptions are forced to be caught ("checked exceptions")
- Catch blocks are ordered
- finally {} what you do after the catch blocks regardless of if there is an error or not

## Reading and Writing to a file
- Exceptions like no permission for file / file name doesnt exist (checked ag
- File newfile = new File("path/to/file.txt")
- Use scanner object again
- FileScanner scnr = new FileScanner(path, append?)
- PrintWriter printer = new PrintWriter(scnr)

# Classes & OOP

## Static
- Static = Belongs to the class  as a whole (not specific to an instance)
- You do not have to create an instance of a class to use its static methods
- For person, non-static method would be "introduce yourself" whereas a static method could be "walk"
- Static instance variables - constants (written in ALLCAPS)
- Static instance variables can be controlled by any of the instances of the class (eg bank account numbers)

## Access Modifiers
1. Public = Accessible everywhere
2. Private =  Accessible within the same class
3. Default  = Accessible within the same package (no access modifier specified)
4. Protected = Seen later

- Private variables can have "getter()" and "setter()" methods that control how the value is changed
- Can use code to control the values that the variable can be set to in the "setter()" function
- Generally: Private instance variables & public methods

# Polymorphism

Polymorphism = Can take many forms (central concept in java)

## Overloading
- A method existing in different forms
- A class has multiple methods with the **same name**
- Rules of overloading
    - Cannot have two methods with same signature
		- Signature = functionName(x,y,z): the name and order of variables together is signature
				- Not including the return type
		- Use the same method name for functions that do the same thing
		- DRY: Dont Repeat Yourself
		- Overloading helps with this. You can run the basic version of an overloaded method in another method of the same name
		- Java will always use the most specific method
		- A double can be passed as an int, but an int **cannot** be passed as a double
		- You can overload constructors (usually to set default values)
		- Constructor A can only call constructor B if it is the **first** thing it does
- Java can figure out which version of the method it should run
- Eg. 
		- Running a function on different data types
		- Putting defaults in a function

NB: Casting: i = (int) 3.5, makes 3.5 into an int

## Overriding
- Two methods named the same but in different classes
- Superclass and subclass
- Uses the word "extends" to make a more specific version of the class you are extending
- "extends" = "is a"
- Constructor of child will call constructor of super/parent
- Can do this manually with:
		- `super(...)` - Runs a specific constructor of the superclass
		- `this(...)` - Runs a specific constructor of the subclass
- How?
		- Write a method in the subclass with:
				- Same signature as the superclass
				- Same return type
				- Is not **more** private than the superclass method
- If you do this, the method of the subclass **overrides** the superclass
- Why would you do this?
		- Override the `toString()` method of your class
- Calling `toString()`
	- Called automatically if you use a `println` statement
- Calling  `equals()`
	- In java `==` tests if two things are the **same** object (ie. Same memory location)
	- This works for primitive types, but can cause issues when comparing objects
	- the `equals()` method is better when comparing objects
	- Because **you can choose** what equality means

## Abstract Classes
- Abstract method = method with no method body (no code inside it)
- Abstract class = Any class with an abstract method
- `public abstract void draw(int size);`
- `abstract class myClass {...}`
- You **cannot** create an instance of an abstract class
- You use them to create classes that inherit it
- Eg. Shape, Square, Circle, but you dont want people to be able to just create "shape"
- When extending an abstract class, the subclass **must override abstract methods**

## Interfaces
- A group of related methods with empty bodies
- Containts **no methods with a body**
- Its how you ensure that a class follows a certain design
- `public interface Item {...}`
- All methods in an interface are implicitly abstract
- `implements` keyword
		- `public class myListener implements myInterface`
		- Can implement multiple interfaces
		- **Implement** an interface, **extend** a class
- An interface can extent another interface
- Why interfaces?
		- You want to extent multiple classes
		- Making games can use `ruleset` or something like that
- `dog instanceof animal`
- Argues this should not be used

NB: Generally, interfaces are used more than abstract classes










