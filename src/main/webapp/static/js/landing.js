const addTrip = function(trip) {
    const tableBody = document.getElementById('table-data')

    const entry = document.createElement('tr')

    entry.innerHTML = `<td>${trip}</td>`

    tableBody.appendChild(entry)
}

const getTrips = function() {
    const xhr = new XMLHttpRequest();
    const body= document.getElementById('loginErr')

    xhr.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200){
            const data = JSON.parse(xhr.responseText)
            data.forEach(element => {
                addTrip(element.tripName)
            });
        } else if(this.readyState == 4 && this.status == 401){
            body.innerHTML = '<p>Hey there, looks like you aren\'t <a href="/OutdoorApp/login">logged in!</a></p>'
        }
    }
    
    xhr.open('GET', 'http://http://18.188.250.14:8080/OutdoorApp/Trip/All')
    xhr.send();
}
getTrips()