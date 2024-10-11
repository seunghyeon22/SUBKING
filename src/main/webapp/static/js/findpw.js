document.getElementById("login").onclick = function(event) {
	event.preventDefault();
	let userid = document.getElementById("user_id").value;
    let username = document.getElementById("user_name").value;
    let userphone = document.getElementById("user_phone").value; 
    let userinfo = { "user_id": userid,"user_name": username, "user_phone": userphone };

    fetch("http://localhost:8080/240930subKingProject/api/v1/tempFindPw", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userinfo)
    })
    .then(response => {
        return response.json();
    })
    .then(data => {
        console.log(data); 
        if (data.success) {
            alert(data.message);
            window.location.href = "http://localhost:8080/240930subKingProject/static/jsp/resetpw.jsp";
        } else {
            alert(data.message);
        }
    })
};
