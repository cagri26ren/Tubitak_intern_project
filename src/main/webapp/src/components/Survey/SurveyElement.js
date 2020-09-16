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
import SurveyHeader from './SurveyHeader'
import SurveyComponent from './SurveyComponent'
import SurveyElementComponent from './SurveyElementComponent'
import { isThisSecond } from 'date-fns';

const uri = "http://localhost:8080/surveyQuestions";

function getParticipant(arr){
    console.log(arr);
    for( let i = 0; i < arr.length; i++ ){
        if( arr[i].tcKimlikNo === localStorage.getItem('tc')){
            return arr[i];
        }
    }
}


let number = 0;




class SurveyElement extends Component{
    constructor(props){
        super(props);
        this.state = {
            event:{},
            answers:[],
            loaded:false
        }
    }
    componentDidMount(){
        console.log(uri+window.location.href.substring(window.location.href.indexOf("/surveyQuestions") + 16));
        axios.post(uri+window.location.href.substring(window.location.href.indexOf("/surveyQuestions") + 16 ),{'tcKimlikNo':localStorage.getItem('tc')}).then(response => {
            this.setState({event:response.data,loaded:true})
            console.log(response)
        });
    }
    getPostData( event ){
        let arr = [];
        for( let i=0; i < getParticipant(event.participants).surveyQuestions.length; i++ ){
            let text = this.state.answers[i];
            if( this.state.answers[i] === undefined ){
                text = "";
            }
            arr.push(text);
        }
        let postData = [];
        for( let i = 0; i < getParticipant(event.participants).surveyQuestions.length; i++ ){
            const data ={
                'questionName': getParticipant(event.participants).surveyQuestions[i].questionName,
                'answer': arr[i],
                'tcKimlikNo':localStorage.getItem('tc')
            }
            postData.push(data);
        }
        console.log(postData);
        return postData;
    }
    handleTextArea(text,i){
        console.log(i);
        console.log(text);
        let arr = [...this.state.answers];
        arr[i] = text;
    
        this.setState({answers:arr});
        console.log(this.state.answers);
    }

    createEventComponents(event){
        number = number + 1;
        console.log(event);
        return(
            <SurveyElementComponent
                question={event.questionName}
                text={this.state.answers[number-1]} 
                number={number}
                handleChange={(text,i)=>{this.handleTextArea(text,i)}}
            > 
            </SurveyElementComponent>
        )    
    }
    
    handleSubmit(event){
        console.log(uri+window.location.href.substring(window.location.href.indexOf("/surveyQuestions")+16));
        axios.put(uri+window.location.href.substring(window.location.href.indexOf("/surveyQuestions") + 16), this.getPostData(this.state.event) ).then(response=>{
            if(response.data.message==='ERROR'){
                console.log(response);
            }
            else{
                history.push("/events")
            }
        })
    }      


    render(){
        console.log("asdas")
        number = 0;
        return(
            <div width="100%">
                <div style={{marginLeft:'40%'}}> <h1>{this.state.event.name} Survey </h1> </div>
                <div style={{backgroundColor: 'black',height:'1px'}}></div>
                <div style={{width:'80%',border:'3px solid black',borderRadius:'25px',margin:'10px auto',backgroundColor:'white'}}>
                    <div style={{display:'flex',flexDirection:'column',justifyContent:'space-evenly'}}>
                        {(this.state.loaded)?getParticipant(this.state.event.participants).surveyQuestions.map( surveyQuestion => this.createEventComponents(surveyQuestion) ):<div></div>}
                    </div>
                    <div style={{marginLeft:'80%',padding:'30px'}}>
                            <Button variant="contained" color="primary" onClick={(event) => {this.handleSubmit(event)}}>
                                Submit
                            </Button>
                        </div>
                </div>

            </div>
        )
    }

}

export default SurveyElement;