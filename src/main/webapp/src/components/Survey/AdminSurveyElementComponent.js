import React from 'react';
import Button from '@material-ui/core/Button';
import history from '../../history';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import { datePickerDefaultProps } from '@material-ui/pickers/constants/prop-types';



function AdminSurveyElementComponent(props){
    let header= props.number + ") " + props.question;

    return(
        <div style={{height:'300px',width:'60%',border:'1px solid black', margin:'10px auto',backgroundColor: "#fafbfc"}}>
            <div style={{marginLeft:'1%'}}>
                <h3> {header}</h3>
            </div>
            <div style={{marginLeft:'8%'}}>
                    <p>{props.text}</p>
            </div>
        </div>
    );
}

export default AdminSurveyElementComponent;