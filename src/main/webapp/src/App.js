import React, { Component } from 'react';
import './App.css';
import ServerStatus from './components/Status/ServerStatus';

class App extends Component {

    
  render() {   

    return (
        <div>
            <ServerStatus></ServerStatus>
        </div>
    )

  }
}

export default App;
