import 'date-fns';
import React, {Component} from "react";
import TextField from '@material-ui/core/TextField';
import SwitchSection from '../SwitchSection';
import history from '../../history';
import DateSection from './../DateSection';
import Button from '@material-ui/core/Button';
import MapContainer from '../../MapContainer';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import EditEventHeader from './EditEventHeader';
import axios from 'axios';
import { da } from 'date-fns/locale';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Switch from '@material-ui/core/Switch';
import OtherQuestions from '../OtherQuestions';


const uri = "http://localhost:8080/events"
function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
  }


function getMonthNameFromNumber(monthNumber){
    switch(monthNumber){
        case "01":
            return "January";
        case "02":
            return "February";
        case "03":
            return "March";
        case "04":
            return "April";
        case "05":
            return "May";
        case "06":
            return "June";
        case "07":
            return "July";
        case "08":
            return "August";
        case "09":
            return "September";
        case "10":
            return "October";
        case "11":
            return "November";
        default:
            return "December";    
    }
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

function fixDateForPicker(date){
    let year = date.substring(0,4);
    let month = getMonthNameFromNumber(date.substring(5,7));
    let day = date.substring(8,10);
    let hour = date.substring(11,13);
    let minute = date.substring(14,16);
    
    return ( month + " " + day + ", " + year + " " + hour + ":" + minute + ":00");
}

function fixDateForJSON(date){
    let year = date.substring(11,15);
    let month = getMonthNumberFromName(date.substring(4,7));
    let day = date.substring(8,10);
    
    return year+'-'+month+'-'+day;
}
function fixTimeForJSON(date){
    console.log(date);
    let hour = date.substring(16,18);
    let minute = date.substring(19,21);
    return hour+':'+minute;
}

class EditEvent extends Component{
    constructor(props){
        super(props);
        this.state = {
            eventName: "",
            eventCapacity: 0,

            open: false,
            msg: "",
            alertType: "",

            locationLat: null,
            locationLng: null,
            locationText: "",

            startDate: "",
            endDate: "",

            askGender: false,
            askAge: false,

            questions:[],
            surveyQuestions:[{'surveyQuestionName':''}],


            dateLoad:false,
            mapLoad:false

        }
    }
    handleQuestions( questions ){
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
    componentDidMount(){
        console.log( window.location.href.substring(window.location.href.indexOf('/view') + 6));
        axios.get( uri + window.location.href.substring(window.location.href.indexOf('/view'))).then(response =>{
            this.setState({
                eventName:response.data.name,
                eventCapacity:response.data.max,
                startDate: (response.data.startDate+"T"+response.data.startTime.substring(0,5)),
                endDate:(response.data.endDate+"T"+response.data.endTime.substring(0,5)),
                locationLat:response.data.lat,
                locationLng:response.data.lng,
                askAge: response.data.askAge,
                askGender: response.data.askGender,
                questions:response.data.questions,
                dateLoad:true,
                mapLoad:true
            })
        });
    }
    handleCreate(event){
        if((this.state.eventName === "")){
            this.setState({ alertType: "error", msg:"Event name can not be empty",open:!this.state.open});
        }
        else if( (this.state.eventCapacity === 0)){
            this.setState({ alertType: "error", msg:"Event capacity can not be empty",open:!this.state.open});
        }
        else{
            const postData ={
                'name':this.state.eventName,
                'max':this.state.eventCapacity,
                'startDate':fixDateForJSON(this.state.startDate),
                'startTime':fixTimeForJSON(this.state.startDate),
                'endDate':fixDateForJSON(this.state.endDate),
                'endTime':fixTimeForJSON(this.state.endDate),
                'lat':this.state.locationLat,
                'askAge':this.state.askAge,
                'askGender':this.state.askGender,
                'questions':this.state.questions,
                'surveyQuestions':this.state.surveyQuestions,
                'lng':this.state.locationLng
            };
            console.log(postData);

            axios.put(uri + (window.location.href.substring(window.location.href.indexOf('/view') + 5)),postData).then(response=>{
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
        }
    }
    handleInput(event){
        const name = event.target.name;
        const value = event.target.value;

        this.setState({
            [name]: value
        });
    }

    handleChange( change ){
        const name = change.target.name;
        const value = change.target.checked;

        this.setState({
            [name]: value
        });

        console.log(value);
    }

    handleClose(){
        this.setState({open:false,msg:'',alertType:''});
    }

    render(){
        console.log(this.state.locationLat);
        let date =  this.state.dateLoad ? <DateSection curStartDate={new Date(fixDateForPicker(this.state.startDate))} curEndDate={new Date(fixDateForPicker(this.state.endDate))} data ='a' dateChoosed={date => {
            this.setState({startDate: date.substring(0,date.indexOf("G")),endDate: date.substring(53)})}}>
        </DateSection> : <p>Loading...</p>
        let map = this.state.mapLoad ? <MapContainer curLat={this.state.locationLat} curLng={this.state.locationLng} locationChoosed={location => this.setState({locationLat:location.lat,locationLng:location.lng})}></MapContainer>
        : <p>Loading...</p>

        return(
            <div>
                <EditEventHeader></EditEventHeader>
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
                            <h2>Event Destination</h2>
                            <div style={{height: '1px',backgroundColor: 'black',width:'75%'}}></div>
                            <div style={{display:'flex',justifyContent:'space-evenly',alignItems:'center'}}>
                                <div style={{marginTop:'10px',marginBottom:'10px'}}>
                                    {map}
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
                            <h2>Time</h2>
                            <div style={{height: '1px',backgroundColor: 'black',width:'75%'}}></div>
                            {date}
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
                            <OtherQuestions type='other' addQuestions={(questions)=>this.handleQuestions(questions)}></OtherQuestions>
                        </div>
                        <div style={{float:'right',marginRight:'100px'}}>
                            <Button variant="contained" color="primary" onClick={(event) => this.handleCreate(event)}>
                                Edit Event
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
            </div>
        )
    }

}

export default EditEvent;