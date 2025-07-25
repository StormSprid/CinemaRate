console.log("adminHeader.js успешно загружен");

document.addEventListener("DOMContentLoaded", function () {
    // 1. Загружаем header
    fetch("fragments/adminHeader.html")
        .then(response => response.text())
        .then(html => {
            document.getElementById("header-placeholder").innerHTML = html;
            initHeader(); // вызываем основную инициализацию
        })
        .catch(err => {
            console.error("Ошибка загрузки header:", err);
        });
});

function initHeader() {
    const uuid = sessionStorage.getItem("sessionId");
    let userName = '';

    fetch(`user/me/name?uuid=${uuid}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Ошибка HTTP: ${response.status}`);
            }
            return response.text();
        })
        .then(name => {
            userName = name;
            console.log('Имя пользователя:', userName);
            document.getElementById("username-display").innerText = "Hello, " + userName + "!";
        })
        .catch(error => {
            console.error('Ошибка при получении имени пользователя:', error);
        });

    console.log("Кнопка инициализирована");

    document.getElementById("migrateBtn").addEventListener("click", () => {
        console.log("Нажата кнопка миграции");
        const loader = document.getElementById("loader");
        loader.style.display = "block";

        fetch("/movie/migrate", { method: "POST" })
            .then(response => {
                if (!response.ok) throw new Error("Ошибка миграции");
                return response.text();
            })
            .then(result => {
                console.log(result);
                loader.style.display = "none";
                alert("Миграция завершена!");
            })
            .catch(err => {
                loader.style.display = "none";
                alert("Ошибка миграции!");
            });
    });

    window.logout = function logout() {
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
    };
}
