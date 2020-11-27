import React, { Component } from 'react';
import {Bar} from 'react-chartjs-2';
import { de } from 'date-fns/locale';
 

class Chart extends Component{

  constructor(props){
    super(props);
    this.state={
      chartData:{
        labels:['Boston', 'Worcester','Springfield','Lowell','Cambridge','New Bedford'],
        datasets:[{
          label:'Population',
          data:[
            123123,
            12341234,
            12341234,
            12341234,
            2341243,
            1234123
          ],
          backgroundColor:[
            'black',
            'green',
            'yellow',
            'blue',
            'green',
            'red',
          ]
        }]
      }
    }

  }

  render(){


    return(
      <div className="chart">
        <Bar
          data = {this.props.chartData}
          width={400}
          height={400}
          options={{
            maintainAspectRatio: false
          }}       
        
        />
      </div>

    )

  }

}

export default Chart;