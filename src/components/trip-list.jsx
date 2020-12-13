import React, { Component } from "react";

class TripList extends Component {
  state = {
    UID: this.props.UID,
    trips: [],
    data: false,
  };

  render() {
    this.getDate();
    return (
      <div>
        <table className="table">
          <thead className="thead-dark">
            <tr>
              <th>{this.state.UID? "Your Trips": "Looks like you aren't logged in yet!"}</th>
            </tr>
          </thead>
          <tbody id="table-data">
            {this.state.trips.map((trip) => (
              <tr key={trip.tripId}>
                <td>{trip.tripName}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <div id="loginErr"></div>
      </div>
    );
  }

  getDate = () => {
    if (!this.state.data) {
      this.getTrips();
    }
  };

  getTrips = () => {
    if (this.state.UID) {
      fetch(`http://ec2-18-188-250-14.us-east-2.compute.amazonaws.com:8080/OutdoorApp/Trip/All/${this.state.UID}`, {
        method: "GET",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        credentials: "same-origin",
      })
        .then((res) => res.json())
        .then((data) => {
          let tripsl = [];
          data.forEach((trip) => {
            tripsl.push(trip);
          });
          this.setState({ trips: tripsl });
          this.setState({ data: true });
        });
    }
  };
}

export default TripList;
