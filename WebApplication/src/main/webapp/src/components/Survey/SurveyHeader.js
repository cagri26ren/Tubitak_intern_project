import React from 'react';

//Zaman kalırsa ekle sisim
function SurveyHeader(props){
    return(
        <div>
            <div style={{height:'65px',marginLeft:'1em'}}>
                <h1>Surveys</h1>
            </div>
            <div style={{float:'right'}}>
                {props.name} {props.surname}
            </div>
            <div style={{backgroundColor: 'black',height:'1px'}}></div>
        </div>
    )
}

export default SurveyHeader;