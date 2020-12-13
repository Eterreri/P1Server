import React, { Component } from "react";
import EditTrip from './edit-trip';

class Trip extends Component {
  state = {
      edit: false,
  };
  render() {
    return (
      <tr>
        <td>{this.state.edit?<EditTrip id={this.props.tripId} UID={this.props.UID} edit={this.edit} />:this.props.tripName}</td>
        <td className="editTD">
          <button value={this.props.tripId} className="editBtn" onClick={this.toggleEdit}>
            EDIT
          </button>
        </td>
      </tr>
    );
  }

  toggleEdit = () => {
      let edit = this.state.edit;
      this.setState({edit: !edit})
  }

  edit = () => {
    this.toggleEdit();
    this.props.edit();
  }
}

export default Trip;
