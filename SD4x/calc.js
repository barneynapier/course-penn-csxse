/*
 * Implement all your JavaScript in this file!
 */

var display = "";
var memory;
var operator;
var newdisplay;
var ans;

function subtotal() {
  ans = ans ? ans : 0;
  if (!operator) {
    ans = Number($("#display").val());
  } else if (operator == "+") {
    ans = Number(memory) + Number(display);
  } else if (operator == "-") {
    ans = Number(memory) - Number(display);
  } else if (operator == "*") {
    ans = Number(memory) * Number(display);
  } else if (operator == "/") {
    ans = Number(memory) / Number(display);
  }
  console.log(ans);
  return ans;
}

$("button").click(function (e) {
  if (this.value && !newdisplay) {
    display = String(display) + String(this.value);
    $("#display").val(display);
  } else if (this.value && newdisplay) {
    display = this.value;
    $("#display").val(display);
    newdisplay = false;
  }
});

$("button").click(function () {
  display = $("#display").val();
});

// $("button").click(subtotal);

$(
  "#addButton, #subtractButton, #divideButton, #multiplyButton, #equalsButton"
).click(function (e) {
  subtotal();
  newdisplay = true;
  display = ans;
  memory = display;
  $("#display").val(display);
});

$("#addButton").click(function (e) {
  operator = "+";
});

$("#subtractButton").click(function (e) {
  operator = "-";
});

$("#multiplyButton").click(function (e) {
  operator = "*";
});

$("#divideButton").click(function (e) {
  operator = "/";
});

$("#equalsButton").click(function (e) {
  operator = undefined;
  newdisplay = true;
});

$("#clearButton").click(function (e) {
  display = "";
  ans = undefined;
  $("#display").val(display);
});
