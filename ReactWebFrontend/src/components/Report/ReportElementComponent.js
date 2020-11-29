import React from 'react';
import Button from '@material-ui/core/Button';
import history from '../../history';


function ReportElementComponent(props){

    return(
        <div>
            <div style={{height:"30px",display:"flex",flexDirection:"row",width:"90%",margin:"5px auto",border:"2px solid black",justifyContent:"space-between",alignItems:"center"}}>
                <div>{props.number})</div>
                <div>Name: {props.name} </div>
                <div>Surname: {props.surname} </div>
                <div style={{marginRight:"10em"}}>T.C.: {props.tc}</div>
                <div><Button variant="contained" color="primary" onClick={()=>{
                        localStorage.setItem('tc',props.tc);
                        history.push("/reports/view/"+localStorage.getItem('eventName'));
                    }}>
                        More Info
                    </Button></div>
            </div>
        </div>
    );
}

export default ReportElementComponent;