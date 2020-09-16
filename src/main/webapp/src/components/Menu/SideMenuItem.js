import React from 'react';
import styles from './MenuStyle.css';
import HomeIcon from '@material-ui/icons/Home';
import AssessmentIcon from '@material-ui/icons/Assessment';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import HelpIcon from '@material-ui/icons/Help';


function SideMenuItem(props){
    let cmp;
    let newStyle;
    if( props.icon === 'Homepage' ){
        cmp = <HomeIcon className='ax'></HomeIcon>;
        newStyle = 'normCon';
    }
    else if( props.icon === 'Reports'){
        cmp = <AssessmentIcon className='ax'></AssessmentIcon>;
        newStyle = 'normCon';
    }
    else if( props.icon == 'LogIn'){
        cmp = <ExitToAppIcon className='ax'></ExitToAppIcon>;
        newStyle ='logInCon';
    }
    else if( props.icon == 'Question'){
        cmp =<HelpIcon className='ax'></HelpIcon>
        newStyle ='normCon'
    }
    return(
        <div className= {newStyle}>
            <div className = 'menuItemStyle'>
                <div>
                    {cmp}
                </div>
                <div>
                    <text>{props.header}</text> 
                </div>
            </div>
        </div>
    )
}

export default SideMenuItem;