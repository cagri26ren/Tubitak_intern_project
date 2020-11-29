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
import AdminSurveyElementComponent from './AdminSurveyElementComponent'

const uri = "http://localhost:8080/allSurveyQuestions";



let number = 0;




class AdminSurveyElement extends Component{
    constructor(props){
        super(props);
        this.state = {
            surveyQuestions:[],
            loaded:false
        }
    }
    componentDidMount(){
        axios.post(uri+window.location.href.substring(window.location.href.indexOf("/allSurveyQuestions") + 19 ),{'tcKimlikNo':localStorage.getItem('tc')}).then(response => {
            this.setState({surveyQuestions:response.data,loaded:true})
            console.log(response)
            localStorage.clear();
        });
        //tc boşalt
    }

    createEventComponents(event){
        number = number + 1;
        return(
            <AdminSurveyElementComponent
                question={event.questionName}
                text={event.answer} 
                number={number}
            > 
            </AdminSurveyElementComponent>
        )    
    }

    //Fix if you have time
    render(){
        console.log("asdas")
        number = 0;
        return(
            <div width="100%">
                <div style={{marginLeft:'40%'}}> <h1>{window.location.href.substring(window.location.href.indexOf("/allSurveyQuestions") + 20) } Survey Results</h1> </div>
                <div style={{backgroundColor: 'black',height:'1px'}}></div>
                <div style={{backgroundColor: 'orange',height:'10px'}}></div>
                <div style={{backgroundColor:'orange'}}>
                    <div style={{width:'80%',border:'3px solid black',borderRadius:'25px',margin:'0 auto',backgroundColor:'white'}}>
                        <div style={{display:'flex',flexDirection:'column',justifyContent:'space-evenly'}}>
                            {(this.state.loaded)?this.state.surveyQuestions.map( surveyQuestion => this.createEventComponents(surveyQuestion) ):<div></div>}
                        </div>
                    </div>
                </div>
                <div style={{backgroundColor: 'orange',height:'10px'}}></div>
            </div>
        )
    }
}

export default AdminSurveyElement;