const onClick = function(event){
    event.preventDefault()

    const username = document.getElementById('username')
    const password = document.getElementById('password')
    const userErr = document.getElementById('userErr')
    const passErr = document.getElementById('passErr')
    const inputErr = document.getElementById('inputErr')
    userErr.innerHTML = ''
    passErr.innerHTML = ''
    inputErr.innerHTML = ''

    if(username.value && password.value){
        const user = {
            "username": username.value,
            "password": password.value
        }
        const xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 201){
                console.log("Account Created")
                window.location.href = '/OutdoorApp/login'
            } else if(this.readyState == 4 && this.status == 409){
                inputErr.innerHTML = 'Username already in use!'
            }
        }

        xhr.open('POST', 'http://localhost:8080/OutdoorApp/Login/CreateUser');
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

const submit = document.getElementById('user-submit')
submit.addEventListener('click', onClick)