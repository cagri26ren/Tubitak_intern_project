import 'date-fns';
import React, {Component} from "react";
import TextField from '@material-ui/core/TextField';
import AddEventHeader from './AddEventHeader';
import SwitchSection from '../SwitchSection';
import history from '../../history';
import DateSection from '../DateSection';
import axios from 'axios';
import Button from '@material-ui/core/Button';
import MapContainer from '../../MapContainer';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import { Data } from '@react-google-maps/api';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Switch from '@material-ui/core/Switch';
import OtherQuestions from '../OtherQuestions';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

/**
 * Errors: 
 * 1-You need to click 2 times to set the location
 */

const uri = "http://localhost:8080/events"

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
  }

function getMonthNumberFromName(monthName){
    switch(monthName){
        case 'Jan':
            return '01';
        case 'Feb':
            return '02';
        case 'Mar':
            return '03';
        case 'Apr':
            return '04';
        case 'May':
            return '05';
        case 'Jun':
            return '06';
        case 'Jul':
            return '07';
        case 'Aug':
            return '08';
        case 'Sep':
            return '09';
        case 'Oct':
            return '10';
        case 'Nov':
            return '11';
        case 'Dec':
            return '12';
        default:
            return "error";
    }
}

function fixDateForJSON(date){
    let year = date.substring(11,15);
    let month = getMonthNumberFromName(date.substring(4,7));
    let day = date.substring(8,10);
    
    return year+'-'+month+'-'+day;
}
function fixTimeForJSON(date){
    let hour = date.substring(16,18);
    let minute = date.substring(19,21);
    return hour+':'+minute;
}
class AddEvent extends Component{
    constructor(props){
        super(props);
        this.state = {
            eventName: "",
            eventCapacity: 0,

            dialogOpen: false,
            open: false,
            msg: "",
            alertType: "",

            locationLat: null,
            locationLng: null,
            locationText: "",

            startDate: "",
            endDate: "",

            switchGender: false,
            switchAge: false,
            questions:[{}],
            surveyQuestions:[{}]
            
        }
    }
      
    handleClose(){
        this.setState({open:false,msg:'',alertType:''});
    }
    handleCreate(event){
        if((this.state.eventName === "")){
            this.setState({ alertType: "error", msg:"Event name can not be empty",open:!this.state.open});
        }
        else if( (this.state.eventCapacity === 0)){
            this.setState({ alertType: "error", msg:"Event capacity can not be empty",open:!this.state.open});
        }
        else if( this.state.locationLat == null || this.state.locationLng == null ){
            this.setState({ alertType: "error", msg:"You must select a location for the event",open:!this.state.open});
        }
    
        else{
            let arr =[];
            let condition = false;
            try {
                console.log(this.state.surveyQuestions[0].indexOf(""))
              }
              catch(err) {
                  condition=true;
                  arr.push("");
                  console.log("eeeeeeerooooooooe")
              }
            const postData ={
                'name':this.state.eventName,
                'max':this.state.eventCapacity,
                'startDate':fixDateForJSON(this.state.startDate),
                'startTime':fixTimeForJSON(this.state.startDate),
                'endDate':fixDateForJSON(this.state.endDate),
                'endTime':fixTimeForJSON(this.state.endDate),
                'lat':this.state.locationLat,
                'lng':this.state.locationLng,
                'askAge':this.state.askAge,
                'askGender':this.state.askGender,
                'questions':this.state.questions,
                'surveyQuestions': ( condition ) ? (arr):(this.state.surveyQuestions)
            };
            axios.post( uri, postData ).then( response => {
                console.log(response);
                let type;
                if( response.data.messageType === 'ERROR' ){
                    type = "error";
                }
                else{
                    type = "success";
                }
                if( type !== "success"  ){
                    this.setState({
                        open:true,
                        msg:response.data.message,
                        alertType:'error'
                    })
                }
                else{
                    this.props.callbackAlert(response.data.message,type);
                }
                if(response.data.messageType === 'SUCCESS'){
                    history.push("/events");
                }
            });
            //Alert the user and pass the data to backend
            //this.setState({ alertType: "succes", msg:"Event Created",open:!this.state.open});
            console.log(postData);
       }
    }
    handleInput(event){
        const name = event.target.name;
        const value = event.target.value;

        this.setState({
            [name]: value
        });
    }
    handleQuestions( questions , type){
        if( type === 'other'){
            setTimeout(()=>{
            let arr = [...questions];
            this.setState({
                questions:arr
            })
            console.log(this.state.questions);
            }
            ,
            100);
        }
        else{
            setTimeout(()=>{
                let arr = [...questions];
                this.setState({
                    surveyQuestions:arr
                })
                console.log(this.state.surveyQuestions);
                }
                ,100);
        }
    }
    handleChange( change ){
        const name = change.target.name;
        const value = change.target.checked;

        this.setState({
            [name]: value
        });

        console.log(value);
    }


