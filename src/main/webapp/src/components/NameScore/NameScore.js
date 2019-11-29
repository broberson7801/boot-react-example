import React, { useEffect, useState } from 'react';
import axios from 'axios';

const NameScore = (props) => {
    const [textFilePath, setTextFilePath] = useState();

    const formatHandler = (filePath) => {
        return filePath.split('\\').join('/');
    
    }


    useEffect(() => {
        const requestBody = formatHandler(props.fileToScore);
        console.group("INSIDE USE EFFECR" + requestBody);
        axios.post('http://localhost:8080/score', {fileToScore: requestBody})
        .then(response => response)
        .then(responseData => {
            console.log(responseData);
        }).catch(error => {
            console.log(error.response.data);
        })
    }, [props.filePath]);

    return (
        <h1>The NameScore</h1>
    )
}


export default NameScore;