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