    render(){
        return(
            <div>
                <AddEventHeader></AddEventHeader>
                <div className = "mainContainer">
                    <form>
                        <div>
                            <h2>Event Name(required)</h2>
                            <div style={{height: '1px',backgroundColor: 'black',width:'75%'}}></div>
                            <div style={{height:'100px', marginTop:'1em',marginLeft:'1em'}}>
                                <TextField name= "eventName" id="standard-basic" value={this.state.eventName} label="Event Name" onChange={(event)=>this.handleInput(event)}/>
                            </div>
                            <h2>Event Capacity(required)</h2>
                            <div style={{height: '1px',backgroundColor: 'black',width:'75%'}}></div>
                            <div style={{height:'100px', marginTop:'1em',marginLeft:'1em'}}>
                                <TextField type="number" name= "eventCapacity" id="standard-basic" value={this.state.eventCapacity} label="Event Capacity" onChange={(event)=>this.handleInput(event)}/>
                            </div>
                        </div>
                        <div>
                            <h2>Event Destination(required)</h2>
                            <div style={{height: '1px',backgroundColor: 'black',width:'75%'}}></div>
                            <div style={{display:'flex',justifyContent:'space-evenly',alignItems:'center'}}>
                                <div style={{marginTop:'10px',marginBottom:'10px'}}>
                                    <MapContainer curLat={10} curLng={10} locationChoosed={location => this.setState({locationLat:location.lat,locationLng:location.lng})}></MapContainer>
                                </div>
                                <div>
                                    <p>Enter Adress Here:</p>
                                    <div>
                                        <TextareaAutosize aria-label="empty textarea" placeholder="Empty" value={this.state.locationText} 
                                        onChange={(text)=>{this.setState({locationText:text})}}/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <h2>Time(required)</h2>
                            <div style={{height: '1px',backgroundColor: 'black',width:'75%'}}></div>
                            <DateSection curStartDate= {new Date()} curEndDate={new Date()} data ='a' dateChoosed={date => { 
                                this.setState({startDate: date.substring(0,date.indexOf("G")), 
                                endDate: date.substring(53)})}}>
                            </DateSection>
                        </div>
                        <div>
                            <h2>Optinal Choices</h2>
                            <p>This part is for optional questions for the outside user, choose the ones you want to ask</p>
                            <div style={{height: '1px',backgroundColor: 'black',width:'75%'}}></div>
                            <div style={{height:'100px', display:'flex',flexDirection:'row', flexWrap:'wrap',justifyContent:'space-evenly'}}>
                                <div>
                                    <FormControlLabel
                                        control={
                                            <Switch
                                                checked={this.state.askGender}
                                                onChange={(change)=>this.handleChange(change)}
                                                name="askGender"
                                                color="primary"
                                            />
                                        }
                                        label="Gender"
                                    />
                                </div>
                                <div>
                                    <FormControlLabel
                                        control={
                                            <Switch
                                            checked={this.state.askAge}
                                                onChange={(change)=>this.handleChange(change)}
                                                name="askAge"
                                                color="primary"
                                            />
                                        }
                                        label="Age"
                                    />
                                </div>
                            </div>
                        </div>
                        <div>
                            <OtherQuestions type="other" addQuestions={(questions,type)=>this.handleQuestions(questions,type)}></OtherQuestions>
                        </div>
                        <div style={{marginTop:'50px'}}>
                            <Button variant="contained" color="primary" onClick={(event) => this.setState({dialogOpen:true})}>
                                Create Survey?
                            </Button>
                        </div>
                        <div style={{float:'right',marginRight:'100px'}}>
                            <Button variant="contained" color="primary" onClick={(event) => this.handleCreate(event)}>
                                Create Event
                            </Button>
                        </div>
                    </form>
                </div>
                <div>
                <Snackbar open={this.state.open} autoHideDuration={3000} onClose={()=>this.handleClose()}>
                    <Alert onClose={()=>this.handleClose()} severity={this.state.alertType}>
                        {this.state.msg}
                    </Alert>
                </Snackbar>
                </div>
                <div>
                    <Dialog open={this.state.dialogOpen} onClose={()=>{this.setState({dialogOpen:false})}} aria-labelledby="form-dialog-title">
                        <DialogTitle id="form-dialog-title">Survey Maker</DialogTitle>
                        <DialogContent>
                            <DialogContentText>
                                Enter the questions you want to ask in your survey.
                            </DialogContentText>
                            <OtherQuestions type="survey" addQuestions={(questions,type)=>this.handleQuestions(questions,type)}></OtherQuestions>
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={()=>{this.setState({dialogOpen:false})}} color="primary">
                                Cancel
                            </Button>
                        </DialogActions>
                    </Dialog>
                </div>
            </div>
        )
    }

}

export default AddEvent;