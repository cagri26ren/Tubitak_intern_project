import React from 'react';
import Button from '@material-ui/core/Button';
import axios from 'axios';
import history from '../../history';


function SurveyComponent(props){

    console.log(localStorage.getItem('tc'))


    return(
        <div style={{height:'260px',width:'26%',border:'1px solid black'}}>
            <div style={{height:'260px',display:'flex',flexDirection:'column',justifyContent:'space-evenly'}}>
                <div>
                    Name: {props.name}
                </div>
                <div>
                    Date: {props.date}
                </div>
                <div style={{marginLeft:'25%'}}>
                    <Button variant="contained" color="primary" onClick={(event) => {history.push('/surveyQuestions/'+props.name )}}>
                        Take the survey
                    </Button>
                </div>
            </div>
        </div>
    )
}

export default SurveyComponent;