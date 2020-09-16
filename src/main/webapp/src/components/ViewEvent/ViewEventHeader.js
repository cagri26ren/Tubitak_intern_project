import React from 'react';

function ViewEventHeader(props){
    return(
        <div>
            <div style={{height:'65px',marginLeft:'1em',display:'flex',justifyContent:'space-between'}}>
                <div>
                    <h1>Join Event</h1>
                </div>
                <div style={{alignSelf:'flex-end',marginRight:'10em'}}>
                    <span style={{marginRight:'15em'}}>Event Name: {props.eventName}</span>
                    Time: {props.startDate + " - " + props.endDate}
                </div>
            </div>
            <div style={{backgroundColor: 'black',height:'1px'}}></div>
        </div>
    )
}

export default ViewEventHeader;