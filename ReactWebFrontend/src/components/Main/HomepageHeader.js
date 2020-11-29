import React, { useContext } from 'react';
import styles from './MainStyle.css';
import history from '../../history'
import { UserContext } from '../../UserContext';

function HomepageHeader() {
  const {user} = useContext(UserContext);
  return (
    <div style={{height:'65px'}}>
        <div style={{float:'right', height: '75%', marginTop:'0.8%', marginRight: '1%',width:'10%'}}>
            {user?<button id = 'a' onClick={()=>{history.push('/add-event')}} >Add Event</button>:<div></div>}
        </div>
    </div>
  );
}

export default HomepageHeader;