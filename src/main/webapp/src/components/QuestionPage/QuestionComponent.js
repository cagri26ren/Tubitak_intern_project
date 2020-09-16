import axios from 'axios';
import React,{useState} from 'react';
import Button from '@material-ui/core/Button';
import history from '../../history';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';

const uri = "http://localhost:8080/events/ask/"
function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
  }

function QuestionComponent(props){
    const[open,setOpen] = useState(false);
    const[answer,setAnswer] = useState("");
    const[alertOpen,setAlertOpen] = useState(false);
    const[alertType,setAlertType] = useState("error");
    const[msg,setMsg] = useState("");


    const handleSubmit=()=>{
        console.log("entered");
        const postData={
            'tcKimlikNo':props.tc,
            'question':props.question,
            'answer':answer
        }
        axios.put(uri + "post/"+props.eventName,postData).then(response=>{
            console.log(response)
            console.log(uri + "post/"+props.eventName);
            console.log(postData);
            if(response.data.messageType === 'ERROR'){
                setAlertOpen(true);
                setMsg(response.data.message);
                setAlertType("error");
            }
            else{
                setAlertOpen(true);
                setMsg(response.data.message);
                setAlertType("success");
                setOpen(false);//ADDED NEW
            }
        })
        
    }

    return(

        <div style={{backgroundColor:'white',width:'75%',border:'1px solid black',borderRadius:'20px',marginTop:'10px',marginBottom:'10px',marginLeft:'13%'}}>
            <div  style={{paddingLeft:'10px'}}>
                {props.theNumber})
            </div>
            <div style={{display:'flex',justifyContent:'space-evenly'}}>
                <div>
                    <h4><b>Event Name:</b> {props.eventName}</h4>
                </div>
                <div>
                    <h4><b>Date:</b> {props.date} </h4>
                </div>
            </div>
            <div style={{backgroundColor: 'black',height:'1px'}}></div>
            <div style={{display:'flex',justifyContent:'space-evenly'}}>
                <div>
                    <h4><b>Name and Surname:</b> {props.name}</h4>
                </div>
                <div>
                    <h4><b>T.C.:</b> {props.tc} </h4>
                </div>
            </div>
            <div style={{display:'flex',justifyContent:'space-evenly'}}>
                <div>
                    <p style={{wordBreak:'break-all'}}>Question: {props.question}</p>
                </div>
                <div>
                    <Button variant="contained" color="primary" onClick={(event) => setOpen(true) }>
                        Answer
                    </Button>
                </div>
            </div>
            <div>
                <Dialog open={open} onClose={()=>{setOpen(false)}} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Question by {props.name} </DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            {props.question}
                        </DialogContentText>
                        <TextareaAutosize aria-label="empty textarea" placeholder="Empty" value={answer} 
                                        onChange={(text)=>{setAnswer(text.target.value)}}/>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={()=>{setOpen(false)}} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={()=>handleSubmit()} color="primary">
                            Submit
                        </Button>
                    </DialogActions>
                </Dialog>
                </div>
                <Snackbar open={alertOpen} autoHideDuration={3000} onClose={()=>setAlertOpen(false)}>
                    <Alert onClose={()=>setAlertOpen(false)} severity={alertType}>
                        {msg}
                    </Alert>
                </Snackbar>
        </div>
    );
}

export default QuestionComponent;