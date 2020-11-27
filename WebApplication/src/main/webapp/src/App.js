import React, { useState,useMemo } from 'react';
import logo from './logo.svg';
import './App.css';
import SideMenu from './components/Menu/SideMenu';
import Main from './components/Main/Main';
import AddEvent from './components/AddEvent/AddEvent';
import Header from'./components/Header';
import history from './history';
import {Router, Route, Link} from 'react-router-dom';
import { BrowserRouter, Redirect } from 'react-router-dom/cjs/react-router-dom.min';
import ViewEvent from './components/ViewEvent/ViewEvent';
import EditEvent from './components/EditEvent/EditEvent';
import Login from './components/Login';
import { UserContext } from './UserContext';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import Report from './components/Report/Report';
import ReportElement from './components/Report/ReportElement';
import ReportMoreInfo from './components/Report/ReportMoreInfo';
import { set } from 'date-fns';
import QrCode from './components/QrCode';
import Ask from './components/AskQuestion/Ask';
import { createChainedFunction } from '@material-ui/core';
import Survey from './components/Survey/Survey'
import QuestionPage from './components/QuestionPage/QuestionPage';
import SurveyElement from './components/Survey/SurveyElement'
import AdminSurvey from './components/Survey/AdminSurvey'
import AdminSurveyElement from './components/Survey/AdminSurveyElement';


function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

function App() {
  const [name,setName] = useState('');
  const [tc,setTc] = useState('');
  const [change,setChange] = useState(false);
  const [user,setUser] = useState(null);
  const [open,setOpen] = useState(false);
  const [msg,setMsg] = useState('');
  const [alertType,setAlertType] = useState('');

  const providerValue = useMemo( () => ({user,setUser}) , [user,setUser]);
  const providerValueTc = useMemo( ()=> ({tc,setTc}),[tc,setTc] );

  const handleClose = ()=>{
    setOpen(false);
  }

  const handleCallBack=(msg,alertType)=>{
    console.log(msg);
    setOpen(true);
    setMsg(msg);
    setAlertType(alertType);
}

const handleMidClose=()=>{
  setChange(false);
}

  const handleMiddlePage=(name,tc)=>{
    setName(name);
    setTc(tc);
  }

  const handleTc =( TC )=>{
    setTc(TC);
    history.push("/e")
  }

  return (

    <Router history = {history}>
      <div style={{backgroundColor: "#fafbfc"}}>
          <SideMenu isLogedIn={user} logOut={()=>setUser(null)}></SideMenu>
          <div id="header"style={{marginLeft:'14%'}}>
            <Header></Header>
            <UserContext.Provider value= {providerValue}>
              <Route exact path="/" render={()=>{return(<Redirect to="/events"/>)}}/>
              <Route exact path = "/qrcode" component={()=>( <QrCode name={name} tc={tc}/> )}/>
              <Route exact path="/events" component={()=>(<Main contextUser = {user} /> )}/>
              <Route exact path = "/add-event" component={()=>((user) == null ? <Main/> : <AddEvent callbackAlert={(msg,alertType)=>{handleCallBack(msg,alertType)}} />)}/>
              <Route exact path = "/events/join/:name" component={()=><ViewEvent callbackMidPage={(name,tc)=>{handleMiddlePage(name,tc)}} callbackAlert={(msg,alertType)=>{handleCallBack(msg,alertType)}}/>}/>
              <Route exact path = "/events/view/:name" component={()=>((user) == null ? <Main/> : <EditEvent callbackAlert={(msg,alertType)=>{handleCallBack(msg,alertType)}} />)}/>
              <Route exact path = "/reports" component={()=>((user) == null ? <Main/> : <Report/>)}/>
              <Route exact path = "/reports/:name" component={(user) == null ? Main : ReportElement}/>
              <Route exact path = "/reports/view/:name" component={(user) == null ? Main : ReportMoreInfo}/>
              <Route exact path = "/events/ask/:name" component={()=>(<Ask />)}/>
              <Route exact path = "/questions" component={()=>((user) == null ? <Main/> : <QuestionPage/>)}/>
              <Route exact path = "/surveyQuestions" component={()=>((localStorage.getItem('tc'))==='' ? <Main/> : <Survey/>)}/>
              <Route exact path = "/allSurveyQuestions" component={()=>((user) == null ? <Main/> : <AdminSurvey/>)}/>
              <Route exact path = "/allSurveyQuestions/:name" component={()=>((user) == null ? <Main/> : <AdminSurveyElement/>)}/>
              <Route exact path = "/surveyQuestions/:name" component={()=>((localStorage.getItem('tc'))==='' ? <SurveyElement/> : <SurveyElement/>)}/>
              <Route exact path = "/login" component={()=>((user) != null ? <Main/> : <Login callbackLogin={(msg,alertType)=>{handleCallBack(msg,alertType)}}/>)}/>
            </UserContext.Provider>
          </div>
          <Snackbar open={open} autoHideDuration={3000} onClose={()=>handleClose()}>
              <Alert onClose={()=>handleClose()} severity={alertType}>
                  {msg}
              </Alert>
          </Snackbar>
      </div>
    </Router>
  );
}

export default App;
