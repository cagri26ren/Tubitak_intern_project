import 'date-fns';
import React, {Component,Fragment} from "react";
import TextField from '@material-ui/core/TextField';
import history from '../../history';
import DateSection from '../DateSection';
import Button from '@material-ui/core/Button';
import Pagination from '@material-ui/lab/Pagination';
import MapContainer from '../../MapContainer';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import ReportHeader from './ReportHeader';
import ReportComponent from './ReportComponent';
import Chart from '../Chart';
import axios from 'axios';

const uri = "http://localhost:8080/reports";

let number = 0;
function createReportComponents(event){
    number = number + 1;
    return(
        <ReportComponent
            number = {number}
            name={event.name} 
            time={event.startDate.substring(8,10) + "-"+ event.startDate.substring(5,7) + "-"+ event.startDate.substring(0,4) + "/" + event.startTime.substring(0,5)
                +"  -  " + event.endDate.substring(8,10) + "-"+ event.endDate.substring(5,7) + "-"+ event.endDate.substring(0,4) + "/" + event.endTime.substring(0,5)} 
            people={event.current + "/" + event.max}>
        </ReportComponent>
    )
}

function chartLogic( data ){
    let chartData = {
        labels:[],
        datasets:[{
            label:'Event',
            data:[],
            backgroundColor:[]
        }]
    }
    for( let i = 0; i < data.length; i++ ){
        chartData.labels.push(data[i].name);
        chartData.datasets[0].data.push(data[i].current);
        chartData.datasets[0].backgroundColor.push('blue');
    }
    return chartData;
}


class Report extends Component{
    constructor(props){
        super(props);
        this.state = {
            events:[],
            allEvents:[],
            page:1
        }
    }
    componentDidMount(){
        number = 0;
        axios.get(uri).then(response => {
            this.setState({allEvents:response.data,events:response.data.slice(0,10)})
            console.log(this.state.allEvents)
        });
    }

    handlePageChange(event,newPage){
        let arr = [...this.state.allEvents].slice(newPage*10 - 10 , newPage * 10);

        this.setState({page:newPage,events:arr});
    }
      


    render(){
        let pagination = (this.state.allEvents.length >= 10) ?<Pagination count={Math.ceil(this.state.allEvents.length/10)} page={this.state.page} color="primary" onChange={(event,newPage)=>{this.handlePageChange(event,newPage)}} />
        : <div></div>
        return(
            <div>
                <ReportHeader></ReportHeader>
                <div style={{width:"40%",margin:'20px auto'}}>
                    <Chart chartData={chartLogic(this.state.allEvents)}></Chart>
                </div>
                <div>
                    {this.state.events.map( event => createReportComponents(event) )}
                </div>
                <div style={{float:'right',margin:'2%'}}>
                    {pagination}
                </div>
                <div>
                </div>
            </div>
        )
    }

}

export default Report;