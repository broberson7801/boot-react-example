import React, { useEffect, useState } from 'react';
import ScoredNames from '../ScoredNames/ScoredNames';
import ServerException from '../Exceptions/ServerException';
import axios from 'axios';

const NameScore = (props) => {
    const [scoredNames, setScoredNames] = useState({ sortedNames: '', scoreMap: '', listTotalScore: ''});
    const [showScoredNames, setShowScoredNames] = useState();
    const [error, setError] = useState();
    const [showError, setShowError] = useState();

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
                        scoreMap: scoreMapHandler(responseData.scoreMap),
                        listTotalScore: responseData.listTotalScore
                    }
                );
                setShowScoredNames(true);
            }).catch(error => {
                console.log(error.response.data);
                setError(error.response.data);
                setShowError(true);
            })
    }, [props.filePath]);

    const jsxHandler = () => {
        if(showScoredNames) {
            return <ScoredNames data={scoredNames} />
        } else if (showError) {
            return <ServerException error={error}/>
        } else {
            return null;
        }
    }

    return (
        <div>
            {jsxHandler()}
        </div>        
    )
}


export default NameScore;