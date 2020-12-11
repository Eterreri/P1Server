const logout = function(){
    const xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200){
            console.log("logged out")
            window.location.href = '/OutdoorApp';
        }
    }
    xhr.open('GET', 'http://ec2-18-188-250-14.us-east-2.compute.amazonaws.com:8080/OutdoorApp/Logout');
    xhr.send();
}

logout();