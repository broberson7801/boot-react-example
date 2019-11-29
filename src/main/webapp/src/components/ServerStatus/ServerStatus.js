import React, { useState, useEffect } from 'react'
import axios from 'axios';

const errorHandler = error => {
    let errorMessage = "";
    if (!error.response) {
        errorMessage = 'Unable to connect to server';
    } else {
        errorMessage = error.response.data;
    }
    return errorMessage;
}

const ServerStatus = () => {
    const [serverStatus, setServerStatus] = useState('Fetching Server Status');
    
    useEffect(() => {
        axios.get('http://localhost:8080/server/up')
            .then(response => response)
            .then(responseData => {
                setServerStatus(responseData.data);
            })
            .catch(error => {
                setServerStatus(errorHandler(error));
            });
    });

    return (
            <div>
                <h1>Server Status</h1>
                <p>{serverStatus}</p>
            </div>
    );
}

export default ServerStatus;