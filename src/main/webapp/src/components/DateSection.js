import 'date-fns';
import React,{useEffect} from 'react';
import DateFnsUtils from '@date-io/date-fns';
import Snackbar from "@material-ui/core/Snackbar";
import MuiAlert from "@material-ui/lab/Alert";
import { makeStyles } from "@material-ui/core/styles";
import {
  MuiPickersUtilsProvider,
  KeyboardTimePicker,
  KeyboardDatePicker,
} from '@material-ui/pickers';

function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}


const DateSection = ({dateChoosed,curStartDate,curEndDate}) => {
    const [selectedStartDate, setSelectedStartDate] = React.useState(curStartDate);
    const [selectedEndDate, setSelectedEndDate] = React.useState(curEndDate);


    //Code for alert starts here
    const [open, setOpen] = React.useState(false);
    const [msg, setMsg] = React.useState('');
    const useStyles = makeStyles((theme) => ({
      root: {
        width: "100%",
        "& > * + *": {
          marginTop: theme.spacing(2)
        }
      }
    }));

    const classes = useStyles();
    const handleClose = (event, reason) => {
      if (reason === "clickaway") {
        return;
      }
  
      setOpen(false);
    };
    //Ends here
    

  const handleStartDateChange = (date) => {
    console.log(selectedStartDate);
    if( selectedEndDate.getTime() >= date.getTime()){
      setSelectedStartDate(date);
      dateChoosed(selectedStartDate + "(start)-" +selectedEndDate + "(end)");
    }
    else{
      setMsg('Start date must be behind end date');
      setOpen(true);
    }
  };
  const handleEndDateChange = (date) => {
    if( date.getTime() >= selectedStartDate.getTime() ){
      setSelectedEndDate(date);
      dateChoosed(selectedStartDate + "(start)-" +selectedEndDate + "(end)");
    }
    else{
      setMsg('Start date must be behind end date');
      setOpen(true);
    }
  };

  return (
    <div>
      <div style = {{display:"flex",flexDirection:"row",justifyContent:"space-evenly",width: '75%'}}>
      <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <KeyboardDatePicker
            disableToolbar
            variant="inline"
            format="dd/MM/yyyy"
            margin="normal"
            id="startDate"
            label="Start Date"
            value={selectedStartDate}
            onChange={handleStartDateChange}
            KeyboardButtonProps={{
              'aria-label': 'change date',
            }}
          />
          <KeyboardDatePicker
            disableToolbar
            variant="inline"
            format="dd/MM/yyyy"
            margin="normal"
            id="endDate"
            label="End Date"
            value={selectedEndDate}
            onChange={handleEndDateChange}
            KeyboardButtonProps={{
              'aria-label': 'change date',
            }}
          />
      </MuiPickersUtilsProvider>
      </div>
      <div style = {{display:"flex",flexDirection:"row",justifyContent:"space-evenly",width: '75%'}}>
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <KeyboardTimePicker
              margin="normal"
              id="time-picker"
              label="Start Time"
              value={selectedStartDate}
              onChange={handleStartDateChange}
              KeyboardButtonProps={{
                'aria-label': 'change time',
              }}
            />
          <KeyboardTimePicker
              margin="normal"
              id="time-picker"
              label="End Time"
              value={selectedEndDate}
              onChange={handleEndDateChange}
              KeyboardButtonProps={{
                'aria-label': 'change time',
              }}
            />
        </MuiPickersUtilsProvider>
      </div>

      <div className={classes.root}>
        <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
          <Alert onClose={handleClose} severity="warning">
            {msg}
          </Alert>
        </Snackbar>
      </div>

    </div>
  );
}

export default DateSection;