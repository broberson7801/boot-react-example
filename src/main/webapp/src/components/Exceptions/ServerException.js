import React from 'react'
import '../Exceptions/ServerException.css'

const ServerException = (props) => {

    return (
        <div className="ServerException">
            <h1>Error With File!</h1>
            <h2>Server Response: {props.error.errorMessage}</h2>
        </div>
    )

}

export default ServerException;