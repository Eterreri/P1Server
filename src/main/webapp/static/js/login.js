const onClick = function(event){
    event.preventDefault()

    const username = document.getElementById('username')
    const password = document.getElementById('password')
    const userErr = document.getElementById('userErr')
    const passErr = document.getElementById('passErr')
    userErr.innerHTML = ''
        passErr.innerHTML = ''

    if(username.value && password.value){
        const user = {
            "username": username.value,
            "password": password.value
        }
        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200){
                console.log("logged in")
                window.location.href = '/OutdoorApp'
            }
        }

        xhr.open('POST', 'http://18.188.250.14:8080/OutdoorApp/Login');
        xhr.send(JSON.stringify(user));
    } else if(username.value && !password.value){
        passErr.innerHTML = 'Please input a password!'
    } else if(!username.value && password.value){
        userErr.innerHTML = 'Please input a username!'
    } else{
        userErr.innerHTML = 'Please input a username!'
        passErr.innerHTML = 'Please input a password!'
    }
}
const submit = document.getElementById('login-submit')
submit.addEventListener('click', onClick)