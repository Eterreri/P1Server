import React, { Component } from "react";
import Navbar from "./navbar";
import TripList from "./trip-list";
import Login from "./login";
import CreateAccount from "./create-account";
import CreateTrip from "./create-trip";

class mainpage extends Component {
  state = {
    currentPage: "home",
    userID: "",
  };

  render() {
    return (
      <div>
        <Navbar curr={this.state.currentPage} handlePage={this.handlePage} UID={this.state.userID} />
        <div className="content">{this.contentLoad()}</div>
      </div>
    );
  }

  handlePage = (event) => {
    this.setState({ currentPage: event.target.id });
  };

  handleLogin = (user) => {
    this.setState({ userID: user });
    this.setState({ currentPage: "home"});
  };

  handleCreate = () => {
    this.setState({ currentPage: "home"});
  }

  contentLoad = () => {
    switch (this.state.currentPage) {
      case "home":
        return <TripList UID={this.state.userID} />;
      case "login":
        return <Login handleLogin={this.handleLogin} />;
      case "createUser":
        return <CreateAccount handleCreate={this.handleCreate} />;
      case "createTrip":
        return <CreateTrip UID={this.state.userID} handleCreate={this.handleCreate} />;
      default:
        return <h1>An Error occured!</h1>;
    }
  };
}

export default mainpage;
