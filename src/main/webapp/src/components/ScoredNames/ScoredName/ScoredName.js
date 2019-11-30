import React from 'react';

const ScoredName = (props) => {
    return (
        <div>
            <h1>Name: {props.name}</h1>
            <h2>Score: {props.score}</h2>
        </div>
    );

}

export default ScoredName;