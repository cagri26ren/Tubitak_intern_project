import React from 'react'
import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';
 
 
 
function MyComponent(props) {

  return (
    <LoadScript
      googleMapsApiKey="AIzaSyBCeAgR3VSJLN36d8Ip2OGE2X64_K-7l2E"
    >
      <GoogleMap
        mapContainerStyle={{width:props.width,height:props.height}}
        center={{lat:props.lat, lng:props.lng}}
        zoom={10}
      >
        { /* Child components, such as markers, info windows, etc. */ }
        <Marker position={{lat: props.lat, lng: props.lng}}/>
      </GoogleMap>
    </LoadScript>
  )
}
 
export default React.memo(MyComponent)