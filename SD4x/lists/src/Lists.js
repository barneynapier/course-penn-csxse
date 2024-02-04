import React, { Component } from "react";
import List from "./List.js";
import { v4 as uuid } from "uuid";

class Lists extends Component {
  render() {
    // If there are no lists, display a relevant message
    if (this.props.lists.length === 0) {
      return (
        <div id="listsDiv" className="List">
          <h2>Add new lists to get started!</h2>
        </div>
      );
    }

    // Otherwise, for each list, create a div
    var lists = this.props.lists;
    var items = this.props.items;
    var addItem = this.props.addItem;
    return (
      <div key={uuid()}>
        {lists.map(function (listName) {
          return (
            <List
              name={listName}
              items={items[listName]}
              addItem={addItem.bind(this)}
              key={uuid()}
            />
          );
        })}
      </div>
    );
  }
}

export default Lists;
