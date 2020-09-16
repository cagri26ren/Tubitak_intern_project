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

const uri = "http://localhost:8080/surveyQuestions";


function createEventComponents(event){
    return(
        <SurveyComponent
            name={event.name} 
            date={event.startDate.substring(8,10) + "-"+ event.startDate.substring(5,7) + "-"+ event.startDate.substring(0,4) + "/" + event.startTime.substring(0,5)
                +"  -  " + event.endDate.substring(8,10) + "-"+ event.endDate.substring(5,7) + "-"+ event.endDate.substring(0,4) + "/" + event.endTime.substring(0,5)}
        > 
        </SurveyComponent>
    )    
}



class Survey extends Component{
    constructor(props){
        super(props);
        this.state = {
            events:[]
        }
    }
    componentDidMount(){
        axios.post(uri,{'tcKimlikNo':localStorage.getItem('tc')}).then(response => {
            this.setState({events:response.data})
        });
    }      


    render(){
        return(
            <div>
                <SurveyHeader></SurveyHeader>
                <div style={{display:'flex',flexDirection:'row',flexWrap:'wrap',justifyContent:'space-evenly'}}>
                    {this.state.events.map( event => createEventComponents(event) )}
                    {this.state.events.length===0?  <div>No surveys yet!</div> :   <div></div>}
                </div>
            </div>
        )
    }

}

export default Survey;