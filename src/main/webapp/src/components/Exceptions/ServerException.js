import React from 'react'

const ServerException = (props) => {

    return (
        <div>
        <h1>Error With File!</h1>
        <h2>Server Response: {props.error.errorMessage}</h2>
        </div>
    )
    
}

export default ServerException;