const uuid = sessionStorage.getItem("sessionId")
let userName = '';

fetch(`user/me/name?uuid=${uuid}`)
    .then(response => {
        if (!response.ok) {
            throw new Error(`Ошибка HTTP: ${response.status}`);
        }
        return response.text(); // так как сервер возвращает строку (имя)
    })
    .then(name => {
        userName = name;
        console.log('Имя пользователя:', userName);
        document.getElementById("username-display").innerText = "Hello, " + userName + "!";
    })
    .catch(error => {
        console.error('Ошибка при получении имени пользователя:', error);
    });

// header.js
function logout() {
    const uuid = sessionStorage.getItem("sessionId");
    if (!uuid) {
        alert("Сессия не найдена.");
        return;
    }

    fetch(`/user/logout?uuid=${uuid}`, {
        method: "POST"
    })
        .then(response => {
            if (response.ok) {
                sessionStorage.removeItem("sessionId");
                window.location.href = "/login.html";
            } else {
                alert("Ошибка при выходе");
            }
        })
        .catch(error => {
            console.error("Ошибка:", error);
            alert("Ошибка сети при выходе");
        });
}

// Сделать функцию доступной глобально
window.logout = logout;


