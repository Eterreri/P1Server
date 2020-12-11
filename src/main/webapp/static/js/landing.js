const addTrip = function(trip, tripid) {
    const tableBody = document.getElementById('table-data')

    const entry = document.createElement('tr')

    entry.innerHTML = `<td>${trip}</td><td id="trash"><button value=${tripid} id="trashBtn"><button></td>`

    tableBody.appendChild(entry)
}

const getTrips = function() {
    const xhr = new XMLHttpRequest();
    const body= document.getElementById('loginErr')

    xhr.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200){
            const data = JSON.parse(xhr.responseText)
            data.forEach(element => {
                addTrip(element.tripName, element.tripid)
            });
        } else if(this.readyState == 4 && this.status == 401){
            body.innerHTML = '<p>Hey there, looks like you aren\'t <a href="/OutdoorApp/login">logged in!</a></p>'
        }
    }
    
    xhr.open('GET', 'http://ec2-18-188-250-14.us-east-2.compute.amazonaws.com:8080/OutdoorApp/Trip/All')
    xhr.send();
}
getTrips()