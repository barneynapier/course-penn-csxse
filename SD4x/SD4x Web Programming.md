#course 
[[Computer Science]]

# HTML & CSS

## WEB SETUP
- URL like https://learning.edx.org/course/course-v1:PennX+SD4x+3T2020
- Organised as protocol->subdomain->domain->filepath->query->params->Fragment
- HTTP = Plain text protocol using client/server model
- req, res model

## HTML
- Hypertext markup language
- Static rendering
- Styling: many elements can belong to one class, but id is unique to the element
- Links: You can reference other parts of a page with the id and #
- Responsive web design: Adjusting styling based on the device (bootstrap)

# Javascript
- Dynamic
- falsy values: undefined, NaN, 0, False, null, ''
- truthy: all the others
- `var` to declare a variable 
- Core variables: number, string, boolean, null, undefined

## Arrays
- Start index at 0
- `array.push(x)` - put x at end
- `array.unshift(x)` - put x at start

## Objects
- JSON format dictionaries
- access properties as a string
`var person = {name : "barney", age: 23}`
`person["age"]`
`23`

## Control structures (comparisons and conditions)
- `||` is OR
- `&&` is AND
- `!` is NOT
- `==` is for comparing values
- `===` is for comparing values AND types
- JS will convert string to number if it can. So 5 < "20" will give True
- Comparing anything to NaN gives False
- Non numeric strings compared alphabetically ("zebra" > "giraffe" is true because Z after G)
- Objects only equal if one is the alias of another

## Functions
`function function_name(param1, param2) {
		function things
		function things
}`
- Passing arrays through functions: `array.forEach(function)`, `array.map(function)`, `array.every()` `array.some()`
- `.forEach()` doesnt return anything, while `.map()` returns a new array with function results
- Pass by value: If x=4 and I do function(x). It does it on 4 and not on x
- Pass by reference: If obj={name:'table'} and I do function(x). It does it on obj, not {name:'table'}
- Javascript functions are objects!

## Prototypes
- Prototype is like a base function/object. Every function has a __proto__ attribute which references what it inherits from
- Prototype functions return OBJECTS
- eg. `var barney = new Person('barney', 23)`, where person is the prototype, accessed by barney.prototype
- eg2. to create another proto that inherits, i would just add `this.__proto__ = new Person(name, age)`. Make sure the new class takes (name, age) as arguments too 
- Can edit prototype from children by doing `Person.prototype.job = 'farmer'`


## Useful builtin string functions
`.length()`
`.toUpperCase()`
`.toLowerCase()`
`.trim()`
`.includes()`
`.startsWith()`
`.endsWith()`
`.search()`

## Regular Expressions
- /regex/
- Not quite a string
- /regex/.test('String to test on')
- Case sensitive

/[012]/ = Any of 0,1,2
/[a-z]/
/[^a-z]/ not letter
/[0-9a-z]/ = digits and letters
/[0-9][a-z]*[0-9]/ = digit followed by a letter followed by any (optional) number of digits or letters
/[0-9][a-z][0-9]/ = Digit, then letter, then digit
/[0-9][a-z]?/ = Digit, followed by at most 1 letter
/^[0-9][a-z]/ = Starts with
/[0-9][a-z]$/ = Ends with
/^[0-9][a-z]$/ = Exact match

## DOM (Document Object Model)
- DOM tree
- Access this structure with Javascript
- "document" object (eg. `document.getElementById()`)
- HTML objects have attribute `.innerHTML`
- "localStorage" object (eg. `localStorage.setItem()`)


## JSON (JavaScript Object Notation)
- JSON --> String = `JSON.stringify(object)`
- Object --> JSON = `JSON.parse(string)`
- Have to save javascript objects as string JSON

## DOM Events (using event listeners)
- var = button
- `button.addEventListener("click", clickHandler)`
- html events you can listen for: https://www.w3schools.com/jsref/dom_obj_event.asp
- `clickHandler(e)` is a callback function, e is the event (argument taken by callback functions)
- Synchronous programming = When the code repeatedly asks "did they do something? did they do something?"
- Asynchronous programming = When the code says "browser, just let me know when they do something"

## jQuery
- Library to make DOM manipulation easier in JS
- `$(selector).action(arguments...)`
- selector works the same way as css selectors
- action is any event (.css, .html, .mouseleave, .click, .hover, )
- arguments is the function/value you pass
- `$(ul.highlight).find(li).on()` = run function "on" on all list items in the list with class highlight
- .on() alows you to specify multiple callback functions for the same selector
- `$(div[name='title'])` chooses all divs with name 'title'

# React

## VirtualDOM

- All about these
- Components have STATE
- Renders according to state
- VirtualDOM
- StandardDOM: If any node changes, updates all nodes
- VirtualDOM: If a node changes, only updates affected nodes (hence the component model)
		- Diff = Figuring out which node changed
		- Reconciliation = Updating the nodes that are affected
- class Barney extends React.Component

Side note - unicode characters can render things like a thumbs up or a twitter sign in the HTML

## REACT COMPONENTS

### Functional (reccomended for use)
- State = const [state, setState] = React.useState(0)
- Lifecycle = useEffect(function, [])
- Note: React hooks are just how functional components manage state and lifecycle

### Class based:
- State = this.state
- Lifecycle = componentDidMount

1 - render() function
- JSX of what html to render 
- ReactDOM.render(JSX component, html location to render)
- VirtualDOM calls the render() function of a react component when it updates

2 - Properties (this.props)
- Fixed attributes and values set when the component is initialised
- Set at creation eg. <Component name='barney' />

