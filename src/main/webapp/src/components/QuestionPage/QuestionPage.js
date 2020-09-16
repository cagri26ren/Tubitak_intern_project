import 'date-fns';
import React, {Component,Fragment} from "react";
import TextField from '@material-ui/core/TextField';
import history from '../../history';
import DateSection from '../DateSection';
import Button from '@material-ui/core/Button';
import Pagination from '@material-ui/lab/Pagination';
import Chart from '../Chart';
import QuestionPageHeader from './QuestionPageHeader'
import axios from 'axios';
import QuestionComponent from './QuestionComponent';

const uri = "http://localhost:8080/questions";

let number = 0;

function getEventArrays( arr ){
    let newArr = [];

    for( let i = 0; i < arr.length; i++ ){
        for( let j = 0; j < arr[i].participants.length; j++ ){
            for( let k = 0; k < arr[i].participants[j].chats.length; k++ ){
                newArr.push(arr[i]);
            }
        }
    }
    return newArr;
}

function getParticipantArrays( arr ){
    let newArr = [];
    for( let i = 0; i < arr.length; i++ ){
        for( let j = 0; j < arr[i].participants.length; j++ ){
            for( let k = 0; k < arr[i].participants[j].chats.length; k++ ){
                newArr.push(arr[i].participants[j]);
            }
        }
    }
    return newArr;
}

function getChatsArrays( arr ){
    let newArr = [];
    for( let i = 0; i < arr.length; i++ ){
        for( let j = 0; j < arr[i].participants.length; j++ ){
            for( let k = 0; k < arr[i].participants[j].chats.length; k++ ){
                newArr.push(arr[i].participants[j].chats[k]);
            }
        }
    }
    return newArr;
}

class QuestionPage extends Component{
    state={
        events:[],
        allEvents:[],
        allChats:[],
        allParticipants:[],
        loaded:false,
        page:1

    }
    componentDidMount(){
        number = 0;
        axios.get(uri).then(response => {
            this.setState({
                allEvents:getEventArrays(response.data),
                events:getEventArrays(response.data).slice(0,5),
                allParticipants:getParticipantArrays(response.data),
                allChats:getChatsArrays(response.data)
           })
            console.log("finiiiiish");
            setTimeout( ()=>{this.setState({loaded:true})},3000);
            console.log(response)
        });
    }

    createQuestionComponents(event){

        if(this.state.page*5 > number && number < this.state.allEvents.length ){
            number = number + 1;
        }

        console.log(number);
        return(
            <QuestionComponent
                theNumber = {number}
                eventName={this.state.allEvents[number-1].name} 
                date={this.state.allEvents[number-1].startDate.substring(8,10) + "-"+ this.state.allEvents[number-1].startDate.substring(5,7) + "-"+ this.state.allEvents[number-1].startDate.substring(0,4) + "/" + this.state.allEvents[number-1].startTime.substring(0,5)
                    +"  -  " + this.state.allEvents[number-1].endDate.substring(8,10) + "-"+ this.state.allEvents[number-1].endDate.substring(5,7) + "-"+ this.state.allEvents[number-1].endDate.substring(0,4) + "/" + this.state.allEvents[number-1].endTime.substring(0,5)} 
                name={this.state.allParticipants[number-1].name}
                surname={this.state.allEvents[number-1].surname}
                tc={this.state.allParticipants[number-1].tcKimlikNo}
                question={this.state.allChats[number-1].question}>
            </QuestionComponent>
        )
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
                <QuestionPageHeader></QuestionPageHeader>
                <div>
                    {(this.state.loaded) ? this.state.events.map( event => this.createQuestionComponents(event) ) : <div></div>}
                    {(this.state.allEvents.length==0)?<div>No questions asked</div>:<div></div>}
                </div>
                <div style={{float:'right',margin:'2%'}}>
                    {pagination}
                </div>
            </div>
        )
    }

}

export default QuestionPage;