import React, { Component } from "react";

class CreateTrip extends Component {
  state = {
    nameErr: "",
    tripName: "",
    UID: this.props.UID
  };

  render() {
    return (
      <div className="form-body">
        <h1>Create a New Trip</h1>
        <form onSubmit={this.handleSubmit}>
          <div className="form-group">
            <label htmlFor="tripName">Trip Name</label>
            <input type="text" name="tripName" onChange={this.handleChange} />
          </div>
          <div className="inErr">{this.state.nameErr}</div>
          <input type="submit" className="button" value="Create Trip"/>
        </form>
      </div>
    );
  }

  handleChange = (event) =>{
    let name = event.target.name;
    let value = event.target.value;
    this.setState({[name]: value});
  }

  handleSubmit = (event) => {
    event.preventDefault();

    if (this.state.tripName) {
      fetch(`http://ec2-18-188-250-14.us-east-2.compute.amazonaws.com:8080/OutdoorApp/Create/Trip/${this.state.UID}`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        credentials: "same-origin",
        body: JSON.stringify({
          name: this.state.tripName
        }),
      })
        .then(this.props.handleCreate());
    } else {
      this.setState({ nameErr: "Please input a Trip Name!" });
    }
  }
}

export default CreateTrip;
