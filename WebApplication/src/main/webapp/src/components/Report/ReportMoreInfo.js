import React,{Component} from 'react';
import axios from 'axios';
import MoreInfoHeader from './MoreInfoHeader'

const uri = "http://localhost:8080/reports/view";

function createAnswerComponents(event){
    return(
        <div style={{marginLeft:'3%'}}>
            <h3>Question: {event.questionName}</h3>
            <p style={{wordBreak:'break-all'}}>Answer: {event.answer}</p>
        </div>
    )
}
class ReportMoreInfo extends Component{

    state={
        participant:{},
        loaded:false
    }

    componentDidMount(){
        console.log(uri+window.location.href.substring(window.location.href.indexOf("/reports/view") + 13 ));
        axios.post(uri+window.location.href.substring(window.location.href.indexOf("/reports/view") + 13 ),{'tcKimlikNo':localStorage.getItem('tc')}).then(response=>{
            console.log(response);
            this.setState({participant:response.data,loaded:true});
        })
    }


    render(){
        return(
            <div>
                <MoreInfoHeader></MoreInfoHeader>
                <div style={{width:'60%'}}>
                <div style={{display:'flex',justifyContent:'space-evenly'}}>
                    <div>
                        <h2>Name: {this.state.participant.name}</h2>
                    </div>

                    <div>
                        <h2>Surname: {this.state.participant.surname}</h2>
                    </div>
                </div>

                <div style={{display:'flex',justifyContent:'space-evenly'}}>
                    {(this.state.participant.age !== 0) ? <div><h2>Age: {this.state.participant.age}</h2></div>:<div></div>}
                    {(this.state.participant.gender!=='yok') ? <div><h2>Gender: {this.state.participant.gender}</h2></div> : <div></div>}
                </div>

                <div style={{display:'flex',justifyContent:'space-evenly'}}>
                    <div>
                        <h2>Tc: {this.state.participant.tcKimlikNo}</h2>
                    </div>

                    <div>
                        <h2>Email: {this.state.participant.email}</h2>
                    </div>
                </div>
                </div>
                <div>Other Questions</div>
                <div style={{backgroundColor: 'black',height:'1px',width:'80%'}}></div>
                <div>
                    {(this.state.loaded)?this.state.participant.answers.map( event => createAnswerComponents(event) ): <div></div>}
                </div>              

            </div>
        )
    }
}

export default ReportMoreInfo;


