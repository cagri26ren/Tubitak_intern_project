import React from 'react';
import Button from '@material-ui/core/Button';
import history from '../../history';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import { datePickerDefaultProps } from '@material-ui/pickers/constants/prop-types';



function SurveyElementComponent(props){
    let header= props.number + ") " + props.question;

    return(
        <div style={{height:'300px',width:'60%',border:'1px solid black', margin:'10px auto',backgroundColor: "#fafbfc"}}>
            <div style={{marginLeft:'1%'}}>
                <h3> {header}</h3>
            </div>
            <div style={{marginLeft:'8%'}}>
                <textarea  placeholder="Empty" rows={14} cols={70} onChange={(text)=>{props.handleChange(text.target.value,props.number-1)}}>
                    {props.text}
                </textarea>
            </div>
        </div>
    );
}

export default SurveyElementComponent;