import React, { Component } from "react";

class EditTrip extends Component {
  state = {
    name: "",
    id: this.props.id,
    UID: this.props.UID,
  };
  render() {
    return (
      <form className="edit" onSubmit={this.handleSubmit}>
        <input name="name" id="name" type="text" onChange={this.handleChange} />
        <input type="submit" className="button" value="Edit Trip" />
      </form>
    );
  }

  handleChange = (event) => {
    let name = event.target.name;
    let value = event.target.value;
    this.setState({ [name]: value });
  };

  handleSubmit = (event) => {
    event.preventDefault();

    if (this.state.name) {
      fetch(`http://ec2-18-188-250-14.us-east-2.compute.amazonaws.com:8080/OutdoorApp/Trip/Edit/${this.state.UID}`, {
        method: "PUT",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        credentials: "same-origin",
        body: JSON.stringify({
          name: this.state.name,
          id: this.state.id,
        }),
      }).then((res) => {
        if (res.status === 200) {
          this.props.edit();
        }
      });
    }
  };
}

export default EditTrip;
