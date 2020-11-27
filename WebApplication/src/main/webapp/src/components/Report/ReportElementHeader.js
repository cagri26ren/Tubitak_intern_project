import React from 'react';

function ReportElementHeader(props){
    return(
        <div>
            <div style={{height:'65px',marginLeft:'1em',display:"flex",justifyContent:"space-between"}}>
                <div>
                    <h2>Event Name: {props.name}</h2>
                </div>
                <div style={{marginRight:"5em"}}>
                    <h2>Time: {props.time} </h2>
                </div>

            </div>
        </div>
    )
}

export default ReportElementHeader;