const onClick = function(event){
    event.preventDefault()

    const tripName = document.getElementById('name')
    const tripErr = document.getElementById('tripErr')
    tripErr.innerHTML = ''

    if(tripName.value){
        const trip = {
            "name": tripName.value
        }
        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 201){
                console.log("trip created")
                window.location.href = '/OutdoorApp'
            }
        }

        xhr.open('POST', 'http://ec2-18-188-250-14.us-east-2.compute.amazonaws.com:8080/OutdoorApp/Create/Trip');
        xhr.send(JSON.stringify(trip));
    } else {
        tripErr.innerHTML = 'Please input a Trip Name!'
    } 
}

const submit = document.getElementById('trip-submit')
submit.addEventListener('click', onClick)