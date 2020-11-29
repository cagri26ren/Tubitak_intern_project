import React from 'react'
import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';
 
const containerStyle = {
  width: '400px',
  height: '400px'
};
 
const center = {
  lat: 38,
  lng: 35
};
 
function MyComponent({locationChoosed,curLat,curLng}) {
  const [map, setMap] = React.useState(null)
  const [newLat,setNewLat] = React.useState(curLat);
  const [newLng,setNewLng] = React.useState(curLng);
 
  const onLoad = React.useCallback(function callback(map) {
    const bounds = new window.google.maps.LatLngBounds();
    map.fitBounds(bounds);
    setMap(map)
  }, [])
 
  const onUnmount = React.useCallback(function callback(map) {
    setMap(null)
  }, [])

  return (
    <LoadScript
      googleMapsApiKey="AIzaSyBCeAgR3VSJLN36d8Ip2OGE2X64_K-7l2E"
    >
      <GoogleMap
        mapContainerStyle={containerStyle}
        center={{lat:curLat,lng:curLng}}
        zoom={10}
        onClick={(event)=>{
            locationChoosed({lat: newLat, lng: newLng});
            setNewLat(event.latLng.lat());
            setNewLng(event.latLng.lng());
        }}
      >
        { /* Child components, such as markers, info windows, etc. */ }
        <Marker position={{lat: newLat, lng:newLng}}/>
      </GoogleMap>
    </LoadScript>
  )
}
 
export default React.memo(MyComponent)