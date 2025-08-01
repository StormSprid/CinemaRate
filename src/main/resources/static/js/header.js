const jwt = sessionStorage.getItem("token");
let userName = '';

fetch(`/user/me`, {
    method: "GET",
    headers: {
        "Authorization": "Bearer " + jwt
    }
})
    .then(response => {
        if (!response.ok) {
            throw new Error(`Ошибка HTTP: ${response.status}`);
        }
        return response.text(); // если сервер возвращает просто строку (имя)
    })
    .then(name => {
        userName = name;
        console.log('Имя пользователя:', userName);
        const usernameDisplay = document.getElementById("username-display");
        if (usernameDisplay) {
            usernameDisplay.innerText = "Hello, " + userName + "!";
        }
    })
    .catch(error => {
        console.error('Ошибка при получении имени пользователя:', error);
    });
// header.js
function logout() {

    sessionStorage.removeItem("token");



    // Редирект на логин
    window.location.href = "/login.html";
}
window.logout = logout;

