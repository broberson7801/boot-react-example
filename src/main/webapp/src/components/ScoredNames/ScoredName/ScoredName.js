import React from 'react';
import '../ScoredName/ScoredName.css'

const ScoredName = (props) => {
    return (
        <div className="ScoredName">
            <h1>Name: {props.name}</h1>
            <h2>Score: {props.score}</h2>
        </div>
    );

}

export default ScoredName;