import React from 'react';
import Button from '@material-ui/core/Button';
import axios from 'axios';
import history from '../../history';


function SurveyAdminComponent(props){

    console.log(localStorage.getItem('tc'))


    return(
        <div>
            <div style={{margin:'10px auto',height:'200px',width:'60%',border:'1px solid black'}}>
                <div style={{display:'flex',justifyContent:'space-evenly'}}>
                    <div>
                        {props.theNumber})
                    </div>
                    <div>
                        Event Name: {props.eventName}
                    </div>
                    <div>
                        Event Date: {props.date}
                    </div>
                </div>
                <div >
                    <h4>Participant</h4>
                    <div style={{backgroundColor: 'black',height:'1px',width:'60%'}}></div>
                </div>
                <div style={{display:'flex',justifyContent:'flex-start',marginTop:'20px',marginRight:'60%'}}>
                    <div style={{marginLeft:'5px'}}>
                        Name:{props.name} {props.surname}
                    </div>
                    <div style={{marginLeft:'10%'}}>
                        T.C.:{props.tc}
                    </div>
                </div>
                <div style={{marginLeft:'76%',marginTop:'10px'}}>
                    <Button variant="contained" color="primary" onClick={(event) => {
                        localStorage.setItem('tc',props.tc);
                        history.push( "/allSurveyQuestions/" + props.eventName );
                    }}>
                        See Results
                    </Button>
                </div>
            </div>
        </div>
    )
}

export default SurveyAdminComponent;