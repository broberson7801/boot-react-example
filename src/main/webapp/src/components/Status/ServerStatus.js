import React, { Component } from 'react';
import axiios from 'axios';

class ServerStatus extends Component {
    state = {
        serverResponse: []
    }

    componentWillMount () {
    axiios.get(`http://localhost:8080/server/up`).then((res) => {
      const serverResponse = res.data  
      this.setState({serverResponse})
    });
  }


render () {
    return (
      <div>
        <div className="cell">
            <h1>Server Status: {this.state.serverResponse.toString()}</h1>
       </div>
      </div>
    );
  }
};

export default ServerStatus;
