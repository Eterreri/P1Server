import React, { Component } from "react";

class CreateAccount extends Component {
  state = {
    passErr: "",
    userErr: "",
    username: "",
    password: "",
    loginErr: "",
  };

  render() {
    return (
      <div className="form-body">
        <h1>Create a User Account</h1>
        <form onSubmit={this.handleSubmit}>
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input type="text" name="username" onChange={this.handleChange} />
          </div>
          <div className="inErr">{this.state.userErr}</div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input type="password" name="password" onChange={this.handleChange} />
          </div>
          <div className="inErr">{this.state.passErr}</div>
          <input type="submit" className="button" value="Create User"/>
        </form>
        <h1>{this.state.loginErr}</h1>
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

    const username = this.state.username
    const password = this.state.password
    this.setState({userErr : ""});
    this.setState({passErr : ""});
    this.setState({loginErr : ""});

    if (username && password) {
      fetch(`http://ec2-18-188-250-14.us-east-2.compute.amazonaws.com:8080/OutdoorApp/Login/CreateUser`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        credentials: "same-origin",
        body: JSON.stringify({
          username: username,
          password: password
        }),
      })
      .then(res => {
        if(res.status === 409){
          this.setState({loginErr: "Account Creation Failed! Try a different username!"})
        } else{
          this.props.handleCreate()
        }
      })
    } else if (username.value && !password.value) {
        this.setState({passErr : "Please input a password!"});
    } else if (!username.value && password.value) {
        this.setState({userErr : "Please input a username!"});
    } else {
      this.setState({userErr : "Please input a username!"});
      this.setState({passErr : "Please input a password!"});
    }
  };
}

export default CreateAccount;
