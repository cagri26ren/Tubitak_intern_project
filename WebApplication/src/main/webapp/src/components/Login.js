import React, {useContext,useEffect } from 'react';
import TextField from '@material-ui/core/TextField';
import {UserContext} from "../UserContext";
import Button from '@material-ui/core/Button';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import history from '../history'
import axios from 'axios';
import { Redirect } from 'react-router-dom/cjs/react-router-dom.min';

const uri = "http://localhost:8080/"

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

function Login(props){
    const [username,setUsername] = React.useState("");
    const [password,setPassword] = React.useState("");
    const [open,setOpen] = React.useState(false);
    const [msg,setMsg] = React.useState("");
    const [alertType,setAlertType] = React.useState("");
    const {user,setUser} = useContext(UserContext);

    const handleClose = ()=>{
        setOpen(false);
      }

    const handleChange = (event) => {
        if(event.target.name === "username"){
            setUsername(event.target.value);
            console.log(username);
        }
        else{
            setPassword(event.target.value);
        }
    }

        const handleLogin = () =>{
        if(username === ""){
            setMsg("Username cannot be empty");
            setOpen(true);
            setAlertType("error");
        }
        else if(password === "" ){
            setMsg("Password cannot be empty");
            setOpen(true);
            setAlertType("error");
        }
        else{
            const postData={
                'username':username,
                'password':password
            }
            console.log(postData)
            axios.post(uri+"login",postData).then(response=>{
                console.log(response);
                if( response.data.messageType === 'SUCCESS'){
                    props.callbackLogin(response.data.message,"success");
                    setUser({"logedin":true});
                    history.push("/events")
                }
                else{
                    setOpen(true);
                    setAlertType('error');
                    setMsg(response.data.message);
                }
            })
        }

    }
    return(
        <div style={{height:"550px"}}>
            <div style={{width:"20%",justifyContent:"center", display:"flex",flexDirection:"column",marginTop:"10%",marginLeft:"500px"}}>
                <h1>Login</h1>
                <TextField
                    name = "username"
                    id="standard-full-width"
                    style={{ margin: 8 }}
                    placeholder="Username"
                    value={username}
                    size="medium"
                    margin="normal"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    onChange={(event)=>handleChange(event)}
                />
                <TextField
                    name="password"
                    id="standard-full-width2"
                    style={{ margin: 8 }}
                    placeholder="Password"
                    size="medium"
                    value={password}
                    margin="normal"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    onChange={(event)=>handleChange(event)}
                />
                <Button variant="contained" color="primary" onClick={handleLogin}>
                    Log in
                </Button>
            </div>
            <div>
                <Snackbar open={open} autoHideDuration={3000} onClose={()=>handleClose()}>
                <Alert onClose={()=>handleClose()} severity={alertType+''}>
                    {msg}
                </Alert>
                </Snackbar>
            </div>
        </div>
    )

}

export default Login;
