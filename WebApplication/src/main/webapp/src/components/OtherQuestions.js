import React, { useState } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

function OtherQuestions(props) {
    let number = 0;
    const[questionNumber,setQuestionNumber] = useState(0);
    const[questions,setQuestions] = useState([]);
    const[showButton,setShowButton] = useState(false);

    const handleCreate=(event)=>{
        number = 0;
        let arr = [];
        for( let i = 0; i < questionNumber; i++ ){
            arr.push("");
        }
        setQuestions(arr);
        setShowButton(true);
    }

    const handleInput=(event)=>{
        const name = event.target.name;
        const value = event.target.value;

        let arr = [...questions];
        arr[name] =value;
        setQuestions(arr);
    }


    const createEventComponents = (event) =>{
        number = number + 1;
        return(
            <div>
                <TextField name= {number-1} id="standard-basic" value={questions[number-1]} label={"Question" + number} onChange={(event)=>handleInput(event)}/>
            </div>
        )
    }
    let button =  (showButton)?<Button variant="contained" color="primary" onClick={(event)=>props.addQuestions(questions)}>Add questions</Button> : <div></div>

    return (
        <div>
            <div style={{ display:'flex',flexDirection:'row', flexWrap:'wrap',justifyContent:'space-evenly'}}>
                <div>
                    <TextField type="number" name= "otherQuestionNumberField" value={questionNumber} label={props.type === 'other' ? 'Number of Questions' : 'Number of Survey Questions'} onChange={(event)=>setQuestionNumber(event.target.value)}/>
                </div>
                <div>
                    <Button variant="contained" color="primary" onClick={(event) => handleCreate(event)}>
                            {props.type === 'other' ? <div>Generate Questions </div> : <div>Generate Survey</div>}
                    </Button>
                </div>
            </div>
            <div >
                {questions.map( question => createEventComponents(question) )}

                {(showButton)?<div style={{marginTop:'2%'}}><Button variant="contained" color="primary" onClick={(event)=>props.addQuestions(questions,props.type)}>Add questions</Button></div>: <div></div>}

            </div>
        </div>
    );
}

export default OtherQuestions;