import React, { useEffect, useState } from 'react';
import ScoredNames from '../ScoredNames/ScoredNames';
import axios from 'axios';

const NameScore = (props) => {
    const [scoredNames, setScoredNames] = useState({ sortedNames: '', scoreMap: '' });
    const [showScoredNames, setShowScoredNames] = useState();

    /*
        file paths have backslashes, which are treated as escape characters;
        we convert all of the backslahes to forward slahes here
    */
    const formatHandler = (filePath) => {
        return filePath.split('\\').join('/');
    }

    /*
        axios for some reason returns java hashmaps as:
            {thisIsNotAString: 123, thisIsAlsoNotString: 456, etc.}
        we use this handler to convert to a Javascript Map
    */
    const scoreMapHandler = (scoreMapObject) => {
        let map = new Map();

        let result = Object.entries(scoreMapObject)
            .map(([key, value]) => ({ key, value }));
        
            result.map((object) => {
            map.set(object.key, object.value);
        });

        return map;
    }

    useEffect(() => {
        const requestBody = formatHandler(props.fileToScore);
        axios.post('http://localhost:8080/score', { fileToScore: requestBody })
            .then(response => response.data)
            .then(responseData => {
                setScoredNames(
                    {
                        sortedNames: responseData.sortedNames,
                        scoreMap: scoreMapHandler(responseData.scoreMap)
                    }
                );
                return responseData;
            }).then(res => {
                /* 
                    we place the setShowScoredNames() in it's own then() block
                    to enforce synchronous behavior;

                    the over arching useEffect() will otherwise call the 
                    setShowScoredNames(true) function, resulting in the 
                    return statement executing before the axios call 
                    finishes
                */
                setShowScoredNames(true);
            }).catch(error => {
                console.log(error.response.data);
            })
    }, [props.filePath]);

    return (

        <div>
            <h1>The Scored Names List</h1>
            {showScoredNames === true ?
                <div>
                    <ScoredNames data={scoredNames} />
                </div> :
                <h2>Getting the Score List</h2>}
        </div>
    )
}


export default NameScore;