import React, { Component } from "react";
import ListItem from "./ListItem.js";
import AddItem from "./AddItem.js";
import { v4 as uuid } from "uuid";

class List extends Component {
  render() {
    var name = this.props.name;
    var items = this.props.items;

    if (items) {
      return (
        <div id={name} key={uuid()}>
          <h3>{name} List</h3>
          <ul>
            {items.map(function (item) {
              return (
                <li key={uuid()}>
                  <ListItem item={item} key={uuid()} />
                </li>
              );
            })}
          </ul>
          <AddItem idName={name} addItem={this.props.addItem.bind(this)} />
        </div>
      );
    }
    return (
      <div id={name} key={uuid()}>
        <h3>{name} List</h3>
        <AddItem idName={name} addItem={this.props.addItem.bind(this)} />
      </div>
    );
  }
}

export default List;
