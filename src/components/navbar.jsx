import React, { Component } from "react";

class NavBar extends Component {
  state = {
    loggedIn: this.props.UID,
    currentPage: this.props.curr
  };

  currentPage = (page) => {
    return (page === this.state.currentPage ? 'curr' : 'notCurr');
  }

  handleClick = (event) => {
    this.setState({currentPage: event.target.id})
    this.props.handlePage(event);
  }

  handleLogout = () => {
    window.location.href = "/";
  }

  static getDerivedStateFromProps(props, state){
    if(props.UID !== state.UID){
      return {
        loggedIn: props.UID,
        currentPage: props.curr
      }
    }
    return null;
  }

  render() {
    return (
      <nav>
        <div className="top-nav">
          <button id="home" className={this.currentPage('home')} onClick={this.handleClick} >
            Home
          </button>
          {this.state.loggedIn && <button id="createTrip" className={this.currentPage('createTrip')} onClick={this.handleClick}>
            New Trip
          </button>}
          {this.state.loggedIn && <button id="logout" className={this.currentPage('logout')} onClick={this.handleLogout}>
            Logout
          </button>}
          {!this.state.loggedIn && <button id="createUser" className={this.currentPage('createUser')} onClick={this.handleClick}>
            Create Account
          </button>}
          {!this.state.loggedIn && <button id="login" className={this.currentPage('login')} onClick={this.handleClick}>
            Login
          </button>}
        </div>
      </nav>
    );
  }
}

export default NavBar;
