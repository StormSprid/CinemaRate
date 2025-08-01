console.log("adminHeader.js успешно загружен");

document.addEventListener("DOMContentLoaded", function () {
    // Загружаем header
    fetch("fragments/adminHeader.html")
        .then(response => response.text())
        .then(html => {
            document.getElementById("header-placeholder").innerHTML = html;
            initHeader(); // инициализация
        })
        .catch(err => {
            console.error("Ошибка загрузки header:", err);
        });
});

function initHeader() {
    const jwt = sessionStorage.getItem("token");
    let userName = '';

    // Получение имени пользователя
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
            return response.text();
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

    console.log("Кнопка инициализирована");

    // Обработчик кнопки миграции
    const migrateBtn = document.getElementById("migrateBtn");
    if (migrateBtn) {
        migrateBtn.addEventListener("click", () => {
            console.log("Нажата кнопка миграции");
            const loader = document.getElementById("loader");
            if (loader) loader.style.display = "block";

            fetch("/movie/migrate", { method: "POST" })
                .then(response => {
                    if (!response.ok) throw new Error("Ошибка миграции");
                    return response.text();
                })
                .then(result => {
                    console.log(result);
                    if (loader) loader.style.display = "none";
                    alert("Миграция завершена!");
                })
                .catch(err => {
                    if (loader) loader.style.display = "none";
                    alert("Ошибка миграции!");
                });
        });
    }

    // Logout
    function logout() {
        sessionStorage.removeItem("token");
        window.location.href = "/login.html";
    }
    window.logout = logout; // доступно глобально
}
