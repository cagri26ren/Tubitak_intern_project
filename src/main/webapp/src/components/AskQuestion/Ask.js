import React,{Component} from 'react';
import axios from 'axios';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import MessageComponent from './MessageComponent';
import AskQuestionHeader from './AskQuestionHeader';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';

const uri = "http://localhost:8080/events/ask/"

//DO NOT FORGET TO EMPTY THE LOCAL STORAGE COMPWILLUNMOUNT
function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

class Ask extends Component{

    state={
        chats:[],
        postQuestion:"",
        openDialog:false,
        open:false,
        alertType:'error',
        theMsg:''
    }
    componentDidMount(){
        axios.post(uri+window.location.href.substring(window.location.href.indexOf('/ask')+5),{'tcKimlikNo':localStorage.getItem('tc')||''}).then(response=>{
            let arr;
            arr = [...response.data];
            this.setState({
                chats:arr
            })
        })
    }
    handleSubmit(){
        const postData ={
            'tcKimlikNo':localStorage.getItem('tc') || '',
            'question':this.state.postQuestion,
            'answer':''
        }
        if( this.state.postQuestion ===""){
            this.setState({open:true,alertType:'error',theMsg:'Question cannot be empty'});
        }
        else{
            axios.post(uri + "post/"+window.location.href.substring(window.location.href.indexOf('/ask')+5),postData).then(response=>{
                if(response.data.messageType === 'ERROR'){
                    this.setState({open:true,alertType:'error',theMsg:response.data.message});
                }
                else{
                    this.setState({openDialog:false})
                    this.setState({open:true,alertType:'success',theMsg:response.data.message});
                }
            })
        }
    }

    createChatComponents(chat){
        console.log(chat)
        let comp;
        comp = (chat.answer!=='')?(<div><MessageComponent type="answer" answer={chat.answer}></MessageComponent></div>):(<div></div>)
        return(
            <div>
                <div style={{backgroundColor: 'black',height:'1px'}}></div>
                <div>
                    <MessageComponent type="question" question={chat.question}></MessageComponent>
                </div>
                {comp}
                <div style={{backgroundColor: 'black',height:'1px',marginTop:'1%'}}></div>
             </div>
        )
    }
    render(){
        console.log("adasdf");
        return(
            <div style={{height:( 400 + (this.state.chats.length * 230 ) + 'px'), width:'100%'}}>
                <AskQuestionHeader></AskQuestionHeader>
                <div>
                    {this.state.chats.map( chat => this.createChatComponents(chat) )}
                </div>
                <div>
                    <div style={{width:'50%',marginTop:'10px',marginBottom:'10px',marginLeft:'43%'}}>
                        <Button onClick={()=>{this.setState({openDialog:true})}} variant="contained" color="primary">
                                Post Question
                        </Button>
                    </div>
                </div>
                <div>
                    <Dialog open={this.state.openDialog} onClose={()=>{this.setState({openDialog:false})}} aria-labelledby="form-dialog-title">
                        <DialogTitle id="form-dialog-title">Enter Question</DialogTitle>
                        <DialogContent>
                            <DialogContentText>
                                Enter the question you want to ask
                            </DialogContentText>
                            <TextareaAutosize aria-label="empty textarea" placeholder="Empty" value={this.state.postQuestion} 
                                        onChange={(text)=>{this.setState({postQuestion:text.target.value})}}/>
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={()=>{this.setState({openDialog:false})}} color="primary">
                                Cancel
                            </Button>
                            <Button onClick={()=>this.handleSubmit()} color="primary">
                                Submit
                            </Button>
                        </DialogActions>
                    </Dialog>
                </div>
                <Snackbar open={this.state.open} autoHideDuration={3000} onClose={()=>this.setState({open:false})}>
                    <Alert onClose={()=>this.setState({open:false})} severity={this.state.alertType}>
                        {this.state.theMsg}
                    </Alert>
                </Snackbar>

            </div>
        )
    }
}

export default Ask;