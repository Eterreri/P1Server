const init = function(){
    const xhr = new XMLHttpRequest()

    xhr.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200){
            navbarInit(true)
        } else if(this.readyState == 4 && this.status == 400){
            navbarInit(false)
        }
    }
    
    xhr.open('GET', 'http://localhost:8080/OutdoorApp/Login')
    xhr.send();
}

const navbarInit = function(loggedin){
    let home = createTrip = createUser = login = logout = 'class="not"'
    const path = window.location.pathname.replace('/OutdoorApp', '')
    console.log(path)
    switch(path){
        case '/':
            home = 'class="curr"'
            break
        case '/create_trip':
            createTrip = 'class="curr"'
            break
        case '/create_user':
            createUser = 'class="curr"'
            break
        case '/login':
            login = 'class="curr"'
            break
        case '/logout':
            logout = 'class="curr"'
            break
    }
    let navbarHTML = `
    <nav>
        <div class="top-nav">
            <a id="home" ${home} href='/OutdoorApp'>Home</a>`
    if(loggedin){
        navbarHTML = navbarHTML.concat(`
                <a id="createTrip"  ${createTrip} href='/OutdoorApp/create_trip'>New Trip</a>
                <a id="logout"  ${logout} href='/OutdoorApp/logout'>Logout</a>
            </div>
        </nav>`)
    } else {
        navbarHTML = navbarHTML.concat(`
            <a id="createUser"  ${createUser} href='/OutdoorApp/create_user'>Create Account</a>
            <a id="login"  ${login} href='/OutdoorApp/login'>Login</a>    
        </div>
    </nav>`)
    }               
    document.getElementById('navbar').innerHTML = navbarHTML
}

function callFlashcardApi(data){
	console.log('fuck')
	console.log(data); 
}

init();