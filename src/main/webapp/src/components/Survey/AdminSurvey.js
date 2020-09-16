import 'date-fns';
import React, {Component,Fragment} from "react";
import TextField from '@material-ui/core/TextField';
import history from '../../history';
import DateSection from '../DateSection';
import Button from '@material-ui/core/Button';
import Pagination from '@material-ui/lab/Pagination';
import MapContainer from '../../MapContainer';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import axios from 'axios';
import SurveyHeader from './SurveyHeader';
import SurveyAdminComponent from './SurveyAdminComponent';

const uri = "http://localhost:8080/surveyQuestions";

let number = 0;

function getEventArrays( arr ){
    let newArr = [];

    for( let i = 0; i < arr.length; i++ ){
        for( let j = 0; j < arr[i].participants.length; j++ ){
                newArr.push(arr[i]);
        }
    }
    return newArr;
}

function getParticipantArrays( arr ){
    let newArr = [];
    for( let i = 0; i < arr.length; i++ ){
        for( let j = 0; j < arr[i].participants.length; j++ ){
                newArr.push(arr[i].participants[j]);
        }
    }
    return newArr;
}


class AdminSurvey extends Component{
    constructor(props){
        super(props);
        this.state = {
            events:[],
            allEvents:[],
            allParticipants:[],
            loaded:false,
            page:1
        }
    }

    createSurveyComponents(event){

        if(this.state.page*5 > number && number < this.state.allEvents.length ){
            number = number + 1;
        }

        console.log(number);
        return(
            <SurveyAdminComponent
                theNumber = {number}
                eventName={this.state.allEvents[number-1].name} 
                date={this.state.allEvents[number-1].startDate.substring(8,10) + "-"+ this.state.allEvents[number-1].startDate.substring(5,7) + "-"+ this.state.allEvents[number-1].startDate.substring(0,4) + "/" + this.state.allEvents[number-1].startTime.substring(0,5)
                    +"  -  " + this.state.allEvents[number-1].endDate.substring(8,10) + "-"+ this.state.allEvents[number-1].endDate.substring(5,7) + "-"+ this.state.allEvents[number-1].endDate.substring(0,4) + "/" + this.state.allEvents[number-1].endTime.substring(0,5)} 
                name={this.state.allParticipants[number-1].name}
                surname={this.state.allEvents[number-1].surname}
                tc={this.state.allParticipants[number-1].tcKimlikNo}
            >
            </SurveyAdminComponent>
        )
    }

    componentDidMount(){
        number = 0;
        axios.get(uri).then(response => {
            this.setState({
                allEvents:getEventArrays(response.data),
                events:getEventArrays(response.data).slice(0,5),
                allParticipants:getParticipantArrays(response.data),
           })
            console.log("finiiiiish");
            setTimeout( ()=>{this.setState({loaded:true})},3000);
            console.log(response)
        });
    } 

    handlePageChange(event,newPage){
        let arr = [...this.state.allEvents].slice(newPage*5 - 5 , newPage * 5);

        this.setState({page:newPage,events:arr});
        console.log("start");
        number = newPage * 5 - 5;
        console.log(number);
    }


    render(){
        console.log(this.state.events);
        let pagination = (this.state.allEvents.length >= 5 && this.state.loaded) ?<Pagination count={Math.ceil(this.state.allEvents.length/5)} page={this.state.page} color="primary" onChange={(event,newPage)=>{this.handlePageChange(event,newPage)}} />
        : <div></div>
        return(
            <div>
                <SurveyHeader></SurveyHeader>
                <div>
                    {(this.state.loaded) ? this.state.events.map( event => this.createSurveyComponents(event) ) : <div></div>}
                </div>
                {pagination}
            </div>
        )
    }

}

export default AdminSurvey;