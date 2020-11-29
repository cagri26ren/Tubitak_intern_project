import React, { Component } from 'react';
import axios from 'axios';
import history from '../history';


const uri = "http://localhost:8080/events/qrcode";

class QrCode extends Component{

    state={
        qrCode: "",
        loaded: false
    }


    componentDidMount(){
        console.log("edasdfasdfsdfasdfa");
        const postData ={
            'name':this.props.name,
            'tcKimlikNo':this.props.tc,
        };
        console.log(postData);
        axios.post(uri,postData,{responseType:"arraybuffer"}).then(response=>{
            console.log(response);
            console.log(Buffer.from(response.data, 'binary').toString('base64'));
            this.setState({
                qrCode: "data:image/png;base64," + Buffer.from(response.data, 'binary').toString('base64'),
                loaded:true
            });
            setTimeout(()=>(history.push("/events")),5000);
        })
    }

    render(){
        console.log('data:image/png;base64,'+ this.state.qrCode);
        let image =(this.state.loaded) ? <img height="200px" width="200px" src={this.state.qrCode} alt="Red dot"/> : <div></div>;

        return(
            <div>
                <div style={{marginLeft:'40%',marginTop:'20%'}}>
                    {image}
                    <p>This qrcode has been sent to your email.</p>
                    <p>Use it when you are entering the event.</p>
                </div>
                <div style={{height:'368px',backgroundColor:'#fafbfc'}}>

                </div>
            </div>

        );

    }


}

export default QrCode;