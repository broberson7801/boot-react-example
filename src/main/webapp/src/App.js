import React, {useState} from 'react';
import './App.css';
import ServerStatus from './components/ServerStatus/ServerStatus'
import NameScore from './components/NameScore/NameScore'

const App = () => {

  return (
    <div>
      <ServerStatus />
      <br></br>
      <NameScore fileToScore={"C:/Users/brobe/workspace/name-score-application/src/test/resources/OCC_Three_Names.txt"} />
    </div>
  )

}

export default App;
