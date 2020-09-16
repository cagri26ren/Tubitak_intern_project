import React, {Component} from "react";
import HomepageHeader from './HomepageHeader';
import EventComponent from './EventComponent';
import axios from 'axios';
import { UserContext } from "../../UserContext";
import Pagination from '@material-ui/lab/Pagination';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';

const uriU = "http://localhost:8080/events"
const uriA = "http://localhost:8080/events/admin"


function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
  }

class Main extends Component{

    state={
        page:1,
        allEvents:[],
        events:[],
        theMsg:'',
        open:false,
        theAlertType:''
    }


    handleClose(){
        this.setState({open:false})
    }
    
    handleCallBack(msg,alertType){
        console.log(msg);
        console.log("entered handle");
       this.setState({
           theMsg:msg+'',
           theAlertType:alertType,
           open:true
       })
    }
    createEventComponents(event){
        return (<EventComponent 
            header={event.name} 
            currentPeople={event.current} 
            maxPeople={event.max} 
            lat={event.lat}
            lng={event.lng}
            buttonTime={event.startDate+"T"+event.startTime}
            startTime={event.startDate.substring(8,10) + "-" + event.startDate.substring(5,7) + "-" + event.startDate.substring(0,4) 
            + " / " + (event.startTime).substring(0,5)}
            endTime={event.endDate.substring(8,10) + "-" + event.endDate.substring(5,7) + "-" + event.endDate.substring(0,4) 
            + " / " + (event.endTime).substring(0,5)}
            callbackAlert={(msg,alertType)=>{this.handleCallBack(msg+"",alertType)}}
            
        ></EventComponent>);
    }
    componentDidMount(){
        console.log(this.props.contextUser)
        let uri = (this.props.contextUser===null)?(uriU):(uriA);
        console.log(uri);
        document.addEventListener('scroll', this.trackScrolling);
        axios.get(uri).then(response =>{
            this.setState({change:false,allEvents:response.data,events:response.data.slice(0,5)})
            console.log(this.state.allEvents)
        })
    }

    handlePageChange(event,newPage){
        let arr = [...this.state.allEvents].slice(newPage*5 - 5 , newPage * 5);

        this.setState({page:newPage,events:arr});
    }

    render(){
        let pagination = (this.state.allEvents.length >= 5) ?<Pagination count={Math.ceil(this.state.allEvents.length/5)} page={this.state.page} color="primary" onChange={(event,newPage)=>{this.handlePageChange(event,newPage)}} />
        : <div></div>
        return(
            <div style={{height:'100%',backgroundColor: '#fafbfc'}}>
                <div id='scrollA'  onScroll={this.handleScroll}>
                    <HomepageHeader></HomepageHeader>
                    <div style={{backgroundColor: 'black',height:'1px'}}></div>
                    <div style={{height:( 100+(this.state.events.length * 320 ) + 'px')}}>
                        {this.state.events.map( event => this.createEventComponents(event) )}
                    </div>
                </div>
                <div style={{float:'right',margin:'2%',backgroundColor: '#fafbfc'}}>
                    {pagination}
                </div>
                <Snackbar open={this.state.open} autoHideDuration={3000} onClose={()=>this.handleClose()}>
                    <Alert onClose={()=>this.handleClose()} severity={this.state.theAlertType}>
                        {this.state.theMsg}
                    </Alert>
                </Snackbar>
            </div>   
        )
    }
}


export default Main;