import React, { useContext, useState } from 'react';
import history from '../../history'
import {UserContext} from '../../UserContext'
import axios from 'axios';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';


const uri = "http://localhost:8080/events"

function ChangingButtons(props) {
    const {user} = useContext(UserContext);
    const [tc,setTc] = useState('');
    const [open,setOpen] = useState(false);
    const [winner,setWinner] = useState(''); 
    const [openGiveaway,setOpenGiveaway] = useState(false);

    const handleSubmit=()=>{
        axios.post(uri+"/checkParticipant",{'name':props.name,'tcKimlikNo':tc}).then(response=>{
            if(response.data.messageType==='ERROR'){
                props.callbackAlert(response.data.message,'error');
            }
            else{
                localStorage.setItem('tc',tc);
                history.push('/events/ask/' + props.name)
            }
        })
    }
    const handleGiveaway=()=>{
        axios.get(uri + "/giveaway/" + props.name).then(response=>{

            setWinner(response.data.name);
            setOpenGiveaway(false);
            setTimeout(()=>{setOpenGiveaway(true)},100);
        });
    }
    let curChoice;
    if( user != null){
        curChoice = 
        <div style={{display: 'flex', flexDirection:'row', justifyContent:'space-around', flexWrap:'wrap'}}>
            <div>
                <button id = 'editButt' onClick={()=>{
                    history.push('/events/view/'+props.name)}}> Edit </button>
            </div>
            <div>
                <button id = 'deleteButt' onClick={()=>{
                    console.log(uri + "/" + props.name);
                    axios.delete(uri + "/" + props.name).then(response=>{
                        let type;
                        if( response.data.messageType === 'ERROR' ){
                            type = "error";
                        }
                        else{
                            type = "success";
                        }
                        props.callbackAlert(response.data.message,type);
                        });
                    }} > Delete </button>
            </div>
            <div>
                <button id = 'giveawayButt' onClick={()=>{
                    setOpenGiveaway(true);
                    }} > Giveaway </button>
            </div>
        </div>
    }
    else{
        let askChoice;
        let date = new Date();
        let now = new Date(Date.now());
        date.setFullYear(props.buttonTime.substring(0,4));
        date.setMonth(props.buttonTime.substring(5,7));
        date.setDate(props.buttonTime.substring(8,10));
        date.setHours(props.buttonTime.substring(11,13));
        date.setMinutes(props.buttonTime.substring(14,16));
        askChoice = (now.getTime() < date.getTime() ) ? (<div>
        <button onClick={()=>setOpen(true)} id = 'ask' style={{width:'7em'}}> Ask Us </button>
    </div>) : (<div></div>)
        curChoice =
        <div style={{display:'flex',flexDirection:'row',justifyContent:'space-between',flexWrap:'wrap'}}>
            <div>
                {(now.getTime() < date.getTime() ) ? <button onClick={()=>(props.max>props.current)?history.push('/events/join/' + props.name):props.callbackAlert("This event is full","error")} id = 'ab' style={((now.getTime() < date.getTime() ) ) ? {width:'7em'} : {width:'10em'}}> Join </button>:<div></div>}
            </div>
            {askChoice}
        </div>
    }
  return (
    <div>
        {curChoice}
        <Dialog open={open} onClose={()=>{setOpen(false)}} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Ask Question</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            To ask a question you must be in this event. Enter your T.C. to let us know you are in this event.
                        </DialogContentText>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="name"
                            label="T.C."
                            type="text"
                            fullWidth
                            onChange={(change)=>{setTc(change.target.value)}}
                        />
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
        <Dialog open={openGiveaway} onClose={()=>{setOpenGiveaway(false)}} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Giveaway</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            {winner !=="" ? <div>Winner: {winner}</div> : <div>Randomly choose a winner</div>}
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={()=>{setOpenGiveaway(false)}} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={()=>handleGiveaway()} color="primary">
                            Submit
                        </Button>
                    </DialogActions>
        </Dialog>
    </div>
  );
}

export default ChangingButtons;