3 - State (this.state)
- Attributes/values that represent current state (modified during lifecycle)
- Handled in constructor(props) function, which is called when variable is initialised
- const [alive, setAlive] = React.useState(true)

4 - Lifecycle callback functions
- Mounting: When added to the virtualDOM
- Updating: When updated
- Unmounting: When removed from virtualDOM
- These functions are replaced by React.useEffect(function, arg) in functional components

## EVENTS WITH REACT COMPONENTS

- this.setState() will call the render function automatically because the state has changed

### General react component setup:

class myComponent extends React.Component {

    constructor(props) {
        super(props)
        this.state = {liked : false}
    }

		// Callbackfunction 1 (you can have loads of these)
    handleClick() {
        this.setState( {liked: !this.state.liked} )
    }
	
    render() {
        return(
            <div>
                <button onClick={this.handleClick.bind(this)}>{this.state.liked}</button>
            </div>
        )
    } 
}

## Component Interraction
- Facebook recommend lifting state up to the highest common denominating component
- Child components take state as a props argument (ie state is not managed in child component)
- This is called "composition", as one component composes the other
- Child components should PASS STATE UP TO PARENT
- eg. <MainList sublists={this.state.sublsists} newlist={this.state.addsublist.bind(this)} />
- Then you can call the addsublist function in the child components
- Homework 5 is a great example of this

## Using APIs with React
- SaaS
- Jeff Bezos letter to employees: "Every piece of software should be externalisable as a service"
- REST = "Representational State Transfer"

## Testing React
- Node allows us to use "require()" and "import"
- Create react app: package.json (all your app dependencies and info), public (all your static content) and src (javascript and css)
- Testing apps: Mocha, Chai, Enzyme
- In src create test folder
- Test version of "myfile.js" should be called "myfile.test.js" in the test folder
- Enzyme creates a mock up of your web page with {shallow} or {mount}
- Chai defines what you expect to happen
- Mocha  tests if the action you perform has the expected (from Chai) result
- Run "npm run test" when you have written all your test scripts

## ES6 
- New javascript
- Arrow notation
- Default function values - function(power=2) {}
- String literals (eg. '\n' for new line)
- use backticks to insert things into strings
- New data structures
- Set() = Ordered list of distinct values
- Map() = Set of key:value pairs

## D3 ("Data Driven Documents")
- SVG (Scalable Vector Graphics) = HTML element that resizes when the window resizes
- <svg><circle></circle></svg>
- D3 allows for SVG and HTML elements to adjust dynamically according to data in the page
- We do this using D3 in javascript
- eg. var svg = D3.select("svg"), svg.select("circle").attr("cx":12)

## Charts in D3
- Can make charts with D3
- D3 basically a library to make charts in HTML
- Maybe useful, just see docs if you want to use it

# Databases & Node

- Web is clients sending requests to servers
- Up until now only looked at client
- Servers listen for requests
- Node treats requests as events, allowing for callback functions

## Express
- Built on top of node
- Allows you to write middleware
 -Simplified backend with node
- var express = require('express')
- var app = express()

## Node requests and responses
- HTTP is a plain text protocol
- (req, res) => {}
- req.method, url, headers, get()
- res.status, type, write(), end(), send()

## Middleware
- = The function that is invoked during the running of an HTTP request
- Used in "middle" between request and response
- (req, res, next) --> middleware --> res
- next() is needed to run the next middleware function after this one is done
- app.use(url, middleware1, middleware2, ...)
- middleware1 needs to have a next() argument or 2 won't run
- (req, res, next) --> mware1 --> (req2, res2, next2) --> mware2 --> ... --> (req, res) => {res.end()}
- var commonRoute = express.Router().use(mware1, mware2, ...)
- commonRoute can now replace all those functions in the app.use(), in case you always run the same functions after one another

## Sending data to Node (GET)
- GET method sends query as part of url (eg bn.com/?name=barney&age=23&job=none)
- Starts with "?" and split by "&", spaces denoted by "+"
- Get data in node through req.query
- Can include params instead of query
- Take these in by doing app.use('/name/:userName') for name:userName parameter
- Get by doing req.params

## Sending data to Node (POST)
- req sent as part of the body
- Used when forms are submitted
- Forms have both 'action' and 'method' props if you want form data to go straight to the backend
- Need to use body-parser library if you do this
- eg. `<form action='/handleForm' method='post>`
- app.use('/handleForm', ...)

TIP ==> [].concat(value) will create an array of values regardless of whether value is an array or a string or something

Sending the response back
- Use EJS to split out formatting and server
- Not sure I'd do it this way given how I learned initially

Client <---> Server <---> Database

## [[MongoDB]]
- noSQL database (non relational)
- Stored as a collection of JSON objects
- Use mongoose to simply talk to mongoDB
- .find() .findOne() and .save()

## Advanced MongoDB Queries
- Can put objects in the schema for mongo
- eg authorSchema is one schema / object
- Book schema has authors = [authorSchema] is an array of authors in another schema
- AND search: query = {criteria}
- OR search : query = ($or : criteria), where criteria is an array of objects [{}, {}, {}]
- if you have multiple authors: query['authors.name'] = req.body.name to take name attribute of all authors
- to find text in text: query = {title : {$regex : req.body.title} }
- sort doing .sort({'title':'asc'}}

## Building an API with node and MongoDB
- Server sends back data to the client from the database instead of sending the whole html page to be rendered
- More memory efficient
- Process all data and filtering in backend
- Just send query in the frontend
- This is quite a different method to the react method from the bath course, seems worse

