import React, { useState, useEffect } from 'react';
import ServerStatus from '../ServerStatus/ServerStatus'
import NameScore from '../NameScore/NameScore';
import axios from 'axios';

const Cockpit = () => {
    const [filePath, setFilePath] = useState();
    const [showNamescore, setShowNameScore] = useState();
    const [serverStatus, setServerStatus] = useState('Fetching server status');
    const [showInputField, setShowInputField] = useState();

    /*
        this pings spring boot on component render;
        we don't want any .txt files getting entered if the server is down
    */
    useEffect(() => {
        axios.get('http://localhost:8080/server/up')
            .then(response => response)
            .then(responseData => {
                setServerStatus(responseData.data);
                setShowInputField(true);
            })
            .catch(error => {
                setServerStatus('Unable to connect to server');
            });

    });

    const filePathHandler = (event) => {
        if (event.key === 'Enter') {
            setFilePath(event.target.value);
            setShowNameScore(true);
        }
    }

    const handleChange = (event) => {
        setFilePath(event.target.value);
        setShowNameScore(false);
    }

    return (
        <div>
            <div>
                {/* display spring boot server status*/}
                {<ServerStatus serverStatus={serverStatus} />}
                <hr/>
                {showInputField ?
                    <input type="text"
                        value={filePath}
                        onChange={handleChange}
                        onKeyPress={filePathHandler} />
                    : null}
                {showNamescore ?
                    <NameScore
                        fileToScore={filePath} />
                    : null}
            </div>
        </div>
    )
}

export default Cockpit;