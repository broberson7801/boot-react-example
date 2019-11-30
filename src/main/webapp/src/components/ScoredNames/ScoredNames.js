import React from 'react';
import ScoredName from '../ScoredNames/ScoredName/ScoredName'

const ScoredNames = (props) => {

    return props.data.sortedNames.map((object) => {
        return (
            <ScoredName 
            name={object}
            score={props.data.scoreMap.get(object)}
            />
        );

    });

}

export default ScoredNames;