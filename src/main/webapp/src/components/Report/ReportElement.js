import 'date-fns';
import React, {Component,Fragment} from "react";
import ReportElementComponent from './ReportElementComponent';
import ReportElementHeader from './ReportElementHeader';
import Chart from '../Chart.js';
import Pagination from '@material-ui/lab/Pagination';
import axios from 'axios';

const uri = "http://localhost:8080/reports";
let number = 0;

function createReportElementComponents(event){
    number = number + 1;
    return(
        <ReportElementComponent
            number={number}
            name={event.name} 
            surname={event.surname}
            tc={event.tcKimlikNo}>
        </ReportElementComponent>
    )
}

function chartLogic( data ){
    let chartData = {
        labels:[],
        datasets:[{
            label:'Participant Count',
            data:[],
            backgroundColor:[]
        }]
    }
    let set = new Set();
    for( let i = 0; i < data.length; i++){
        let num = 0;
        if( !set.has(data[i].joinDate)){
            set.add(data[i].joinDate);
            for( let j = 0; j < data.length; j++){
                if( data[i].joinDate == data[j].joinDate ){
                    num++;
                }
            }
            chartData.labels.push(data[i].joinDate);
            chartData.datasets[0].data.push(num);
            chartData.datasets[0].backgroundColor.push('green');
        }
        
    }
    return chartData;
}

class ReportElement extends Component{
    constructor(props){
        super(props);
        this.state = {
            name:'',
            time:'',
            max:0,
            current:0,
            allParticipants:[],
            participants:[],
            page:1
        }
    }

    handlePageChange(event,newPage){
        let arr = [...this.state.allParticipants].slice(newPage*10 - 10 , newPage * 10);

        this.setState({page:newPage,participants:arr});
    }
      
    componentDidMount(){
        number = 0;
        axios.get(uri+window.location.href.substring(window.location.href.indexOf("/reports") + 8 )).then(response=>{
            this.setState({
                name: response.data.name,
                time: response.data.startDate.substring(8,10) + "-"+ response.data.startDate.substring(5,7) + "-"+ response.data.startDate.substring(0,4) + "/" + response.data.startTime.substring(0,5)
                +"  -  " + response.data.endDate.substring(8,10) + "-"+ response.data.endDate.substring(5,7) + "-"+ response.data.endDate.substring(0,4) + "/" + response.data.endTime.substring(0,5),
                max:response.data.max,
                current:response.data.current,
                allParticipants:response.data.participants,
                participants:response.data.participants.slice(0,10)
            })
        })
    }


    render(){
        let pagination = (this.state.allParticipants.length >= 10) ?<Pagination count={Math.ceil(this.state.allParticipants.length/10)} page={this.state.page} color="primary" onChange={(event,newPage)=>{this.handlePageChange(event,newPage)}} />
        : <div></div>
        return(
            <div>
                <ReportElementHeader name={this.state.name} time={this.state.time}></ReportElementHeader>
                <div>
                    <div style={{borderBottom:"1px solid black",marginTop:"1em"}}>
                        <div style={{marginLeft:"1em"}}>
                            <h4>Joined Users( {this.state.current + "/" + this.state.max} )</h4>
                        </div>
                    </div>
                    {(this.state.allParticipants.length===0) ? (<p>No one joined lol</p>) : (<div></div>)}
                    {this.state.participants.map( participant => createReportElementComponents(participant) )}
                </div>
                <div style={{float:'right',margin:'2%'}}>
                    {pagination}
                </div>
                <div>
                    <div style={{borderBottom:"1px solid black",marginTop:"1em"}}>
                        <div style={{marginLeft:"1em"}}>
                            <h4>Graph</h4>
                        </div>
                        <div style={{height:'1px',backgroundColor:'black'}}></div>
                        <div style={{width:"60%",margin:'2px auto'}}>
                            <Chart chartData={chartLogic(this.state.allParticipants)}></Chart>
                        </div>
                    </div>
                </div>
                <div>
                </div>
            </div>
        )
    }

}

export default ReportElement;