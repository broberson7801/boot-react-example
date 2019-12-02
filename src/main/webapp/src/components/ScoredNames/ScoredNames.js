import React from 'react';
import ScoredName from '../ScoredNames/ScoredName/ScoredName';
import '../ScoredNames/ScoredNames.css';

const ScoredNames = (props) => {

    const scoredNamesHandler = (props) => {
        return props.data.sortedNames.map((object, index) => {
            return (
                <ScoredName
                    key={index}
                    name={object}
                    score={props.data.scoreMap.get(object)}
                />
            );

        });
    }

    return (
        <div className="ScoredNames">
            <h1>Total Score Of List: {props.data.listTotalScore}</h1>
            {scoredNamesHandler(props)}
        </div>
    )

}

export default ScoredNames;