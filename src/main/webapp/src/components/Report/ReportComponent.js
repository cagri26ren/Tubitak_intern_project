import React from 'react';
import Button from '@material-ui/core/Button';
import history from '../../history';

function ReportComponent(props){

    return(
        <div>
            <div style={{height:"50px",display:"flex",flexDirection:"row",borderRadius:"20px",width:"90%",margin:"3px auto",border:"2px solid black",justifyContent:"space-evenly",alignItems:"center"}}>
                    <div>{props.number})</div>
                    <div>Event Name: {props.name} </div>
                    <div>Time: {props.time}</div>
                    <div>Capacity: {props.people}</div>
                    <div><Button variant="contained" color="primary" onClick={()=>{
                        localStorage.setItem('eventName',props.name);
                        history.push("/reports/"+props.name)
                        }}>
                        More
                    </Button>
                </div>
            </div>
        </div>
    );
}

export default ReportComponent;