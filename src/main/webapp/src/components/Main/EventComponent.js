import React, {Component} from "react";
import AccessibilityIcon from '@material-ui/icons/Accessibility';
import ChangingButtons from "./ChangingButtons";
import MapContainer from '../../MapContainerView'
import AssignmentIcon from '@material-ui/icons/Assignment';

class EventComponent extends Component{

    render(){
        return(
            <div className="eventContainer" >
                <div style={{display:'flex',justifyContent:'space-between'}}>
                    <h2 style={{marginLeft: '5%'}}>{this.props.header}</h2>
                    <div style = {{display:'flex',flexDirection:'row',alignItems:'center',marginRight:'3%'}}>
                        <div>
                            <AccessibilityIcon></AccessibilityIcon>
                        </div>
                        <div >
                            <p>{this.props.currentPeople} / {this.props.maxPeople}</p>
                        </div>
                    </div>
                </div>
                <div style={{backgroundColor: 'black', height:'2px'}}></div>
                <div style={{display:'flex', flexDirection:'row',justifyContent:'space-around',alignItems:'center', height:'200px'}}>
                    <div style={{alignSelf:'flex-start'}}>
                        <MapContainer lat={this.props.lat} lng={this.props.lng} width='300px' height='220px'></MapContainer>
                    </div>
                    <div style = {{width: '30%',alignSelf:'flex-end'}}>
                        <div>
                            <p>Start Date: {this.props.startTime}</p>
                            <p>End Date: {this.props.endTime}</p>
                        </div>
                        <ChangingButtons time = {this.props.joinExpireDate}buttonTime={this.props.buttonTime} name={this.props.header} current={this.props.currentPeople} max ={this.props.maxPeople} callbackAlert={(msg,alertType)=>this.props.callbackAlert(msg,alertType)}></ChangingButtons>
                    </div>
                </div>
            </div>
        )
    }
}


export default EventComponent;