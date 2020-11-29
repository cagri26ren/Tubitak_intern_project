import React, {Component, useContext} from 'react';
import SideMenuItem from "./SideMenuItem";
import styles from "./MenuStyle.css";
import history from "../../history"
import axios from 'axios'
import { UserContext } from '../../UserContext';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';

const uri = "http://localhost:8080/checkParticipant"

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

class SideMenu extends Component{

    state={
        open:false,
        openDialog:false,
        theMsg:'',
        alertType:'error',
        tc:''
    }

    surveyHandle(){
        axios.post(uri,{'tcKimlikNo':this.state.tc}).then(response=>{
            console.log(this.state.tc);

            if( response.data.messageType === 'ERROR'){
                console.log(response);
                this.setState({
                    open:true,
                    theMsg:response.data.message,
                    alertType:'error'
                })
            }
            else{
                this.setState({openDialog:false})
                localStorage.setItem('tc',this.state.tc);
                history.push( "/surveyQuestions" );
            }
        })
    }

    render(){
        let name;
        return(
            <div className = "menuStyle">
                <div onClick={()=> {history.push('/')}}>
                    <SideMenuItem header = "Homepage" icon = "Homepage"></SideMenuItem>
                </div>
                <div onClick={()=>{history.push('/reports')}}>
                    {(this.props.isLogedIn!=null)?(<SideMenuItem header = "Reports"  icon = "Reports"></SideMenuItem>):(<div></div>)}
                </div>
                <div onClick={()=>{history.push('/questions')}}>
                    {(this.props.isLogedIn!=null)?(<SideMenuItem header = "Questions"  icon = "Question"></SideMenuItem>):(<div></div>)}
                </div>
                <div onClick={()=>{(this.props.isLogedIn==null) ? (this.setState({openDialog:true})):( history.push( "/allSurveyQuestions" ) ) } }>
                    {(<SideMenuItem header = "Surveys"  icon = "Question"></SideMenuItem>)}
                </div>
                <div onClick={() => { (this.props.isLogedIn==null) ? ( history.push('/login') ) : ( this.props.logOut() )}}>
                    <SideMenuItem header = { (this.props.isLogedIn==null) ? ("Log In") : ("Log Out") } icon = "LogIn"></SideMenuItem>
                </div>
                <div>
                    <Dialog open={this.state.openDialog} onClose={()=>{this.setState({openDialog:false})}} aria-labelledby="form-dialog-title">
                        <DialogTitle id="form-dialog-title">Enter Question</DialogTitle>
                        <DialogContent>
                            <DialogContentText>
                                Enter your tc
                            </DialogContentText>
                            <TextField
                                autoFocus
                                margin="dense"
                                id="name"
                                label="T.C."
                                type="text"
                                fullWidth
                                onChange={(change)=>{this.setState({ tc: change.target.value})}}
                            />
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={()=>{this.setState({openDialog:false})}} color="primary">
                                Cancel
                            </Button>
                            <Button onClick={()=>this.surveyHandle()} color="primary">
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


export default SideMenu;