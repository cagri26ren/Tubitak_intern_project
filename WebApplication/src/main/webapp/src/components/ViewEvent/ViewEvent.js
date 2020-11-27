import 'date-fns';
import React, {Component,Fragment} from "react";
import TextField from '@material-ui/core/TextField';
import history from '../../history';
import Button from '@material-ui/core/Button';
import MapContainer from '../../MapContainerView';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import ViewEventHeader from  './ViewEventHeader';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import axios from 'axios';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import { Redirect } from 'react-router-dom/cjs/react-router-dom.min';

import QrCode from '../QrCode';


const uri = "http://localhost:8080/events"

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}


function fixDateForJSON(date){
    console.log(date);

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

let number = 0;
class ViewEvent extends Component{
    state={
        joinDone:false,
        eventName:'',
        eventStartDate:'',
        eventEndDate:'',
        eventMax:0,
        eventCur:0,
        eventLat:0,
        eventLng:0,
        mapLoaded:false,

        name:'',
        surname:'',
        email:'',
        tc: '',

        age:0,
        askAge:true,
        gender:'yok',
        askGender:false,

        alertType:'',
        msg:'',
        open:false,

        questions:[],
        answers:[],
        surveyQuestions:[]
    }
    componentDidMount(){
        axios.get(uri + '/view' +window.location.href.substring(window.location.href.indexOf('/join')+5)).then(response=>{
            let arr = [];
            console.log(response.data.questions)
            for( let i = 0; i < response.data.questions.length; i++ ){
                if( response.data.questions[i].questionName !== null ){
                    arr.push({
                        "questionName": response.data.questions[i].questionName,
                        "answer": ''
                    })
                }
            }
            this.setState({
                eventName:response.data.name,
                eventStartDate: response.data.startDate.substring(8,10) + "-" + response.data.startDate.substring(5,7) + "-" + response.data.startDate.substring(0,4)
                + " / " + response.data.startTime,
                eventEndDate: response.data.endDate.substring(8,10) + "-" + response.data.endDate.substring(5,7) + "-" + response.data.endDate.substring(0,4)
                + " / " + response.data.endTime,
                eventLat: response.data.lat,
                eventLng: response.data.lng,
                askAge: response.data.askAge,
                askGender: response.data.askGender,
                questions: response.data.questions,
                answers: arr,
                surveyQuestions:response.data.surveyQuestions,
                mapLoaded:true
            })
            console.log(this.state.answers)
        })
    }
    createQuestionComponents(a){
        console.log()
        number = number + 1;
        return(
            <div style={{marginBottom:'5%',marginTop:'5%',marginLeft:'3%'}}>
                <TextField name= { number - 1 } id="standard-basic" value={this.state.answers[number-1].answer} label={a.questionName+"(Optional)"} onChange={(event)=>this.handleQuestionInput(event)}/>
            </div>
        )
    }
    handleClose(){
        this.setState({open:false,msg:'',alertType:''});
    }
    handleInput(event){
        const name = event.target.name;
        const value = event.target.value;

        this.setState({
            [name]: value
        });
    }

    handleQuestionInput(event){
        const name = event.target.name;
        console.log(name)
        const value = event.target.value;

        let arr = [...this.state.answers];
        arr[name].answer = value;
        this.setState({
            answers:arr
        })
    }

    handleJoin(){
        if(this.state.name=='' || this.state.surname=='' || this.state.tc =='' ||this.state.email==''){
            this.setState({ alertType: "error", msg:"You must fill all the necessary parts",open:true});
            return;
        }
        if( this.state.askAge && this.state.age===0){
            this.setState({ alertType: "error", msg:"You must fill all the necessary parts",open:true});
            return;
        }
        const date = "" +new Date();
        let surveyArr =[];
        for( let i = 0; i < this.state.surveyQuestions.length; i++){
            console.log(this.state.surveyQuestions[i].questionName);
            const pushData ={
                'questionName':this.state.surveyQuestions[i].surveyQuestionName,
                'answer':''
            }
            surveyArr.push(pushData);
        }
        const postData ={
            'name':this.state.name,
            'surname':this.state.surname,
            'email':this.state.email,
            'tcKimlikNo':this.state.tc,
            'joinDate': fixDateForJSON(  date ),
            'joinTime': fixTimeForJSON( date ),
            'age':this.state.age,
            'gender':this.state.gender,
            'answers':this.state.answers,
            'surveyQuestions':surveyArr
        };
        //Alert the user and pass the data to backend
        //this.setState({ alertType: "succes", msg:"Event Created",open:!this.state.open});
        axios.put(uri + (window.location.href.substring(window.location.href.indexOf('/join'))),postData).then(response=>{
            console.log(response);
            console.log(postData);
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
                    this.props.callbackMidPage(this.state.eventName,this.state.tc)
                    history.push("/qrcode")
                
                }
        });

    }

    render(){
        number = 0;
        const qr = (this.state.joinDone) ? <Redirect to="/events"/>:<div></div>;

        const map = this.state.mapLoaded ? <MapContainer lat={this.state.eventLat} lng={this.state.eventLng} width='400px' height='400px'></MapContainer> : <p>Loading...</p>;
        return(
            <div style={{height: (700 + (this.state.questions.length*50)) + 'px'  ,backgroundColor:"#fafbfc"}}>
                <ViewEventHeader eventName={this.state.eventName} startDate={this.state.eventStartDate} endDate={this.state.eventEndDate}></ViewEventHeader>
                    <div style={{height:'500px',display:'flex',justifyContent:'space-evenly'}}>
                    <div>
                        <form>
                            <p>Fill the informations that's asked to join the event</p>
                            <div style={{display:'flex', justifyContent:'flex-start',flexDirection:'row'}}>
                                <div style={{height:'100px', marginTop:'1em', marginLeft:'1em'}}>
                                            <TextField name= "name" id="standard-basic" value={this.state.name} label="Name" onChange={(event)=>this.handleInput(event)}/>
                                </div>
                                <div style={{height:'100px', marginTop:'1em', marginLeft:'10em'}}>
                                            <TextField name= "surname" id="standard-basic" value={this.state.surname} label="Surname" onChange={(event)=>this.handleInput(event)}/>
                                </div>
                            </div>
                            <div style={{height:'100px', marginTop:'1em', marginLeft:'1em'}}>
                                <TextField name= "email" id="standard-basic" value={this.state.email} label="Email" onChange={(event)=>this.handleInput(event)}/>
                            </div>
                            <div style={{height:'100px', marginTop:'1em', marginLeft:'1em'}}>
                                <TextField name= "tc" id="standard-basic" value={this.state.tc} label="T.C." onChange={(event)=>this.handleInput(event)}/>
                            </div>
                            <div style={{display:'flex', marginTop:'1em', marginLeft:'1em',justifyContent:'flex-start',flexDirection:'row',flexWrap:'wrap'}}>
                                {(this.state.askAge)?(<div><TextField type="number" name= "age" id="standard-basic" value={this.state.age} label="Age" onChange={(event)=>this.handleInput(event)}/></div>)
                                :(<div></div>)}
                                {(this.state.askGender)?(<div style={{marginLeft:'9em'}}> <RadioGroup aria-label="gender" name="gender" value={this.state.gender} onChange={(event)=>{console.log(event.target.value);this.setState({gender:event.target.value})}}>
                                                        <FormControlLabel value="female" control={<Radio />} label="Female" />
                                                        <FormControlLabel value="male" control={<Radio />} label="Male" />
                                                    </RadioGroup></div>)
                                :(<div></div>)}
                            </div>
                            <div>
                                {this.state.answers.map( a => this.createQuestionComponents(a) )}
                            </div>
                        </form>
                    </div>
                    <div style={{alignSelf:'flex-end'}}>
                        {map}
                        <div style={{width:'400px'}}>
                            <p style={{wordBreak:'break-all'}}>Description:</p>
                        </div>
                    </div>
                </div>
                <div style={{marginTop:(50 + (this.state.questions.length*50))+'px',marginRight:'50px',float:'right'}}>
                    <Button variant="contained" color="primary" onClick={(event) => this.handleJoin(event)}>
                            Join Event
                    </Button>
                </div>
                <div>
                    <Snackbar open={this.state.open} autoHideDuration={3000} onClose={()=>this.handleClose()}>
                        <Alert onClose={()=>this.handleClose()} severity={this.state.alertType}>
                            {this.state.msg}
                        </Alert>
                    </Snackbar>                
                </div>
            </div>
        );
    }

}

export default ViewEvent;