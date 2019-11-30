import React from 'react'

const ServerStatus = (props) => {
    return (
        <div>
            <h1>Server Status</h1>
            <p>{props.serverStatus}</p>
        </div>
    );
}

export default ServerStatus;