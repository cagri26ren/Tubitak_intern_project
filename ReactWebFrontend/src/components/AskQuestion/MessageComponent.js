import React from 'react';
import axios from 'axios';
import TextField from '@material-ui/core/TextField';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';



function MessageComponent(props){
    let value;
    if( props.type === "question"){
        value =(
            <div style={{backgroundColor:'white',height:'200px',width:'350px',border:'2px solid black',borderRadius:'20px',marginLeft:'3%',marginTop:'1%'}}>

                <div style={{marginLeft:'1%',width:'80%'}}>
                    <h4><i>Question</i></h4>
                    <div style={{backgroundColor: 'black',height:'1px'}}></div>
                </div>
                <div style={{marginLeft:'1%'}}>
                    <p>{props.question}</p>
                </div>
            </div>
        )
    }
    else{
        value =(
            <div>
                <div style={{backgroundColor:'white',height:'200px',width:'350px',marginLeft:'70%',border:'2px solid black',borderRadius:'20px'}}>

                    <div style={{marginLeft:'1%',width:'80%'}}>
                        <h4><i>Answer</i></h4>
                        <div style={{backgroundColor: 'black',height:'1px'}}></div>
                    </div>
                    <div style={{marginLeft:'1%'}}>
                        <p>{props.answer}</p>
                    </div>
                </div>
            </div>
        )
    }

    return(
        <div>
            {value}
        </div>
    )
}

export default MessageComponent;