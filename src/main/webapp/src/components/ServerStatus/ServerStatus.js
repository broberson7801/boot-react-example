import React from 'react'
import '../ServerStatus/ServerStatus.css'

const ServerStatus = (props) => {
    return (
        <div className="ServerStatus">
            <h1>Server Status</h1>
            <p>{props.serverStatus}</p>
        </div>
    );
}

export default ServerStatus;