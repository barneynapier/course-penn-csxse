class FontChooser extends React.Component {
  constructor(props) {
    super(props);

    var min = Math.max(this.props.min, 1);
    var max = Math.max(this.props.max, min);

    this.state = {
      bold: this.props.bold == "false" ? false : true,
      start_size: Math.max(Math.min(Number(this.props.size), max), min),
      size: Math.max(Math.min(Number(this.props.size), max), min),
      hideTools: true,
      fontMin: min,
      fontMax: max,
      fontRed: false,
    };
  }

  // Callback functions
  toggleBold() {
    this.setState({ bold: !this.state.bold });
  }

  increaseSize() {
    this.setState({
      size: Math.min(this.state.size + 1, this.state.fontMax),
    });
    if (this.state.size + 1 >= this.state.fontMax) {
      this.setState({ fontRed: true });
    } else {
      this.setState({ fontRed: false });
    }
  }

  decreaseSize() {
    this.setState({
      size: Math.max(this.state.size - 1, this.state.fontMin),
    });
    if (this.state.size - 1 <= this.state.fontMin) {
      this.setState({ fontRed: true });
    } else {
      this.setState({ fontRed: false });
    }
  }

  resetSize() {
    this.setState({ size: this.state.start_size });
  }

  toggleHidden() {
    this.setState({ hideTools: !this.state.hideTools });
  }

  render() {
    var styling = {
      fontWeight: this.state.bold ? "bold" : "normal",
      fontSize: this.state.size,
      color: this.state.fontRed ? "red" : "black",
    };

    return (
      <div>
        <input
          type="checkbox"
          id="boldCheckbox"
          hidden={this.state.hideTools}
          checked={this.state.bold}
          onChange={this.toggleBold.bind(this)}
        />
        <button
          id="decreaseButton"
          hidden={this.state.hideTools}
          onClick={this.decreaseSize.bind(this)}
        >
          -
        </button>
        <span
          id="fontSizeSpan"
          hidden={this.state.hideTools}
          onDoubleClick={this.resetSize.bind(this)}
        >
          {this.state.size}
        </span>
        <button
          id="increaseButton"
          hidden={this.state.hideTools}
          onClick={this.increaseSize.bind(this)}
        >
          +
        </button>
        <span
          id="textSpan"
          style={styling}
          onClick={this.toggleHidden.bind(this)}
        >
          {this.props.text}
        </span>
      </div>
    );
  }
}
