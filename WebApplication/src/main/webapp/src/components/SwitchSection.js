import React from 'react';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Switch from '@material-ui/core/Switch';

function SwitchSection({switchsChoosed}) {
    const [gender,setGender] = React.useState(false);
    const [age,setAge] = React.useState(false);

    const handleGenderChange = (event) => {
        setGender(event.target.checked);
        switchsChoosed(gender,age);
      };

    const handleAgeChange = (event) => {
        setAge(event.target.checked);
        switchsChoosed(gender,age);
    };
    
    return (
        <div style={{height:'100px', display:'flex',flexDirection:'row', flexWrap:'wrap',justifyContent:'space-evenly'}}>
            <div>
                <FormControlLabel
                    control={
                        <Switch
                            checked={gender}
                            onChange={handleGenderChange}
                            name="gender"
                            color="primary"
                        />
                    }
                    label="Gender"
                />
            </div>
            <div>
                <FormControlLabel
                    control={
                        <Switch
                        checked={age}
                            onChange={handleAgeChange}
                            name="age"
                            color="primary"
                        />
                    }
                    label="Age"
                />
            </div>
        </div>
    );
}

export default SwitchSection;